package com.wenc.tltd.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wenc.tltd.R;
import com.wenc.tltd.i.Keyword;

public class SeriesSpeechSubSubTxtActivity extends AppCompatActivity {

    private static final String TAG = "SSActivity";
    private int parentIndex; //父activity传入的值
    private int parentSubIndex; // 二级父activity传入的值
    private int subTitleIndex; // 标题的下标
    private String[] content;//内容数组
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActionBar();
        setContentView(R.layout.page_ss_text);
        initContent();
        if (title != null && !title.isEmpty() && content != null) {
            TextView tvPcTitle = (TextView) findViewById(R.id.tv_ssTitle);
            tvPcTitle.setText(title);
            tvPcTitle.setTextSize(25);
            tvPcTitle.setGravity(Gravity.CENTER);
            tvPcTitle.setTextColor(Color.BLACK);
            // 设置标题布局
            LinearLayout.LayoutParams tvTitleLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvTitleLayoutParams.setMargins(0, 30, 0, 30);
            tvPcTitle.setLayoutParams(tvTitleLayoutParams);
            LinearLayout llPcText = (LinearLayout) findViewById(R.id.ll_ssText);
            for (String con : content) {
                TextView tvCon = new TextView(getApplicationContext());
                tvCon.setText(con);
                tvCon.setTextSize(22);
                tvCon.setTextColor(Color.BLACK);
                tvCon.setLineSpacing(0, 1.5f);
                llPcText.addView(tvCon);
            }
        }
    }

    private void initContent() {
        Intent intent = getIntent();
        String ssSubIndex = intent.getStringExtra(Keyword.SS_SUB_INDEX);
        Log.d(TAG, "ssSubIndex: " + ssSubIndex);
        String[] ssSubIndexs = ssSubIndex.split("_");
        Log.d(TAG, "ssSubIndexs: "+ ssSubIndexs.length);
        if (ssSubIndexs.length == 1) {
            Log.d(TAG, "ssSubIndexs[0]: "+ ssSubIndexs[0]);
            parentIndex = Integer.parseInt(ssSubIndexs[0]);
        }
        if (ssSubIndexs.length == 2) {
            parentIndex = Integer.parseInt(ssSubIndexs[0]);
            parentSubIndex = Integer.parseInt(ssSubIndexs[1]);
        }
        if (ssSubIndexs.length == 3) {
            parentIndex = Integer.parseInt(ssSubIndexs[0]);
            parentSubIndex = Integer.parseInt(ssSubIndexs[1]);
            subTitleIndex = Integer.parseInt(ssSubIndexs[2]);
        }
        setContent();
    }

    private void setContent() {
        Log.d(TAG, parentIndex + "-"+ parentSubIndex + "-" + subTitleIndex);
        switch (parentIndex) {
            // 习近平总书记系列重要讲话读本（2016年版）
            case 0:
                switch (parentSubIndex) {
                    //实现"两个一百年"奋斗目标和中华民族伟大复兴的科学理论指导和行动指南 >>
                    case 0:
                        title = getResources().getString(R.string.xjpSsTitle0);
                        content = getResources().getStringArray(R.array.xjpSs0);
                        break;
                    //一、中华民族近代以来最伟大的梦想——关于实现中华民族伟大复兴的中国梦 >>
                    case 1:
                        switch (subTitleIndex) {
                            //1．中国梦凝聚了几代中国人的夙愿 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle1SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub1);
                                break;
                            //2．中国梦归根到底是人民的梦 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle1SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub2);
                                break;
                            //3．坚持中国道路、弘扬中国精神、凝聚中国力量 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle1SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub3);
                                break;
                            //4．实干才能梦想成真义 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle1SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub4);
                                break;
                            //5．中国梦与世界各国人民的美好梦想相通 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle1SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub5);
                                break;
                        }
                        break;
                    //二、实现中华民族伟大复兴的必由之路——关于坚持和发展中国特色社会主义 >>
                    case 2:
                        switch (subTitleIndex) {
                            //1．中国特色社会主义是历史的结论、人民的选择 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle2Sub1);
                                break;
                            //2．中国特色社会主义由道路、理论体系、制度三位一体构成 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle1Sub2);
                                break;
                            //3．中国特色社会主义是社会主义而不是其他什么主义 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle2Sub3);
                                break;
                            //4．正确认识改革开放前后两个历史时期 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle2Sub4);
                                break;
                            //5．丰富和发展当代中国马克思主义 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle2Sub5);
                                break;
                            //6．继续把中国特色社会主义这篇大文章写下去 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle2SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle2Sub6);
                                break;
                        }
                        break;
                    //三、新的历史条件下治国理政总方略——关于协调推进“四个全面”战略布局 >>
                    case 3:
                        switch (subTitleIndex) {
                            //1．“四个全面”战略布局是时代和实践发展对党和国家工作的新要求 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle3SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle3Sub1);
                                break;
                            //2．“四个全面”战略布局是具有内在逻辑关系的有机统一体 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle3SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle3Sub2);
                                break;
                            //3．“四个全面”战略布局蕴含着科学统筹的思想方法 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle3SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle3Sub3);
                                break;
                            //4．自觉用“四个全面”战略布局统一思想、引领工作 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle3SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle3Sub4);
                                break;
                        }
                        break;
                    //四、奋力实现第一个百年奋斗目标——关于全面建成小康社会 >>
                    case 4:
                        switch (subTitleIndex) {
                            //1．我们党向人民、向历史作出的庄严承诺 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle4SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle4Sub1);
                                break;
                            //2．准确把握全面建成小康社会新的目标要求 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle4SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle4Sub2);
                                break;
                            //3．全面小康是全面发展的小康 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle4SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle4Sub3);
                                break;
                            //4．维护和用好我国发展重要战略机遇期 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle4SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle4Sub4);
                                break;
                            //5．破解制约全面建成小康社会的重点难点问题 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle4SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle4Sub5);
                                break;
                        }
                        break;
                    //五、决定当代中国命运的关键一招——关于全面深化改革 >>
                    case 5:
                        switch (subTitleIndex) {
                            //1．改革是一场深刻革命 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub1);
                                break;
                            //2．把握全面深化改革总要求 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub2);
                                break;
                            //3．推进国家治理体系和治理能力现代化 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub3);
                                break;
                            //4．让人民群众有更多获得感 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub4);
                                break;
                            //5．处理好全面深化改革的重大关系 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub5);
                                break;
                            //6．争当改革的促进派和实干家 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle5SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle5Sub6);
                                break;
                        }
                        break;
                    //六、全力推进法治中国建设——关于全面依法治国 >>
                    case 6:
                        switch (subTitleIndex) {
                            //1．党领导人民治理囯家的基本方略 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub1);
                                break;
                            //2．坚定不移走中国特色社会主义法治道路 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub2);
                                break;
                            //3．建设中国特色社会主义法治体系 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub3);
                                break;
                            //4．维护社会公平正义、司法公正义 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub4);
                                break;
                            //5．在党的领导下依法治国、厉行法治 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub5);
                                break;
                            //6．领导干部要做尊法学法守法用法的模范 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle6SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle6Sub6);
                                break;
                        }
                        break;
                    //七、打铁还需自身硬——关于全面从严治党 >>
                    case 7:
                        switch (subTitleIndex) {
                            //1．中国共产党是中国特色社会主义事业的坚强领导核心 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub1);
                                break;
                            //2．管党治党一刻不能松懈 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub2);
                                break;
                            //3．补足共产党人精神上的“钙” >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub3);
                                break;
                            //4．培养选拔党和人民需要的好干部 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub4);
                                break;
                            //5．作风建设永远在路上 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub5);
                                break;
                            //6．用制度治党、管权、治吏 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub6);
                                break;
                            //7．用铁的纪律维护党的团结统一 >>
                            case 6:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle7);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub7);
                                break;
                            //8．坚持以零容忍态度惩治腐败 >>
                            case 7:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle8);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub8);
                                break;
                            //9．充分调动广大党员干部的积极性、主动性、创造性 >>
                            case 8:
                                title = getResources().getString(R.string.xjpSsTitle7SubTitle9);
                                content = getResources().getStringArray(R.array.xjpSsTitle7Sub9);
                                break;
                        }
                        break;
                    //八、以新发展理念引领发展——关于树立创新、协调、绿色、开放、共享的发展理念 >>
                    case 8:
                        switch (subTitleIndex) {
                            //1．坚持以人民为中心的发展思想 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle8SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle8Sub1);
                                break;
                            //2．关系我国发展全局的一场深刻变革 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle8SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle8Sub2);
                                break;
                            //3．准确把握新发展理念的科学内涵 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle8SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle8Sub3);
                                break;
                            //4．牢固树立和自觉践行新发展理念 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle8SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle8Sub4);
                                break;
                        }
                        break;
                    //九、主动适应、把握、引领经济发展新常态——关于促进经济持续健康发展 >>
                    case 9:
                        switch (subTitleIndex) {
                            //1．准确把握我国经济发展的大逻辑 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub1);
                                break;
                            //2．坚持以提高发展质量和效益为中心 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub2);
                                break;
                            //3．使市场在资源配置中起决定性作用和更好发挥政府作用 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub3);
                                break;
                            //4．加快实施创新驱动发展战略义 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub4);
                                break;
                            //5．推进供给侧结构性改革 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub5);
                                break;
                            //6．始终把“三农”工作牢牢抓在手上 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub6);
                                break;
                            //7．推进以人为核心的新型城镇化 >>
                            case 6:
                                title = getResources().getString(R.string.xjpSsTitle9SubTitle7);
                                content = getResources().getStringArray(R.array.xjpSsTitle9Sub7);
                                break;
                        }
                        break;
                    //十、充分发挥我国社会主义政治制度优越性——关于发展社会主义民主政治 >>
                    case 10:
                        switch (subTitleIndex) {
                            //1．坚持走中国特色社会主义政治发展道路 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub1);
                                break;
                            //2．发展适合我国国情的社会主义政治制度 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub2);
                                break;
                            //3．保证人民当家作主 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub3);
                                break;
                            //4．巩固和发展最广泛的爱国统一战线 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub4);
                                break;
                            //5．在行政体制改革上迈出新步伐义 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub5);
                                break;
                            //6．全面贯彻党的民族政策、宗教政策 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub6);
                                break;
                            //7．丰富“一国两制”实践 >>
                            case 6:
                                title = getResources().getString(R.string.xjpSsTitle10SubTitle7);
                                content = getResources().getStringArray(R.array.xjpSsTitle10Sub7);
                                break;
                        }
                        break;
                    //十一、用社会主义核心价值观凝心聚力——关于建设社会主义文化强国 >>
                    case 11:
                        switch (subTitleIndex) {
                            //1．推动物质文明和精神文明协调发展 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub1);
                                break;
                            //2．培育和践行社会主义核心价值观 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub2);
                                break;
                            //3．牢牢掌握意识形态工作领导权和话语权 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub3);
                                break;
                            //4．坚持以人民为中心的创作导向 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub4);
                                break;
                            //5．传承和弘扬中华优秀传统文化 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub5);
                                break;
                            //6．使网络空间清朗起来 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub6);
                                break;
                            //7．提高国家文化软实力 >>
                            case 6:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle7);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub7);
                                break;
                            //8．讲好中国故事 >>
                            case 7:
                                title = getResources().getString(R.string.xjpSsTitle11SubTitle8);
                                content = getResources().getStringArray(R.array.xjpSsTitle11Sub8);
                                break;
                        }
                        break;
                    //十二、让老百姓过上好日子——关于改善民生和创新社会治理 >>
                    case 12:
                        switch (subTitleIndex) {
                            //1．实现经济发展和民生改善良性循环 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub1);
                                break;
                            //2．抓住人民最关心最直接最现实的利益问题 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub2);
                                break;
                            //3．坚决打赢脱贫攻坚战 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub3);
                                break;
                            //4．维护社会和谐稳定 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub4);
                                break;
                            //5．构建全民共建共享的社会治理格局 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub5);
                                break;
                            //6．坚持总体国家安全观 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle12SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle12Sub6);
                                break;
                        }
                        break;
                    //十三、绿水青山就是金山银山——关于大力推进生态文明建设 >>
                    case 13:
                        switch (subTitleIndex) {
                            //1．像对待生命一样对待生态环境 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle13SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle13Sub1);
                                break;
                            //2．保护生态环境就是保护生产力 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle13SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle13Sub2);
                                break;
                            //3．以系统工程思路抓生态建设 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle13SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle13Sub3);
                                break;
                            //4．实行最严格的生态环境保护制度 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle13SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle13Sub4);
                                break;
                        }
                        break;
                    //十四、建设一支听党指挥能打胜仗作风优良的人民军队——关于全面推进国防和军队建设 >>
                    case 14:
                        switch (subTitleIndex) {
                            //1．牢牢把握党在新形势下的强军目标 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub1);
                                break;
                            //2．坚持以新形势下军事战略方针为统揽 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub2);
                                break;
                            //3．贯彻新的历史条件下政治建军方略 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub3);
                                break;
                            //4．围绕能打仗、打胜仗拓展和深化军事斗争准备 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub4);
                                break;
                            //5．全面实施改革强军战略义 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub5);
                                break;
                            //6．深入推进依法治军、从严治军 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle14SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle14Sub6);
                                break;
                        }
                        break;
                    //十五、推动构建以合作共赢为核心的新型国际关系——关于国际关系和我国外交战略 >>
                    case 15:
                        switch (subTitleIndex) {
                            //1．和平、发展、合作、共赢成为时代潮流 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub1);
                                break;
                            //2．坚定不移走和平发展道路 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub2);
                                break;
                            //3．打造人类命运共同体 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub3);
                                break;
                            //4．积极实施“一带一路”战略 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub4);
                                break;
                            //5．推动与各方关系全面发展 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub5);
                                break;
                            //6．坚决维护国家核心利益 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub6);
                                break;
                            //7．推进全球治理体系变革 >>
                            case 6:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle7);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub7);
                                break;
                            //8．中国开放的大门永远不会关上 >>
                            case 7:
                                title = getResources().getString(R.string.xjpSsTitle15SubTitle8);
                                content = getResources().getStringArray(R.array.xjpSsTitle15Sub8);
                                break;
                        }
                        break;
                    //十六、提高解决改革发展基本问题的本领——关于科学的思想方法和工作方法 >>
                    case 16:
                        switch (subTitleIndex) {
                            //1．学习和掌握马克思主义哲学 >>
                            case 0:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle1);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub1);
                                break;
                            //2．保持战略定力 >>
                            case 1:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle2);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub2);
                                break;
                            //3．提高战略思维、历史思维、辩证思维、创新思维、底线思维能力 >>
                            case 2:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle3);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub3);
                                break;
                            //4．调查研究是一项基本功 >>
                            case 3:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle4);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub4);
                                break;
                            //5．发扬钉钉子精神 >>
                            case 4:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle5);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub5);
                                break;
                            //6．依靠学习走向未来 >>
                            case 5:
                                title = getResources().getString(R.string.xjpSsTitle16SubTitle6);
                                content = getResources().getStringArray(R.array.xjpSsTitle16Sub6);
                                break;
                        }
                        break;

                }
                break;
            //锡党组办字〔2016〕51号
            case 1:
                switch (parentSubIndex) {
                    //关于设立全盟“两学一做”学习教育协调小组的通知
                    case 0:
                        title = getResources().getString(R.string.xdzbSsTitle0);
                        content = getResources().getStringArray(R.array.xdzbSsTitle0Sub);
                        break;
                    //综合组
                    case 1:
                        title = getResources().getString(R.string.xdzbSsTitle1);
                        content = getResources().getStringArray(R.array.xdzbSsTitle1Sub);
                        break;
                    //材料组
                    case 2:
                        title = getResources().getString(R.string.xdzbSsTitle2);
                        content = getResources().getStringArray(R.array.xdzbSsTitle2Sub);
                        break;
                    //宣传组
                    case 3:
                        title = getResources().getString(R.string.xdzbSsTitle3);
                        content = getResources().getStringArray(R.array.xdzbSsTitle3Sub);
                        break;
                    //旗县市（区）督导组
                    case 4:
                        title = getResources().getString(R.string.xdzbSsTitle4);
                        content = getResources().getStringArray(R.array.xdzbSsTitle4Sub);
                        break;
                    //盟直单位督导组
                    case 5:
                        title = getResources().getString(R.string.xdzbSsTitle5);
                        content = getResources().getStringArray(R.array.xdzbSsTitle5Sub);
                        break;
                    //企事业单位督导组
                    case 6:
                        title = getResources().getString(R.string.xdzbSsTitle6);
                        content = getResources().getStringArray(R.array.xdzbSsTitle6Sub);
                        break;
                }
                break;
            //在全盟“两学一做”学习教育工作座谈会上的讲话（2016年4月24日）狄瑞珍
            case 2:
                title = getResources().getString(R.string.drzSsTitle0);
                content = getResources().getStringArray(R.array.drzSsTitle0Sub);
                break;
            //在全盟“两学一做”学习教育工作座谈会上的讲话（2016年4月24日）罗虎在
            case 3:
                title = getResources().getString(R.string.lhzSsTitle0);
                content = getResources().getStringArray(R.array.lhzSsTitle0Sub);
                break;
            //廉洁自律准则
            case 4:
                title = getResources().getString(R.string.ljzlzzSsTitle0);
                content = getResources().getStringArray(R.array.ljzlzzSsTitle0Sub);
                break;
        }
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            View v = LayoutInflater.from(this).inflate(R.layout.actionbar_sub, null);
            TextView tvActionBar = (TextView) v.findViewById(R.id.tv_main_actionbar);
            ImageView ivActionBar = (ImageView) v.findViewById(R.id.back_img);
            tvActionBar.setText(R.string.studySeriesSpeech);
            actionBar.setCustomView(v);
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbarBgColor)));
            ivActionBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
