package com.wenc.tltd.i;

/**
 * Created by WenC on 2016-05-26.
 */
public interface WebUrl {

    public static final String REGIST_URL = "/acct/01"; //注册

    public static final String SIGNIN_URL = "/acct/02"; // 登录

    public static final String SIGNOUT_URL = "/acct/03"; // 注销

    public static final String AUTO_SIGN_URL = "/acct/05"; // 自动登录

    public static final String NEWS_URL = "/news/01"; //新闻列表

    public static final String NEWS_INFO_URL = "/news/02"; //新闻详情

    public static final String NEWS_TITLE_UTL = "/carousel/01"; // 新闻标题

    public static final String NEWS_TITLE_INFO_URL = "/carousel/02"; //新闻详情

    public static final String EXAM_UPDATE_URL = "/exam/01"; // 更新考试

    public static final String EXAM_COMMIT = "/exam/02"; // 提交成绩

    public static final String EXAM_RECORD = "/exam/08"; // 考试成绩

}
