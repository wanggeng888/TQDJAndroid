package com.wenc.tltd.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.NewsInfoActivity;
import com.wenc.tltd.adapter.CarouselAdapter;
import com.wenc.tltd.adapter.NewsListAdapter;
import com.wenc.tltd.entity.Carousel;
import com.wenc.tltd.entity.News;
import com.wenc.tltd.i.IMessageCode;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.i.WebUrl;
import com.wenc.tltd.utils.layout.CommonLayout;
import com.wenc.tltd.utils.request.RequestImage;
import com.wenc.tltd.utils.request.RequestThread;
import com.wenc.tltd.utils.message.SendHandlerMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WenC on 2016-06-11.
 */
public class NewsService implements Keyword, WebUrl, IMessageCode {

    private static final String LOG = "NewsService";
    private static final int NEWS = 100;
    private static final int NEWS_ADAPTER = 200;
    private static final int NEWS_INFO = 300;
    private static final int NEWS_INFO_TEXT = 301;
    private static final int NEWS_INFO_IMG = 302;
    private static final int CLOSE_REFRESH = 400;
    private static final int NEWS_MAIN_LIST_ADAPTER = 500;
    private static final int NEWS_TITLE_IMAGE = 600;
    private static final int NEWS_TITLE_IMAGE1 = 601;
    private static final int NEWS_TITLE_IMAGE2 = 602;
    private static final int NEWS_TITLE_IMAGE3 = 603;
    private static final int NEWS_TITLE_IMAGE_ADD = 700;

    private static boolean isLoading = false; // 是否正在刷新
    private Activity mActivity;
    private Context mContext;
    private ListView mListView;
    private List<News> newsList;
    private NewsListAdapter mNewsListAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean isSetAdapter = false;
    private TextView tvTitle;
    private TextView tvText;
    private TextView tvTime;
    private ImageView ivImage;
    private String title;
    private String text;
    private String time;
    private Bitmap mBitmap;
    private ViewPager viewPager;
    private List<Carousel> carouselList;
    private CarouselAdapter adapter; // 轮播图
    private boolean direction = true;// 轮播方向

    public NewsService(Context context) {
        this.mContext = context;
    }

    public NewsService(Context context, ListView listView) {
        this.mContext = context;
        this.mListView = listView;
    }

    public NewsService(Activity activity, Context context, ListView listView) {
        this.mActivity = activity;
        this.mContext = context;
        this.mListView = listView;
    }

    public NewsService(Context context, ListView listView, NewsListAdapter newsListAdapter) {
        this.mContext = context;
        this.mListView = listView;
        this.mNewsListAdapter = newsListAdapter;
    }

    public NewsService(Activity activity, Context context, ListView listView, NewsListAdapter newsListAdapter) {
        this.mActivity = activity;
        this.mContext = context;
        this.mListView = listView;
        this.mNewsListAdapter = newsListAdapter;
    }

