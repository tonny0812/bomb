package com.llmoe.bomb.common.config;

import cn.hutool.json.JSONUtil;
import com.llmoe.bomb.common.ResultBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功
 *
 * @author llmoe
 * @date 2020/04/04 20:27
 */

@Component("loginSuccessHandlerConfig")
public class LoginSuccessHandlerConfig extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append(JSONUtil.parse(ResultBean.success("登录成功")).toString());
    }
}
