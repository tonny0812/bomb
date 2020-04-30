package com.llmoe.bomb.common;

import com.llmoe.bomb.common.utils.StringUtils;

import java.util.HashMap;

/**
 * 通用返回类型
 *
 * @author llmoe
 * @date 2020/04/04 20:27
 */
public class ResultBean extends HashMap<String, Object> {

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";
    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";
    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 ResultBean 对象，使其表示一个空消息。
     */
    public ResultBean() {
    }

    /**
     * 初始化一个新创建的 ResultBean 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public ResultBean(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ResultBean 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResultBean(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResultBean success() {
        return ResultBean.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResultBean success(Object data) {
        return ResultBean.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResultBean success(String msg) {
        return ResultBean.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResultBean success(String msg, Object data) {
        return new ResultBean(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultBean warn(String msg) {
        return ResultBean.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultBean warn(String msg, Object data) {
        return new ResultBean(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResultBean error() {
        return ResultBean.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResultBean error(String msg) {
        return ResultBean.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResultBean error(String msg, Object data) {
        return new ResultBean(Type.ERROR, msg, data);
    }


    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),
        /**
         * 错误
         */
        ERROR(500);
        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
}
