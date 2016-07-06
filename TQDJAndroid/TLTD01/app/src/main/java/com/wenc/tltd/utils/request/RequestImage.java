package com.wenc.tltd.utils.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by WenC on 2016-06-07.
 */
public abstract class RequestImage {

    private String mWebUrl;

    public RequestImage(String webUrl) {
        this.mWebUrl = webUrl;
    }

    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = HttpRequestImage.getInstance().doRequest(mWebUrl);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                setImage(bitmap);
                try {
                    if(is!= null){
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public abstract void setImage(Bitmap bitmap);

}
