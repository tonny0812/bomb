package com.llmoe.bomb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 轰炸请求
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public class BombTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 请求人名称
     */
    private String name;

    /**
     * 攻击时间(分钟)
     */
    private Integer attackTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 攻击号码
     */
    private String phone;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 是否完成 0 未开始 1 进行中 2 已完成
     */
    private Integer isAttack;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 请求设备
     */
    private String requestDevice;

    /**
     * 请求浏览器
     */
    private String requestBrowser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotBlank(message = "名称不能为空")
    @Size(min = 0, max = 30, message = "名称不能超过30个字符")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "时间不能为空")
    public Integer getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(Integer attackTime) {
        this.attackTime = attackTime;
    }

    public String getRequestDevice() {
        return requestDevice;
    }

    public void setRequestDevice(String requestDevice) {
        this.requestDevice = requestDevice;
    }

    public String getRequestBrowser() {
        return requestBrowser;
    }

    public void setRequestBrowser(String requestBrowser) {
        this.requestBrowser = requestBrowser;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "不是正确的手机号")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getIsAttack() {
        return isAttack;
    }

    public void setIsAttack(Integer isAttack) {
        this.isAttack = isAttack;
    }

    @Override
    public String toString() {
        return "BombTask{" +
                "id=" + id +
                ", name=" + name +
                ", attackTime=" + attackTime +
                ", createTime=" + createTime +
                ", phone=" + phone +
                ", startTime=" + startTime +
                ", isAttack=" + isAttack +
                "}";
    }
}
