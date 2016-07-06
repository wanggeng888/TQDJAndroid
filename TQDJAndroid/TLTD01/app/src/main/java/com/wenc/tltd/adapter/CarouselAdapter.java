package com.wenc.tltd.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wenc.tltd.R;
import com.wenc.tltd.activity.CarouselActivity;
import com.wenc.tltd.entity.Carousel;
import com.wenc.tltd.handler.ImageHandler;
import com.wenc.tltd.i.Keyword;
import com.wenc.tltd.utils.message.SendHandlerMessage;
import com.wenc.tltd.utils.request.RequestImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WenC on 2016-06-17.
 */
public class CarouselAdapter extends PagerAdapter {

    private int currentPosition = 0;
    private Activity mActivity;
    private List<Carousel> carouselList;
    private List<ImageView> imageViewList;

    public CarouselAdapter(Activity activity, List<Carousel> carouselList) {
        this.mActivity = activity;
        this.carouselList = carouselList;
        this.imageViewList = new ArrayList<ImageView>();
        final ImageHandler imageHandler = new ImageHandler();
        for (int i = 0; i < carouselList.size(); i++) {
            final int position = i;
            final int id = carouselList.get(i).getId();
            final ImageView iv = new ImageView(mActivity);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mActivity, CarouselActivity.class);
                    intent.putExtra(Keyword.ID, id);
                    mActivity.startActivity(intent);
                }
            });
            imageViewList.add(iv);
            final String src = carouselList.get(i).getUrl();
            new RequestImage(src) {
                @Override
                public void setImage(Bitmap bitmap) {
                    if (position == 0) {
                        imageHandler.setImage1(iv, bitmap);
                        SendHandlerMessage.sendMessage(imageHandler, ImageHandler.NEWS_TITLE_IMAGE_REFRESH, ImageHandler.NEWS_TITLE_IMAGE1);
                    } else if (position == 1) {
                        imageHandler.setImage2(iv, bitmap);
                        SendHandlerMessage.sendMessage(imageHandler, ImageHandler.NEWS_TITLE_IMAGE_REFRESH, ImageHandler.NEWS_TITLE_IMAGE2);
                    } else if (position == 2) {
                        imageHandler.setImage3(iv, bitmap);
                        SendHandlerMessage.sendMessage(imageHandler, ImageHandler.NEWS_TITLE_IMAGE_REFRESH, ImageHandler.NEWS_TITLE_IMAGE3);
                    }
                }
            }.run();
        }

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(imageViewList.get(position));

        return imageViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViewList.get(position));
    }

    @Override
    public int getCount() {
        return carouselList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }
}
