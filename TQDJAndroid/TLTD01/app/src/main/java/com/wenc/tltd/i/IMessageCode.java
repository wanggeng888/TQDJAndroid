package com.wenc.tltd.i;

/**
 * Created by WenC on 2016-06-07.
 */
public interface IMessageCode {

    public static final int REQUEST_ERROR_PARAMS_ERROR = -1; //请求参数错误

    public static final int REGIST_ERROR_USERNAME_EXIST = 1001; // 用户名已存在

    public static final int REGIST_ERROR_PASSWORD_LENGTH_ILLEGAL = 1002; // 密码长度不合法

    public static final int REGIST_ERROR_USERNAME_ILLEGAL = 1003; // 用户名不合法

    public static final int LOGIN_ERROR_USERNAME_PASSWORD_UNMATCHED = 2001; // 用户名密码错误

    public static final int LOGIN_ERROR_TOKEN_ERROR = 2002; // token错误

    public static final int VERIFY_ERROR_TOKEN_ERROR = 3001; // token验证失败

    public static final int EXAM_SROCE_ERROR = 7001; //成绩计算异常
}
