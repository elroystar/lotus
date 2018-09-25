package com.lotus.lotusapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lotus.lotusapp.dto.PasswordRule;

public class C09Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c09);

        findViewById(R.id.ib_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(C09Activity.this, MainActivity.class);
                PasswordRule passwordRule = i.getParcelableExtra("passwordRule");
                startActivity(i);
            }
        });
    }
}
