package com.spadestack.nsuonline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity_erm extends AppCompatActivity {

    private WebView ermView;
    private Button btn_ss;
    private String currentUrl;
    ProgressDialog mProgressDialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erm);

        mProgressDialog = new ProgressDialog(this);

        ermView = (WebView) findViewById(R.id.ermView);

        ermView.setVerticalScrollBarEnabled(false);
        ermView.setHorizontalScrollBarEnabled(false);


        WebSettings webSettings = ermView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        ermView.loadUrl("https://rds1.northsouth.edu/");
        ermView.setWebViewClient(new HelloWebViewClient());

        currentUrl = ermView.getUrl().toString();

        if(currentUrl.equals("https://rds1.northsouth.edu/index.php/attendance/attendance/studentAttendanceLanding")){
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }




        ///////////////////////////////////////SCREENSHOT CODE DOWN////////////////////////////////////////////////////

        btn_ss = (Button) findViewById(R.id.btn_ss);

        btn_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout_erm);

                layout.post(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap pic = takeScreenShot(layout);

                        try{
                            if (pic != null);{
                                saveScreenShot(pic);
                                Toast.makeText(Activity_erm.this, "Screenshot Taken!", Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            Toast.makeText(Activity_erm.this, "Failed to take screenshot!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


    }



    private Bitmap takeScreenShot(View view){
        Bitmap screenShot=null;

        try{
            int width=view.getMeasuredWidth();
            int height=view.getMeasuredHeight();

            screenShot=Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

            Canvas c = new Canvas(screenShot);
            view.draw(c);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return screenShot;
    }

    //String timeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
    String filename = new Date().getTime() + ".png";

    private void saveScreenShot(Bitmap bitmap){

/*        String filePath = Environment.DIRECTORY_DCIM + File.separator + "DCIM/screen"+ filename;
        File imagePath = new File(filePath);

        FileOutputStream fos;
        try{
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
        }
*/

        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.setVisibility(View.GONE);
            //mProgressDialog.setTitle("Loading");
            mProgressDialog.show();
            mProgressDialog.setMessage("Loading Data . . .");
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
        WebView view = (WebView) findViewById(R.id.ermView);
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
