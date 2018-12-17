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

public class Course extends AppCompatActivity {

    private WebView courseView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mProgressDialog = new ProgressDialog(this);

        courseView = (WebView) findViewById(R.id.courseView);

        courseView.setHorizontalScrollBarEnabled(false);
        courseView.setVerticalScrollBarEnabled(false);

        WebSettings webSettings = courseView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        courseView.loadUrl("http://www.northsouth.edu");
        courseView.setWebViewClient(new HelloWebViewClient());
    }

    class HelloWebViewClient extends WebViewClient {
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
        WebView view = (WebView) findViewById(R.id.courseView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
