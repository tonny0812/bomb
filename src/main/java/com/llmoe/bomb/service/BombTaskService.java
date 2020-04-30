package com.llmoe.bomb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llmoe.bomb.entity.BombTask;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 轰炸请求 服务类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public interface BombTaskService extends IService<BombTask> {

    /**
     * 批量删除
     *
     * @param ids ID数组
     * @return 结果
     */
    boolean batchDelete(String ids);

    /**
     * 轰炸请求
     *
     * @param bomb 轰炸
     * @return 结果
     */
    boolean startBombing(BombTask bomb, HttpServletRequest request);

    /**
     * 查询轰炸记录 手机号带掩码
     *
     * @return 集合
     */
    List<BombTask> listCoverUp();

    /**
     * 更新任务状态
     *
     */
    void updateTaskStatus();
}
