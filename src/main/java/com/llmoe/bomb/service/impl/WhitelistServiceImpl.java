package com.llmoe.bomb.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llmoe.bomb.entity.Whitelist;
import com.llmoe.bomb.mapper.WhitelistMapper;
import com.llmoe.bomb.service.WhitelistService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 白名单 服务实现类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Service
public class WhitelistServiceImpl extends ServiceImpl<WhitelistMapper, Whitelist> implements WhitelistService {

    @Override
    public boolean batchDelete(String ids) {
        Integer[] array = Convert.toIntArray(ids);
        for (Integer id : array) {
            baseMapper.deleteById(id);
        }
        return true;
    }

}
