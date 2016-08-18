package com.zsl.atvlinks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ScrollingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TextView tv_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        tv_content = (TextView) findViewById(R.id.content_scrolling_tv_content);
        tv_content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void initEvent() {
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initData() {
        String text = "我同意<a href=\"http://www.baidu.com/\">用户协议</a>中的所有内容<a href=\"http://www.xiaomi.com/\">小米网</a>后面是<a href=\"www.baidu.com/\">没有http://</a>";
        tv_content.setText(getSpannableStringBuilder(text));
    }

    @NonNull
    private SpannableStringBuilder getSpannableStringBuilder(String htmlText) {
        SpannableStringBuilder spannedString = (SpannableStringBuilder) Html.fromHtml(htmlText);
        URLSpan[] urlSpans = spannedString.getSpans(0, spannedString.length(), URLSpan.class);
        Log.d("SpannableStringBuilder", "SpannableStringBuilder:" + urlSpans.length);
        for (URLSpan span : urlSpans) {
            int start = spannedString.getSpanStart(span);
            int end = spannedString.getSpanEnd(span);
            spannedString.removeSpan(span);
            spannedString.setSpan(new MyURLSpan(span.getURL()), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannedString;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
