package com.wenc.tltd.utils.request;

import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by WenC on 2016-05-26.
 */
public class HttpRequestImage {

    private static final String TAG = "HttpRequestImage";
    public static final String GET = "GET";
    private static HttpRequestImage request;

    private HttpRequestImage() {
    }

    /**
     * 获得实例
     *
     * @return
     */
    public static HttpRequestImage getInstance() {
        if (request == null) {
            request = new HttpRequestImage();
        }
        return request;
    }

    /**
     * 发送请求
     *
     * @param params
     * @return
     */
    public InputStream doRequest(String webUrl) {
        Log.d(TAG, "---->> webUrl: "+ webUrl);
        InputStream is = null;
        try {
            HttpURLConnection connection = getConnection(webUrl);
            is = response(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 获得连接
     *
     * @return
     * @throws IOException
     */
    private HttpURLConnection getConnection(String webUrl) throws IOException {
        URL url = new URL(webUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod(GET);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.setConnectTimeout(5000); // 5s连接超时
        connection.setReadTimeout(10000); // 10s读取超时
        return connection;
    }

    /**
     * 获得响应
     *
     * @param connection
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private InputStream response(HttpURLConnection connection) throws IOException, JSONException {
        return connection.getInputStream();
    }

}
