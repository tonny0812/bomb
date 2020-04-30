package com.llmoe.bomb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 网站配置
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * KEY
     */
    @TableField(value = "`key`")
    private String key;

    /**
     * 值
     */
    @TableField(value = "`value`")
    private String value;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", key=" + key +
                ", value=" + value +
                "}";
    }
}
