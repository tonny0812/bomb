package com.llmoe.bomb.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmoe.bomb.common.Constant;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.exception.BusinessException;
import com.llmoe.bomb.common.utils.SendUtil;
import com.llmoe.bomb.entity.ApiInterface;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.mapper.ApiInterfaceMapper;
import com.llmoe.bomb.mapper.ConfigMapper;
import com.llmoe.bomb.service.ApiInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 短信API接口 服务实现类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Service
public class ApiInterfaceServiceImpl extends ServiceImpl<ApiInterfaceMapper, ApiInterface> implements ApiInterfaceService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    @Cacheable(cacheNames = "apiList")
    public List<ApiInterface> list() {
        return super.list();
    }

    @Override
    @CacheEvict(cacheNames = "apiList", allEntries = true)
    public boolean saveOrUpdate(ApiInterface entity) {
        return super.saveOrUpdate(entity);
    }


    @Override
    public boolean batchDelete(String ids) {
        Integer[] array = Convert.toIntArray(ids);
        for (Integer id : array) {
            baseMapper.deleteById(id);
        }
        return true;
    }

    @Override
    public ResultBean run(Integer id) {
        ApiInterface one = baseMapper.selectOne(new QueryWrapper<ApiInterface>().eq("id", id));
        if (one == null) {
            throw new BusinessException("查询不到接口");
        }

        Config testPhone = configMapper.selectOne(new QueryWrapper<Config>().eq("`key`", Constant.TEST_PHONE));
        if (testPhone == null) {
            throw new BusinessException("测试手机号未设置");
        }

        return SendUtil.send(one, testPhone.getValue());
    }

}
