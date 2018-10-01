package com.lotus.lotusapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
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

public class MainActivity extends Activity {

    // 图片按钮定义
    private ImageButton imageButton;

    // 布局定义
    private LinearLayout linearLayout;

    // 数字输入框字符串
    private String stringTx = "";

    // 声明soundPool
    private SoundPool soundPool;

    // 定义一个整型用load(),来设置soundID
    private int music;

    // 数据库连接工具
    private SQLiteDbHelper sqLiteDbHelper;

    // User实体类定义
    private User user = new User();

    // 密码规则实体类定义
    private PasswordRule passwordRule = new PasswordRule();

    private String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "data" + File.separator + "lotus" + File.separator + "a09";

    // 定义RequestOptions
    private RequestOptions requestOptions = new RequestOptions()
            .error(R.drawable.ic_launcher_background);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a09);
        // 加载声音
        initSound();
        // 加载按钮图片
        loadImageButton();
        // 数字键盘区按钮逻辑
        functionNumButton();

    }

    /**
     * 加载声音
     */
    private void initSound() {
        soundPool = new SoundPool(5, AudioManager.STREAM_SYSTEM, 0);
        music = soundPool.load(this, R.raw.music1, 1);
    }

    /**
     * 播放声音
     */
    private void playSound() {
        soundPool.play(music, 1, 1, 1, 0, 1);
    }

    /**
     * 数字键盘区按钮逻辑
     */
    private void functionNumButton() {
        // 数字键
        findViewById(R.id.ib_num_0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_0, "0", "ib_num_0.png");
            }
        });
        findViewById(R.id.ib_num_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_1, "1", "ib_num_1.png");
            }
        });
        findViewById(R.id.ib_num_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_2, "2", "ib_num_2.png");
            }
        });
        findViewById(R.id.ib_num_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_3, "3", "ib_num_3.png");
            }
        });
        findViewById(R.id.ib_num_4).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_4, "4", "ib_num_4.png");
            }
        });
        findViewById(R.id.ib_num_5).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_5, "5", "ib_num_5.png");
            }
        });
        findViewById(R.id.ib_num_6).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_6, "6", "ib_num_6.png");
            }
        });
        findViewById(R.id.ib_num_7).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_7, "7", "ib_num_7.png");
            }
        });
        findViewById(R.id.ib_num_8).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_8, "8", "ib_num_8.png");
            }
        });
        findViewById(R.id.ib_num_9).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchNumFunction(v, event, R.id.ib_num_9, "9", "ib_num_9.png");
            }
        });
        // Esc键
        findViewById(R.id.ib_num_esc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 播放按键声音
                playSound();
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
                // 播放按键声音
                playSound();
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
                                user.setRewardNum(cursor.getInt(cursor.getColumnIndex("reward_num")));
                                user.setRewardTotal(cursor.getInt(cursor.getColumnIndex("reward_total")));
                                user.setWashingNum(cursor.getInt(cursor.getColumnIndex("washing_num")));
                                user.setWashingTotal(cursor.getInt(cursor.getColumnIndex("washing_total")));
                            }
                            // 显示洗衣次数及奖励
                            TextView tv = findViewById(R.id.tx_washing);
                            tv.setText(user.getWashingNum());
                            tv = findViewById(R.id.tx_reward);
                            tv.setText(user.getRewardNum());
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
                            alertMsg("Tips", "请验证后重新输入！");
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
                                    alertMsg("Tips", "请验证后重新输入！");
                                }
                            } else {
                                alertMsg("Error", "查询不到密码规则！");
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
     * 数字键触摸逻辑控制
     *
     * @param v
     * @param event
     * @param ib_num
     * @param txStr
     * @param imgStr
     * @return
     */
    private boolean onTouchNumFunction(View v, MotionEvent event, int ib_num, String txStr, String imgStr) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 播放按键声音
            playSound();
            // 重新设置按下时的背景图片
            loadImage(ib_num, this, filePath + File.separator + "cg", "ib_num_OK.png");
            if (stringTx.length() < 10) {
                // 获取textView id
                int tx_num_id = getResources().getIdentifier("tx_num_" + stringTx.length(), "id", getPackageName());
                TextView tv = findViewById(tx_num_id);
                tv.setText(txStr);
            }
            stringTx = stringTx + txStr;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 再修改为抬起时的正常图片
            loadImage(ib_num, this, filePath + File.separator + "cg", imgStr);
        }
        return false;
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
                    .load(new File(filePath + File.separator + "cg", "ib_num_" + i + ".png"))
                    .apply(requestOptions)
                    .into(imageButton);
        }
        loadImage(R.id.ib_num_en, this, filePath + File.separator + "cg", "ib_num_En.png");
        loadImage(R.id.ib_num_esc, this, filePath + File.separator + "cg", "ib_num_Esc.png");
        loadImage(R.id.ib_num_ok, this, filePath + File.separator + "cg", "ib_num_OK.png");
    }

    /**
     * 加载洗衣机键
     */
    private void loadWashingBtn() {
        loadImage(R.id.ib_washing_1, this, filePath + File.separator + "wash", "ib_wash_normal1.png");
        loadImage(R.id.ib_washing_2, this, filePath + File.separator + "wash", "ib_wash_damaged.png");
        loadImage(R.id.ib_washing_3, this, filePath + File.separator + "wash", "ib_wash_invalid.png");
        loadImage(R.id.ib_washing_4, this, filePath + File.separator + "wash", "ib_wash_ing1.png");
        loadImage(R.id.ib_washing_5, this, filePath + File.separator + "wash", "ib_wash_ing2.png");
        loadImage(R.id.ib_washing_6, this, filePath + File.separator + "wash", "ib_wash_ing3.png");
    }

    /**
     * 加载图片
     *
     * @param ib_washing
     * @param activity
     * @param filePath
     * @param imgStr
     */
    private void loadImage(int ib_washing, Activity activity, String filePath, String imgStr) {
        imageButton = findViewById(ib_washing);
        Glide.with(activity)
                .load(new File(filePath, imgStr))
                .apply(requestOptions)
                .into(imageButton);
    }

    /**
     * 弹框提示
     *
     * @param tips
     * @param msg
     */
    private void alertMsg(String tips, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(tips);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        builder.show();
    }
}
