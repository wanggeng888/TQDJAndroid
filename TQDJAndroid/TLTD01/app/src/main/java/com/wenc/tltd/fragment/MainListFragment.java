package com.wenc.tltd.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.DisciplinaryRegulationsActivity;
import com.wenc.tltd.activity.ExamRecordActivity;
import com.wenc.tltd.activity.ExamSubjectActivity;
import com.wenc.tltd.activity.NewsActivity;
import com.wenc.tltd.activity.PartyConstitutionActivity;
import com.wenc.tltd.activity.SeriesSpeechActivity;
import com.wenc.tltd.activity.TLTDActivity;
import com.wenc.tltd.adapter.CarouselAdapter;
import com.wenc.tltd.adapter.NewsListAdapter;
import com.wenc.tltd.entity.Carousel;
import com.wenc.tltd.service.NewsService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainListFragment extends Fragment implements View.OnClickListener {

    private static MainListFragment fragment;
    private SwipeRefreshLayout refreshLayout;

    public MainListFragment() {
    }

    public static MainListFragment newInstance() {
        if (fragment == null) {
            fragment = new MainListFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_home, container, false);
        view.setOnClickListener(this);
        LinearLayout llStudyPc = (LinearLayout) view.findViewById(R.id.main_study_pc);
        LinearLayout llStudySs = (LinearLayout) view.findViewById(R.id.main_study_ss);
        LinearLayout llStudyDr = (LinearLayout) view.findViewById(R.id.main_study_dr);
        LinearLayout llStudyTltd = (LinearLayout) view.findViewById(R.id.main_study_tltd);
        LinearLayout llRecord = (LinearLayout) view.findViewById(R.id.main_tltd_record);
        LinearLayout llSubject = (LinearLayout) view.findViewById(R.id.main_exam_subject);
        TextView tvMoreNews = (TextView) view.findViewById(R.id.tv_news_more);
        // 轮播图
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.page_main_vp_carousel);

        llStudyPc.setOnClickListener(this);
        llStudySs.setOnClickListener(this);
        llStudyDr.setOnClickListener(this);
        llStudyTltd.setOnClickListener(this);
        llRecord.setOnClickListener(this);
        llSubject.setOnClickListener(this);
        tvMoreNews.setOnClickListener(this);
        final ListView newsListView = (ListView) view.findViewById(R.id.page_main_news_list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.page_main_refresh);
        NewsService newsService = new NewsService(getActivity(), getActivity().getApplicationContext(), newsListView);
        newsService.newsMainList("1", refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity().getApplicationContext());
                NewsService newsService = new NewsService(getActivity(), getActivity().getApplicationContext(), newsListView);
                newsService.newsMainList("1", refreshLayout);
                initViewPager(viewPager);
            }
        });
        initViewPager(viewPager);
        return view;
    }

    private void initViewPager(ViewPager viewPager) {
        NewsService newsService = new NewsService(getActivity(), getActivity().getApplicationContext(), null);
        newsService.carousel(viewPager, refreshLayout);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.main_study_pc:
                intent.setClass(getActivity(), PartyConstitutionActivity.class);
                break;
            case R.id.main_study_ss:
                intent.setClass(getActivity(), SeriesSpeechActivity.class);
                break;
            case R.id.main_study_dr:
                intent.setClass(getActivity(), DisciplinaryRegulationsActivity.class);
                break;
            case R.id.main_study_tltd:
                intent.setClass(getActivity(), TLTDActivity.class);
                break;
            case R.id.main_tltd_record:
                intent.setClass(getActivity(), ExamRecordActivity.class);
                break;
            case R.id.main_exam_subject:
                intent.setClass(getActivity(), ExamSubjectActivity.class);
                break;
            case R.id.tv_news_more:
                intent.setClass(getActivity(), NewsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
