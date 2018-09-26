package com.lotus.lotusapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class C09Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c09);
        // 数字键盘区按钮逻辑
        functionNumButton();
        // 退出C界面
        findViewById(R.id.bt_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(C09Activity.this, MainActivity.class);
                startActivity(i);
            }
        });
        // 返回安卓
        findViewById(R.id.bt_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
    }

    /**
     * 数字键盘区按钮逻辑
     */
    private void functionNumButton() {
        // 数字键
        /*findViewById(R.id.bt_num_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });*/
    }
}
