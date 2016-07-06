package com.wenc.tltd.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenc.tltd.R;

/**
 * 模仿ios提示框
 * Created by WenC on 2016-06-05.
 */
public class IOSDialog extends Dialog {

    public IOSDialog(Context context) {
        super(context);
    }

    public IOSDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context mContext;
        private String message;
        private String title;
        private String mPositiveBtnTxt;
        private String mNegativeBtnTxt;
        private DialogInterface.OnClickListener positiveBtnClickListener;
        private DialogInterface.OnClickListener negativeBtnClickListener;

        public Builder(Context context) {
            this.mContext = context;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Builder setPositiveBtnClickListener(String positiveBtnTxt, DialogInterface.OnClickListener positiveBtnClickListener) {
            this.mPositiveBtnTxt = positiveBtnTxt;
            this.positiveBtnClickListener = positiveBtnClickListener;
            return this;
        }

        public Builder setNegativeBtnClickListener(String negativeBtnTxt, DialogInterface.OnClickListener negativeBtnClickListener) {
            this.mNegativeBtnTxt = negativeBtnTxt;
            this.negativeBtnClickListener = negativeBtnClickListener;
            return this;
        }

        public IOSDialog create() {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final IOSDialog dialog = new IOSDialog(mContext, R.style.dialog);
            View view = null;

            if (negativeBtnClickListener != null) {
                view = inflater.inflate(R.layout.dialog_two_btn_no_title, null);
                // 添加布局
                dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView dialogMessage = (TextView) view.findViewById(R.id.dialogMessage);
                dialogMessage.setText(message);
                Button negativeBtn = (Button) view.findViewById(R.id.negativeBtn);
                negativeBtn.setText(mNegativeBtnTxt);
                negativeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeBtnClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                });
            } else {
                view = inflater.inflate(R.layout.dialog_single_btn_no_title, null);
                // 添加布局
                dialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView dialogMessage = (TextView) view.findViewById(R.id.dialogSingleMessage);
                dialogMessage.setText(message);
            }
            if (positiveBtnClickListener != null) {
                Button positiveBtn = (Button) view.findViewById(R.id.positiveBtn);
                positiveBtn.setText(mPositiveBtnTxt);
                positiveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        positiveBtnClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                    }
                });
            }
            dialog.setContentView(view);
            return dialog;
        }

    }

}
