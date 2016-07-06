package com.wenc.tltd.utils.message;

import android.os.Handler;
import android.os.Message;

/**
 * Created by WenC on 2016-06-07.
 */
public class SendHandlerMessage {

    public static void sendMessage(final Handler handler, final Message msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendMessage(msg);
            }
        }).start();
    }

    public static void sendMessage(final Handler handler, final int what) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = what;
                handler.sendMessage(msg);
            }
        }).start();
    }

    public static void sendMessage(final Handler handler, final int what, final int arg1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = what;
                msg.arg1 = arg1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    public static void sendMessage(final Handler handler, final int what, final int arg1, final int arg2) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = what;
                msg.arg1 = arg1;
                msg.arg2 = arg2;
                handler.sendMessage(msg);
            }
        }).start();
    }

}
