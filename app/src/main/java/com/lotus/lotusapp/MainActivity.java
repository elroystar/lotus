package com.lotus.lotusapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lotus.lotusapp.db.SQLiteDbHelper;
import com.lotus.lotusapp.dto.PasswordRule;
import com.lotus.lotusapp.dto.User;
import com.lotus.lotusapp.utils.PasswordRuleUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    // 图片按钮定义
    private ImageButton imageButton;

    // 布局定义
    private LinearLayout linearLayout;

    // 数字输入框字符串
    private String stringTx = "";

    // 数据连接工具
    private SQLiteDbHelper sqLiteDbHelper;

    // User实体类定义
    private User user = new User();

    // 密码规则实体类定义
    private PasswordRule passwordRule = new PasswordRule();

    // 定义RequestOptions
    private RequestOptions requestOptions = new RequestOptions()
            .error(R.drawable.ic_launcher_background);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a09);
        // 加载按钮图片
        loadImageButton();
        // 数字键盘区按钮逻辑
        functionNumButton();

    }

    /**
     * 数字键盘区按钮逻辑
     */
    private void functionNumButton() {
        // 数字键
        findViewById(R.id.ib_num_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("0");
                }
                stringTx = stringTx + "0";
            }
        });
        findViewById(R.id.ib_num_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("1");
                }
                stringTx = stringTx + "1";
            }
        });
        findViewById(R.id.ib_num_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("2");
                }
                stringTx = stringTx + "2";
            }
        });
        findViewById(R.id.ib_num_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("3");
                }
                stringTx = stringTx + "3";
            }
        });
        findViewById(R.id.ib_num_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("4");
                }
                stringTx = stringTx + "4";
            }
        });
        findViewById(R.id.ib_num_5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("5");
                }
                stringTx = stringTx + "5";
            }
        });
        findViewById(R.id.ib_num_6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("6");
                }
                stringTx = stringTx + "6";
            }
        });
        findViewById(R.id.ib_num_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("7");
                }
                stringTx = stringTx + "7";
            }
        });
        findViewById(R.id.ib_num_8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("8");
                }
                stringTx = stringTx + "8";
            }
        });
        findViewById(R.id.ib_num_9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stringTx.length() < 10) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("9");
                }
                stringTx = stringTx + "9";
            }
        });
        // Esc键
        findViewById(R.id.ib_num_esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    // 获取textView id
                    int tx_num_id = getResources().getIdentifier("tx_num_" + i, "id", getPackageName());
                    TextView tv = findViewById(tx_num_id);
                    tv.setText("");
                }
                stringTx = "";
            }
        });
        // OK键
        findViewById(R.id.ib_num_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查询数据库
                sqLiteDbHelper = new SQLiteDbHelper(getApplicationContext());
                SQLiteDatabase dbRead = sqLiteDbHelper.getReadableDatabase();
                SQLiteDatabase dbWrit = sqLiteDbHelper.getWritableDatabase();
                try {
                    // 判断输入数字是否为0开头和10位
                    if (stringTx.startsWith("0") && stringTx.length() == 10) {
                        // select * from user where phone = 'stringTx'
                        Cursor cursor = dbRead.query("user",
                                null,
                                "phone = ?",
                                new String[]{stringTx},
                                null,
                                null,
                                null);
                        if (cursor.getCount() > 0) {
                            // user实体赋值
                            while (cursor.moveToNext()) {
                                user.setId(cursor.getInt(cursor.getColumnIndex("id")));
                                user.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                                user.setReward_num(cursor.getString(cursor.getColumnIndex("reward_num")));
                                user.setReward_total(cursor.getString(cursor.getColumnIndex("reward_total")));
                                user.setWashing_num(cursor.getString(cursor.getColumnIndex("washing_num")));
                                user.setWashing_total(cursor.getString(cursor.getColumnIndex("washing_total")));
                            }
                            // 显示洗衣次数及奖励
                            TextView tv = findViewById(R.id.tx_washing);
                            tv.setText(user.getWashing_num());
                            tv = findViewById(R.id.tx_reward);
                            tv.setText(user.getReward_num());
                        } else {
                            // 注册键亮起
                            imageButton = findViewById(R.id.ib_reg);
                            Glide.with(v)
                                    .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09", "ib_reg.png"))
                                    .apply(requestOptions)
                                    .into(imageButton);
                        }
                    } else {
                        // 验证密码有限期
                        // select * from password_bank where password = 'stringTx'
                        Cursor cursor = dbRead.query("password_bank",
                                null,
                                "password = ?",
                                new String[]{stringTx},
                                null,
                                null,
                                null);
                        if (cursor.getCount() > 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Tips");
                            builder.setMessage("请验证后重新输入");
                            builder.setPositiveButton("OK", null);
                            builder.show();
                        } else {
                            // 获取密码规则
                            // select * from password_rule where state = '1'
                            cursor = dbRead.query("password_rule",
                                    null,
                                    "state = ?",
                                    new String[]{"1"},
                                    null,
                                    null,
                                    null);
                            if (cursor.getCount() > 0) {
                                // passwordRule赋值
                                while (cursor.moveToNext()) {
                                    passwordRule.setId(cursor.getInt(cursor.getColumnIndex("id")));
                                    passwordRule.setRule(cursor.getString(cursor.getColumnIndex("rule")));
                                    passwordRule.setState(cursor.getString(cursor.getColumnIndex("state")));
                                }
                                // 匹配密码规则进入C界面
                                if (PasswordRuleUtil.checkPassword(passwordRule.getRule(), stringTx)) {
                                    // 已经使用的密码入库
                                    dbWrit.execSQL("insert into password_bank(password) values ('" + stringTx + "')");
                                    // 跳转C界面
                                    Intent i = new Intent(MainActivity.this, C09Activity.class);
                                    i.putExtra("passwordRule", passwordRule);
                                    startActivity(i);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle("Tips");
                                    builder.setMessage("请验证后重新输入");
                                    builder.setPositiveButton("OK", null);
                                    builder.show();
                                }
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Error");
                                builder.setMessage("查询不到密码规则");
                                builder.setPositiveButton("OK", null);
                                builder.show();
                            }
                        }
                    }
                } finally {
                    // 关闭数据库连接
                    dbRead.close();
                    dbWrit.close();
                }
            }
        });
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
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_normal1.png"))
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
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_ing1.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_washing_5);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_ing2.png"))
                .apply(requestOptions)
                .into(imageButton);
        imageButton = findViewById(R.id.ib_washing_6);
        Glide.with(this)
                .load(new File(Environment.getDataDirectory().getPath() + File.separator + "lotus" + File.separator + "a09" + File.separator + "wash", "ib_wash_ing3.png"))
                .apply(requestOptions)
                .into(imageButton);
    }
}
