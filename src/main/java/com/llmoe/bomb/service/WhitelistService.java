package com.llmoe.bomb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llmoe.bomb.entity.Whitelist;

/**
 * <p>
 * 白名单 服务类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public interface WhitelistService extends IService<Whitelist> {

    /**
     * 批量删除
     *
     * @param ids 数组
     * @return 结果
     */
    boolean batchDelete(String ids);

}
