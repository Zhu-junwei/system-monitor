package com.zjw.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 封装通用的返回结果
 *
 * @author 朱俊伟
 * @since 2024/12/04 13:20
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class R {

    private Integer code;    // 状态码
    private String msg;      // 提示消息
    private Object data;     // 返回的数据

    // 成功的静态方法
    public static R success(Object data) {
        return new R(200, "成功", data);  // 200 通常表示 HTTP 成功状态
    }

    // 成功的静态方法（无数据）
    public static R success() {
        return new R(200, "成功", null);
    }

    // 失败的静态方法
    public static R error(Integer code, String msg) {
        return new R(code, msg, null);
    }

    // 失败的静态方法（带默认错误信息）
    public static R error(String msg) {
        return new R(500, msg, null);  // 500 通常表示服务器错误
    }
}
