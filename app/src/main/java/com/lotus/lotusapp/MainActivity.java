package com.lotus.lotusapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;

    private LinearLayout linearLayout;

    // 定义RequestOptions
    private RequestOptions requestOptions = new RequestOptions()
            .error(R.drawable.ic_launcher_background);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a09);
        // 加载按钮图片
        loadImageButton();

    }

    /**
     * 加载按钮图片
     */
    private void loadImageButton() {
        // 设置背景图片
        linearLayout = findViewById(R.id.body_background);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09", "body_background.png"))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linearLayout.setBackground(resource);
                    }
                });
        // 设置注册键
        imageButton = findViewById(R.id.ib_reg);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09", "ib_reg.png"))
                .apply(requestOptions)
                .into(imageButton);
        // 设置洗衣次数背景
        linearLayout = findViewById(R.id.member_times);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09", "member_times.png"))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        linearLayout.setBackground(resource);
                    }
                });
        // 设置使用积分键
        imageButton = findViewById(R.id.use_integral);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09", "use_integral.png"))
                .apply(requestOptions)
                .into(imageButton);
        // 设置数字键
        loadNumBtn();
        // 设置洗衣机
        loadWashingBtn();


    }

    /**
     * 加载数字键
     */
    private void loadNumBtn() {
        // 数字按钮区图片
        for (int i = 0; i < 10; i++) {
            // 获取按钮id
            int ib_id = getResources().getIdentifier("ib_num_" + i, "id", getPackageName());
            imageButton = findViewById(ib_id);
            //加载图片
            Glide.with(this)
                    .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "cg", "ib_num_" + i + ".png"))
                    .apply(requestOptions)
                    .into(imageButton);
        }
        imageButton = findViewById(R.id.ib_num_en);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "cg", "ib_num_En.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_num_esc);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "cg", "ib_num_Esc.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_num_ok);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "cg", "ib_num_OK.png"))
                .apply(requestOptions)
                .into(imageButton);
    }

    /**
     * 加载洗衣机键
     */
    private void loadWashingBtn() {
        imageButton = findViewById(R.id.ib_washing_1);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_normal.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_washing_2);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_damaged.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_washing_3);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_invalid.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_washing_4);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_ing.png"))
                .apply(requestOptions)
                .into(imageButton);
    }
}
