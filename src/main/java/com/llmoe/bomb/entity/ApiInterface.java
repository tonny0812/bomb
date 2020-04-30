package com.llmoe.bomb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 短信API接口
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public class ApiInterface implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * APi名称
     */
    private String name;

    /**
     * API地址
     */
    private String url;

    /**
     * 请求方式
     */
    private String type;

    /**
     * cookie
     */
    private String cookie;

    /**
     * 请求参数JSON格式
     */
    private String parm;

    /**
     * 请求头
     */
    private String headers;


    /**
     * API状态 0启用 1暂停
     */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getParm() {
        return parm;
    }

    public void setParm(String parm) {
        this.parm = parm;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApiInterface{" +
                "id=" + id +
                ", name=" + name +
                ", url=" + url +
                ", type=" + type +
                ", cookie=" + cookie +
                ", parm=" + parm +
                ", headers=" + headers +
                ", status=" + status +
                "}";
    }
}
