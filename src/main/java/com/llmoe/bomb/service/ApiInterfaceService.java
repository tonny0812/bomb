package com.llmoe.bomb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.entity.ApiInterface;

/**
 * <p>
 * 短信API接口 服务类
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public interface ApiInterfaceService extends IService<ApiInterface> {

    /**
     * 批量删除
     *
     * @param ids ID数组
     * @return 结果
     */
    public boolean batchDelete(String ids);

    /**
     * 运行测试接口
     *
     * @param id 接口ID
     */
    public ResultBean run(Integer id);
}
