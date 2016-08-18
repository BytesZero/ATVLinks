package com.zsl.atvlinks;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;

/**
 * Created by zsl on 16/8/18.
 * 自定义的URLSpan
 */
public class MyURLSpan extends URLSpan {

    public MyURLSpan(String url) {
        super(url);
    }

    public MyURLSpan(Parcel src) {
        super(src);
    }

    @Override
    public void onClick(View widget) {
        try {
            String url = getURL();
            Log.d("MyURLSpan", "url:" + url);
            Context context = widget.getContext();
            Intent intent = new Intent();
            intent.setClass(context, WebActivity.class);
            intent.putExtra("url", url);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.d("MyURLSpan", "start webviewActivity error start system browser");
            super.onClick(widget);
        }
    }
}
