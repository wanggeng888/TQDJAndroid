package com.wenc.tltd.utils.request;

import android.widget.Button;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by WenC on 2016-06-07.
 */
public abstract class RequestThread implements Runnable {

    private Map<String, String> mParams;
    private String mWebUrl;
    private Button mLockBtn; //要锁定的按钮

    public RequestThread(String webUrl, Map<String, String> params) {
        this.mParams = params;
        this.mWebUrl = webUrl;
    }

    public RequestThread(String webUrl, Map<String, String> params, Button lockBtn) {
        this.mParams = params;
        this.mWebUrl = webUrl;
        this.mLockBtn = lockBtn;
    }

    @Override
    public void run() {
        if (mLockBtn != null) {
            mLockBtn.setClickable(false);
        }
        JSONObject result = HttpRequest.getInstance().doRequest(mWebUrl, mParams);
        doResult(result);
        if (mLockBtn != null) {
            mLockBtn.setClickable(true);
        }
    }

    public abstract void doResult(JSONObject result);


}
