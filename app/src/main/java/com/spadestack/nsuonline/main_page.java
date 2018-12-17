package com.spadestack.nsuonline;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_page extends AppCompatActivity {

    Button btn_erm, btn_course, btn_dev, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        btn_erm = (Button) findViewById(R.id.btn_erm);
        btn_course = (Button) findViewById(R.id.btn_course);
        btn_dev = (Button) findViewById(R.id.btn_dev);
        btn_exit = (Button) findViewById(R.id.btn_exit);

        btn_erm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent ermIntent = new Intent(main_page.this, Activity_erm.class);
                startActivity(ermIntent);
            }
        });

        btn_course.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent courseIntent = new Intent(main_page.this, Course.class);
                startActivity(courseIntent);
            }
        });

        btn_dev.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent devIntent = new Intent(main_page.this, Developer.class);
                startActivity(devIntent);
            }
        });
    }

        public void clickexit(View v){
            moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }




}