    public void newsInfo(String id, TextView tvTitle, TextView tvTime, TextView tvText, ImageView ivImage, boolean isCarousel) {
        this.tvTitle = tvTitle;
        this.tvTime = tvTime;
        this.tvText = tvText;
        this.ivImage = ivImage;
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ID, id);
            String data = Base64.encodeToString(Base64.encodeToString(URLEncoder.encode(jsonObject.toString(), "utf-8").getBytes(), Base64.DEFAULT).getBytes(), Base64.DEFAULT);
            Map<String, String> map = new HashMap<String, String>();
            map.put(USERNAME, username);
            map.put(TOKEN, token);
            map.put(DATA, data);
            String src = null;
            if (isCarousel) {
                src = NEWS_TITLE_INFO_URL;
            } else {
                src = NEWS_INFO_URL;
            }
            new Thread(new RequestThread(src, map) {
                @Override
                public void doResult(JSONObject result) {
                    if (result != null) {
                        try {
                            String code = result.getString(CODE);
                            if (SUCCESS.equals(code)) {
                                JSONObject jsonObj = result.getJSONObject(DATA);
                                Log.d(LOG, "---->> jsonObj: " + jsonObj.toString());
                                title = jsonObj.getString("title");
                                time = jsonObj.getString("time");
                                text = jsonObj.getString("text");
                                String imgSrc = jsonObj.getString("src");
                                if (imgSrc != null && !imgSrc.isEmpty()) {
                                    new RequestImage(imgSrc) {
                                        @Override
                                        public void setImage(Bitmap bitmap) {
                                            mBitmap = bitmap;
                                            Log.d(LOG, "---->> mBitmap: " + mBitmap);
                                            SendHandlerMessage.sendMessage(handler, NEWS_INFO, NEWS_INFO_IMG);
                                        }
                                    }.run();
                                } else {
                                    SendHandlerMessage.sendMessage(handler, NEWS_INFO, NEWS_INFO_IMG);
                                }
                                SendHandlerMessage.sendMessage(handler, NEWS_INFO, NEWS_INFO_TEXT);
                            } else if (ERROR.equals(code)) {
                                String message = result.getString(MESSAGE);
                                SendHandlerMessage.sendMessage(handler, NEWS, Integer.parseInt(message));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                        }
                    } else {
                        SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
        }
    }

    public void newsList(String currentPage, final SwipeRefreshLayout refreshLayout) {
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(CURRENT_PAGE, currentPage);
            String data = Base64.encodeToString(Base64.encodeToString(URLEncoder.encode(jsonObject.toString(), "utf-8").getBytes(), Base64.DEFAULT).getBytes(), Base64.DEFAULT);
            Map<String, String> map = new HashMap<String, String>();
            map.put(USERNAME, username);
            map.put(TOKEN, token);
            map.put(DATA, data);
            new Thread(new RequestThread(NEWS_URL, map) {
                @Override
                public void doResult(JSONObject result) {
                    if (result != null) {
                        try {
                            Log.d(LOG, "---->> result: " + result.toString());
                            String code = result.getString(CODE);
                            if (SUCCESS.equals(code)) {
                                JSONArray jsonArray = result.getJSONArray(DATA);
                                newsList = new ArrayList<News>();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                                    String id = jsonObj.getString(ID);
                                    String title = jsonObj.getString(NEWS_TITLE);
                                    String lead = jsonObj.getString(NEWS_LEAD);
                                    String time = jsonObj.getString(NEWS_TIME);
                                    String readCount = jsonObj.getString("readcount");
                                    News news = new News();
                                    news.setId(Integer.parseInt(id));
                                    news.setTitle(title);
                                    news.setLead("null".equals(lead) ? null : lead);
                                    news.setTime(time);
                                    news.setReadCount(Integer.parseInt(readCount));
                                    newsList.add(news);
                                }
                                if (refreshLayout != null) {
                                    mRefreshLayout = refreshLayout;
                                    SendHandlerMessage.sendMessage(handler, CLOSE_REFRESH);
                                }
                                SendHandlerMessage.sendMessage(handler, NEWS_ADAPTER);
                            } else if (ERROR.equals(code)) {
                                String message = result.getString(MESSAGE);
                                SendHandlerMessage.sendMessage(handler, NEWS, Integer.parseInt(message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                        }
                    } else {
                        SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
        }
    }

    /**
     * 主界面的新闻列表
     *
     * @param currentPage
     * @param refreshLayout
     */
    public void newsMainList(String currentPage, final SwipeRefreshLayout refreshLayout) {
        SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(CURRENT_PAGE, currentPage);
            String data = Base64.encodeToString(Base64.encodeToString(URLEncoder.encode(jsonObject.toString(), "utf-8").getBytes(), Base64.DEFAULT).getBytes(), Base64.DEFAULT);
            Map<String, String> map = new HashMap<String, String>();
            map.put(USERNAME, username);
            map.put(TOKEN, token);
            map.put(DATA, data);
            new Thread(new RequestThread(NEWS_URL, map) {
                @Override
                public void doResult(JSONObject result) {
                    if (result != null) {
                        try {
                            Log.d(LOG, "---->> result: " + result.toString());
                            String code = result.getString(CODE);
                            if (SUCCESS.equals(code)) {
                                JSONArray jsonArray = result.getJSONArray(DATA);
                                newsList = new ArrayList<News>();
                                int listSize = jsonArray.length() < 3 ? jsonArray.length() : 3;
                                Log.d(LOG, "---->> listSize: " + listSize);
                                for (int i = 0; i < listSize; i++) {
                                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                                    String id = jsonObj.getString(ID);
                                    String title = jsonObj.getString(NEWS_TITLE);
                                    String lead = jsonObj.getString(NEWS_LEAD);
                                    String time = jsonObj.getString(NEWS_TIME);
                                    String readCount = jsonObj.getString("readcount");
                                    News news = new News();
                                    news.setId(Integer.parseInt(id));
                                    news.setTitle(title);
                                    news.setLead("null".equals(lead) ? null : lead);
                                    news.setTime(time);
                                    news.setReadCount(Integer.parseInt(readCount));
                                    newsList.add(news);
                                }
                                if (refreshLayout != null) {
                                    mRefreshLayout = refreshLayout;
                                    SendHandlerMessage.sendMessage(handler, CLOSE_REFRESH);
                                }
                                SendHandlerMessage.sendMessage(handler, NEWS_MAIN_LIST_ADAPTER);
                            } else if (ERROR.equals(code)) {
                                String message = result.getString(MESSAGE);
                                SendHandlerMessage.sendMessage(handler, NEWS, Integer.parseInt(message));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                        }
                    } else {
                        SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
        }
    }

    private void setMainPageListView(List<News> newses) {
        NewsListAdapter newsListAdapter = new NewsListAdapter(mContext);
        newsListAdapter.setNewList(newses);
        mListView.setAdapter(newsListAdapter);
        CommonLayout.setListViewHeightBasedOnChildren(mListView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(ID, (int) id);
                intent.setClass(mActivity, NewsInfoActivity.class);
                mActivity.startActivity(intent);
            }
        });
    }

    private void setListView(List<News> newses) {
        NewsListAdapter newsListAdapter = newsAdapter(newses);
        mListView.setAdapter(newsListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(ID, (int) id);
                intent.setClass(mActivity, NewsInfoActivity.class);
                mActivity.startActivity(intent);
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            if (view.getCount() % PAGE_SIZE == 0) {
                                if (!isLoading) {
                                    newsList(String.valueOf(view.getCount() / PAGE_SIZE + 1), null);
                                }
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        isSetAdapter = true;
    }

    private void addListView(List<News> newses) {
        if (newses != null && !newses.isEmpty()) {
            NewsListAdapter newsListAdapter = newsAdapter(newses);
            newsListAdapter.notifyDataSetChanged();
        }
    }

    private NewsListAdapter newsAdapter(List<News> newses) {
        mNewsListAdapter.setNewList(newses);
        return mNewsListAdapter;
    }

    public void carousel(final ViewPager viewPager, final SwipeRefreshLayout refreshLayout) {
        this.viewPager = viewPager;
        final SharedPreferences sp = mContext.getSharedPreferences(BCONF, Activity.MODE_PRIVATE);
        String username = sp.getString(USERNAME, "");
        String token = sp.getString(TOKEN, "");
        Map<String, String> params = new HashMap<String, String>();
        params.put(USERNAME, username);
        params.put(TOKEN, token);
        new Thread(new RequestThread(NEWS_TITLE_UTL, params) {
            @Override
            public void doResult(JSONObject result) {
                if (result != null) {
                    try {
                        String code = result.getString(CODE);
                        if (SUCCESS.equals(code)) {
                            JSONArray jsonArray = result.getJSONArray(DATA);
                            Log.d(LOG, "---->> jsonArray: " + jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String id = jsonArray.getJSONObject(i).getString("id");
                                String imgSrc = jsonArray.getJSONObject(i).getString("src");
                                if (carouselList == null) {
                                    carouselList = new ArrayList<Carousel>();
                                }
                                Carousel carousel = new Carousel(Integer.parseInt(id), imgSrc);
                                carouselList.add(carousel);
                            }
                            if (refreshLayout != null) {
                                mRefreshLayout = refreshLayout;
                                SendHandlerMessage.sendMessage(handler, CLOSE_REFRESH);
                            }
                            SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE_ADD);
                        } else if (ERROR.equals(code)) {
                            String message = result.getString(MESSAGE);
                            SendHandlerMessage.sendMessage(handler, NEWS, Integer.parseInt(message));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                    }
                } else {
                    SendHandlerMessage.sendMessage(handler, NEWS, REQUEST_ERROR_PARAMS_ERROR);
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS:
                    switch (msg.arg1) {
                        case REQUEST_ERROR_PARAMS_ERROR:
                            Toast.makeText(mContext, R.string.requestErrorParamsError, Toast.LENGTH_SHORT).show();
                            if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.setRefreshing(false);
                            }
                            break;
                        case VERIFY_ERROR_TOKEN_ERROR:
                            Toast.makeText(mContext, R.string.tokenVerifyError, Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
                case NEWS_ADAPTER:
                    if (!isSetAdapter) {
                        setListView(newsList);
                    } else {
                        addListView(newsList);
                    }
                    isLoading = false;
                    break;
                case NEWS_MAIN_LIST_ADAPTER:
                    setMainPageListView(newsList);
                    break;
                case NEWS_INFO:
                    switch (msg.arg1) {
                        case NEWS_INFO_TEXT:
                            tvTitle.setText(title);
                            tvTime.setText(time);
                            tvText.setText(text);
                            break;
                        case NEWS_INFO_IMG:
                            if (mBitmap != null) {
                                ivImage.setImageBitmap(mBitmap);
                            } else {
                                ivImage.setImageResource(R.mipmap.page_main_logo);
                            }
                            break;
                    }
                    break;
                case CLOSE_REFRESH:
                    mRefreshLayout.setRefreshing(false);
                    break;
                case NEWS_TITLE_IMAGE:
                    switch (msg.arg1) {
                        case NEWS_TITLE_IMAGE1:
                            adapter.setCurrentPosition(0);
                            viewPager.setCurrentItem(0, true);
                            break;
                        case NEWS_TITLE_IMAGE2:
                            adapter.setCurrentPosition(1);
                            viewPager.setCurrentItem(1, true);
                            break;
                        case NEWS_TITLE_IMAGE3:
                            adapter.setCurrentPosition(2);
                            viewPager.setCurrentItem(2, true);
                            break;
                    }
                    break;
                case NEWS_TITLE_IMAGE_ADD:
                    adapter = new CarouselAdapter(mActivity, carouselList);
                    viewPager.setAdapter(adapter);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            int currentPosition = adapter.getCurrentPosition();
                            switch (currentPosition) {
                                case 0:
                                    SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE, NEWS_TITLE_IMAGE2);
                                    direction = true;
                                    break;
                                case 1:
                                    SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE);
                                    if (direction) {
                                        SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE, NEWS_TITLE_IMAGE3);
                                    } else {
                                        SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE, NEWS_TITLE_IMAGE1);
                                    }
                                    break;
                                case 2:
                                    SendHandlerMessage.sendMessage(handler, NEWS_TITLE_IMAGE, NEWS_TITLE_IMAGE2);
                                    direction = false;
                                    break;
                            }
                        }
                    }, 5000, 5000);
                    break;

            }
            super.handleMessage(msg);
        }
    };

}
