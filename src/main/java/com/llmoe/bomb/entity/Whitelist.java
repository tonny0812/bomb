package com.llmoe.bomb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 白名单
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public class Whitelist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "不是正确的手机号")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Whitelist{" +
                "id=" + id +
                ", phone=" + phone +
                ", createTime=" + createTime +
                "}";
    }
}
