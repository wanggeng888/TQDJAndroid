package com.wenc.tltd.utils.request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by WenC on 2016-05-26.
 */
public class HttpRequest {

    private static final String WEB_MAIN_URL = "http://122.114.52.84:8080/Management";
    private static final String TAG = "HttpRequest";
    public static final String POST = "POST";
    private static HttpRequest request;
    private BufferedReader br = null;
    private OutputStream os = null;

    private HttpRequest() {
    }

    /**
     * 获得实例
     *
     * @return
     */
    public static HttpRequest getInstance() {
        if (request == null) {
            request = new HttpRequest();
        }
        return request;
    }

    /**
     * 发送请求
     *
     * @param params
     * @return
     */
    public JSONObject doRequest(String webUrl, Map<String, String> params) {
        JSONObject result = null;
        try {
            HttpURLConnection connection = getConnection(webUrl);
            request(connection, params);
            result = response(connection);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                closeStream();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获得连接
     *
     * @return
     * @throws IOException
     */
    private HttpURLConnection getConnection(String webUrl) throws IOException {
        URL url = new URL(WEB_MAIN_URL + webUrl);
        Log.d(TAG, "-->> webUrl: " + (WEB_MAIN_URL + webUrl));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod(POST);
        connection.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        connection.setConnectTimeout(10000); // 5s连接超时
        connection.setReadTimeout(20000); // 10s读取超时
        return connection;
    }

    /**
     * 发送请求参数
     *
     * @param connection
     * @param params
     * @throws IOException
     */
    private void request(HttpURLConnection connection, Map<String, String> params) throws IOException {
        os = connection.getOutputStream();
        JSONObject jsonObject = new JSONObject(params);
        os.write(URLEncoder.encode(jsonObject.toString(), "UTF-8").getBytes("UTF-8"));
        os.flush();
    }

    /**
     * 获得响应
     *
     * @param connection
     * @return
     * @throws IOException
     * @throws JSONException
     */
    private JSONObject response(HttpURLConnection connection) throws IOException, JSONException {
        br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String readLine = null;
        while ((readLine = br.readLine()) != null) {
            sb.append(readLine);
        }
        return new JSONObject(sb.toString());
    }

    /**
     * 关闭流
     *
     * @throws IOException
     */
    private void closeStream() throws IOException {
        if (br != null) {
            br.close();
        }
        if (os != null) {
            os.close();
        }
    }


}
