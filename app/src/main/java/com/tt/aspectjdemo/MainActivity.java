package com.tt.aspectjdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tt.userbehavior.UserEvent;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @UserEvent(tag = "这是注解登录方法的tag值")
    public void onLogin(View view) {
        Log.d(TAG, "执行了这个方法");
    }

    public void onJumpSecond(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
