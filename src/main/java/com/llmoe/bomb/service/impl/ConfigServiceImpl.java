package com.llmoe.bomb.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.mapper.ConfigMapper;
import com.llmoe.bomb.service.ConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 网站配置 服务实现类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {


    @Override
    @Cacheable(cacheNames = "config")
    public List<Config> list() {
        return super.list();
    }

    @Override
    @CacheEvict(cacheNames = "config", allEntries = true)
    public boolean updateConfig(Map<String, String> options) {
        if (null == options || options.isEmpty()) {
            return false;
        }
        Config config = null;
        Set<Map.Entry<String, String>> entrySet = options.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            config = new Config();
            config.setValue(entry.getValue());
            this.update(config, new UpdateWrapper<Config>().eq("`key`", entry.getKey()));
        }
        return true;
    }
}
