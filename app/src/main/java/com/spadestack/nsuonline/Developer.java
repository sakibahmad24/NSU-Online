package com.spadestack.nsuonline;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Developer extends AppCompatActivity {
    private WebView devView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        mProgressDialog = new ProgressDialog(this);

        devView = (WebView) findViewById(R.id.devView);

        devView.setHorizontalScrollBarEnabled(false);
        devView.setVerticalScrollBarEnabled(false);

        WebSettings webSettings = devView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        devView.loadUrl("http://www.spadestack.com");
        devView.setWebViewClient(new HelloWebViewClient());
    }

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.setVisibility(View.GONE);
            //mProgressDialog.setTitle("Loading");
            mProgressDialog.show();
            mProgressDialog.setMessage("Loading Page . . .");
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mProgressDialog.dismiss();
            animate(view);
            view.setVisibility(View.VISIBLE);
            super.onPageFinished(view, url);
        }
    }

    private void animate(final WebView view) {
        Animation anim = AnimationUtils.loadAnimation(getBaseContext(),
                android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        WebView view = (WebView) findViewById(R.id.devView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
