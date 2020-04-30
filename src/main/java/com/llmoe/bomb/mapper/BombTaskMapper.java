package com.llmoe.bomb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.llmoe.bomb.entity.BombTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 轰炸请求 Mapper 接口
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public interface BombTaskMapper extends BaseMapper<BombTask> {

    /**
     * 查询轰炸记录 手机号带掩码
     *
     * @return 集合
     */
    List<BombTask> listCoverUp();

    /**
     * 更新任务状态
     *
     * @return 结果
     */
    int updateTaskStatus();

    /**
     * 查询这个IP最近一小时的轰炸任务
     *
     * @param ipAddr IP地址
     * @return 结果
     */
    int selectRecentTaskByIp(@Param("ipAddr") String ipAddr);
}
