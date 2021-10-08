package com.ruoyi.common.core.domain;

import java.util.HashMap;
import com.ruoyi.common.utils.StringUtils;

/**
 * 操作消息提醒
 *
 * @author ruoyi
 */
public class WxLoginResult extends HashMap<String, Object>

{
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "userInfo";

    /** 数据对象 */
    public static final String TOKEN_TAG = "token";

    /**
     * 状态类型
     */
    public enum Type
    {
        /** 成功 */
        SUCCESS(0),
        /** 警告 */
        WARN(301),
        /** 错误 */
        ERROR(500);
        private final int value;

        Type(int value)
        {
            this.value = value;
        }

        public int value()
        {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public WxLoginResult()
    {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     */
    public WxLoginResult(Type type, String msg)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     * @param data 数据对象
     */
    public WxLoginResult(Type type, String msg, Object data)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 初始化一个新创建的 WxLoginResult 对象
     *
     * @param type 状态类型
     * @param msg 返回内容
     * @param data userInfo 用户信息对象
     * @param token wx登陆凭证
     * @Author yang
     */

    public WxLoginResult(Type type, String msg, Object data,String token)
    {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (StringUtils.isNotNull(data)&&StringUtils.isNotNull(token))
        {
            super.put(DATA_TAG, data);
            super.put(TOKEN_TAG,token);
        }
    }

    /**
     * 方便链式调用
     *
     * @param key 键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public WxLoginResult put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static WxLoginResult success()
    {
        return WxLoginResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static WxLoginResult success(Object data)
    {
        return WxLoginResult.success("操作成功", data);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static WxLoginResult success(Object data,String token)
    {
        return WxLoginResult.success("操作成功", data,token);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static WxLoginResult success(String msg)
    {
        return WxLoginResult.success(msg, null,null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static WxLoginResult success(String msg, Object data)
    {
        return new WxLoginResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回成功消息
     *
     * @param msg
     * @param data
     * @param token  wx登陆凭证
     * @return  成功消息
     * @Author yang
     */

    public static WxLoginResult success(String msg, Object data,String token)
    {
        return new WxLoginResult(Type.SUCCESS, msg, data,token);
    }



    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static WxLoginResult warn(String msg)
    {
        return WxLoginResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static WxLoginResult warn(String msg, Object data)
    {
        return new WxLoginResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static WxLoginResult error()
    {
        return WxLoginResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static WxLoginResult error(String msg)
    {
        return WxLoginResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static WxLoginResult error(String msg, Object data)
    {
        return new WxLoginResult(Type.ERROR, msg, data);
    }
}
