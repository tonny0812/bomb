package com.llmoe.bomb.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmoe.bomb.common.exception.BusinessException;
import com.llmoe.bomb.common.utils.UserAgentGetter;
import com.llmoe.bomb.entity.BombTask;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.entity.Whitelist;
import com.llmoe.bomb.mapper.BombTaskMapper;
import com.llmoe.bomb.mapper.ConfigMapper;
import com.llmoe.bomb.mapper.WhitelistMapper;
import com.llmoe.bomb.service.BombTaskService;
import com.llmoe.bomb.task.BombTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 轰炸请求 服务实现类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Service
public class BombTaskServiceImpl extends ServiceImpl<BombTaskMapper, BombTask> implements BombTaskService {

    /**
     * 白名单
     */
    @Autowired
    private WhitelistMapper whitelistMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Autowired
    private BombTasks bomTask;


    @Override
    public boolean batchDelete(String ids) {
        Integer[] array = Convert.toIntArray(ids);
        for (Integer id : array) {
            baseMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public boolean startBombing(BombTask bomb, HttpServletRequest request) {
        //添加
        BombTask one = baseMapper.selectOne(new QueryWrapper<BombTask>().eq("phone", bomb.getPhone()).ne("is_attack", 2));
        if (one != null) {
            throw new BusinessException("号码已在队列中");
        }

        int count = whitelistMapper.selectCount(new QueryWrapper<Whitelist>().eq("phone", bomb.getPhone()));
        if (count > 0) {
            throw new BusinessException("号码已录入白名单无法轰炸");
        }

        Config config = configMapper.selectOne(new QueryWrapper<Config>().eq("`key`", "max_time"));
        //判断时间是否正确
        if (bomb.getAttackTime() < 1 || bomb.getAttackTime() > Convert.toInt(config.getValue())) {
            throw new BusinessException("轰炸最小时间为1分钟,最大时间" + config.getValue() + "分钟");
        }


        UserAgentGetter getter = new UserAgentGetter(request);
        //IP地址
        bomb.setRequestIp(getter.getIpAddr());

        if (baseMapper.selectRecentTaskByIp(getter.getIpAddr()) > 10) {
            throw new BusinessException("频率过高请一小时后再试！");
        }

        //浏览器
        bomb.setRequestBrowser(getter.getBrowser());
        //设备名称
        bomb.setRequestDevice(getter.getDevice());
        //创建时间
        bomb.setCreateTime(new DateTime());
        //状态未开始轰炸
        bomb.setIsAttack(0);

        //轰炸请求保存成功
        boolean save = save(bomb);
        if (save) {
            //轰炸逻辑代码
            bomTask.bombTasks();
        }
        return save;
    }

    @Override
    public List<BombTask> listCoverUp() {
        return baseMapper.listCoverUp();
    }

    @Override
    public void updateTaskStatus() {
        baseMapper.updateTaskStatus();
    }
}
