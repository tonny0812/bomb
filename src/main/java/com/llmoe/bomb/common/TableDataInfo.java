package com.llmoe.bomb.common;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author llmoe
 */
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long count;

    /**
     * 列表数据
     */
    private List<?> data;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private int msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     *
     * @param data  列表数据
     * @param count 总记录数
     */
    public TableDataInfo(List<?> data, int count) {
        this.data = data;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }
}
