package com.llmoe.bomb.common.config;

import cn.hutool.json.JSONUtil;
import com.llmoe.bomb.common.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败
 *
 * @author llmoe
 * @date 2020/04/04 20:27
 */
@Component("loginFailureHandlerConfig")
public class LoginFailureHandlerConfig extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(JSONUtil.parse(ResultBean.error("登录失败,请检查账号密码是否正确")).toString());
    }
}
