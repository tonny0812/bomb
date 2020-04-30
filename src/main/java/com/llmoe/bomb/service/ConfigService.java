package com.llmoe.bomb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llmoe.bomb.entity.Config;

import java.util.Map;

/**
 * <p>
 * 网站配置 服务类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public interface ConfigService extends IService<Config> {

    /**
     * 更新网站配置
     *
     * @param options 更新值
     * @return 结果
     */
    public boolean updateConfig(Map<String, String> options);
}
