package com.llmoe.bomb.common.exception;


import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.utils.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author llmoe
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultBean handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultBean.error("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultBean notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResultBean.error("运行时异常:" + e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultBean.error("服务器错误，请联系管理员:" + e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Object businessException(HttpServletRequest request, BusinessException e) {
        log.error(e.getMessage(), e);
        if (ServletUtils.isAjaxRequest(request)) {
            return ResultBean.error(e.getMessage());
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("error/error");
            return modelAndView;
        }
    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultBean validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResultBean.error(message);
    }
}
