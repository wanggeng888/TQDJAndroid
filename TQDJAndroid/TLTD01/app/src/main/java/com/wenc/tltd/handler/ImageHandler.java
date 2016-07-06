package com.wenc.tltd.handler;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by WenC on 2016-06-18.
 */
public class ImageHandler extends Handler {

    public static final int NEWS_TITLE_IMAGE_REFRESH = 800;
    public static final int NEWS_TITLE_IMAGE1 = 601;
    public static final int NEWS_TITLE_IMAGE2 = 602;
    public static final int NEWS_TITLE_IMAGE3 = 603;

    private ImageView imageView1;
    private Bitmap bitmap1;
    private ImageView imageView2;
    private Bitmap bitmap2;
    private ImageView imageView3;
    private Bitmap bitmap3;

    public void setImage1(ImageView imageView, Bitmap bitmap) {
        this.imageView1 = imageView;
        this.bitmap1 = bitmap;
    }

    public void setImage2(ImageView imageView, Bitmap bitmap) {
        this.imageView2 = imageView;
        this.bitmap2 = bitmap;
    }

    public void setImage3(ImageView imageView, Bitmap bitmap) {
        this.imageView3 = imageView;
        this.bitmap3 = bitmap;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case NEWS_TITLE_IMAGE_REFRESH:
                switch (msg.arg1) {
                    case NEWS_TITLE_IMAGE1:
                        imageView1.setImageBitmap(bitmap1);
                        break;
                    case NEWS_TITLE_IMAGE2:
                        imageView2.setImageBitmap(bitmap2);
                        break;
                    case NEWS_TITLE_IMAGE3:
                        imageView3.setImageBitmap(bitmap3);
                        break;
                }
                break;
        }
        super.handleMessage(msg);
    }
}
