package com.zsl.atvlinks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private WebView wv_content;

    //title
    String mTitle = "";
    //url
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        wv_content = (WebView) findViewById(R.id.web_wv_webview);
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
        mTitle = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");

        //启用支持javascript
        WebSettings settings = wv_content.getSettings();
        settings.setJavaScriptEnabled(true);
        //自适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //设置WebChromeClient设置标题title
        wv_content.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (TextUtils.isEmpty(mTitle)) {
                    toolbar.setContentDescription(title);
                } else {
                    toolbar.setContentDescription(mTitle);
                }
            }
        });
        wv_content.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        // 设置setWebChromeClient对象
        wv_content.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
            }
        });

        //加载网页
        wv_content.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (wv_content.canGoBack()) {
            wv_content.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
