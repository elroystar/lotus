package com.lotus.lotusapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.lotus.lotusapp.db.SQLiteDbHelper;
import com.lotus.lotusapp.dto.PasswordRule;

public class C09Activity extends Activity {

    public static final String MODIFY_PASSWORD = "modify_password";
    public static final String OUT_MONEY = "out_money";
    public static final String PRICE = "price";
    public static final String TEST = "test";
    public static final String WASHING_MACHINE = "washing_machine";
    public static final String COIN_BOX = "coin_box";

    // C界面模式选择，默认为修改密码模式
    private String model = "";

    // 密码规则
    private PasswordRule passwordRule;

    // 数据库连接工具
    private SQLiteDbHelper sqLiteDbHelper;

    // 声明soundPool
    private SoundPool soundPool;

    // 定义一个整型用load(),来设置soundID
    private int music;

    /**
     * textView选中判断条件
     * 1：新1修改密码
     * 2：新2修改密码
     * 3：
     * 4：
     * 5：
     * 6：
     * 7：
     * 8：
     * 9：
     * 10：
      */
    private String getTvSelectModel = "";

    /**
     * 修改密码区逻辑
     */
    private String newPasswordOne = "";
    private String newPasswordTwo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c09);
        // 加载声音
        initSound();
        // 获取显示密码规则
        showPasswordRule();
        // 置灰不可选择按钮
        ashNoChoiceButton(model);
        // 修改密码逻辑
        functionModifyPassword();
        // 数字键盘区按钮逻辑
        functionNumButton();
        // 定价键区按钮逻辑
        functionPriceButton();
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
//                moveTaskToBack(true);
                // 将应用退到桌面上，保留自身
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
    }

    /**
     * 置灰不可选择按钮
     * @param model
     */
    private void ashNoChoiceButton(String model) {
        switch (model) {
            case OUT_MONEY:
                ashOutMoneyButton();
                break;
            case PRICE:
                ashPriceButton();
                break;
            case TEST:
                ashTestButton();
                break;
            case WASHING_MACHINE:
                ashWashingMachineButton();
                break;
            case COIN_BOX:
                ashCoinBoxButton();
                break;
            default:
                ashOutMoneyButton();
                ashPriceButton();
                ashTestButton();
                ashWashingMachineButton();
                ashCoinBoxButton();
                break;
        }
    }

    /**
     * 置灰投币箱
     */
    private void ashCoinBoxButton() {
        for (int i = 1; i <= 4; i++) {
            // 获取textView id
            int bt_coin_box = getResources().getIdentifier("bt_coin_box_" + i, "id", getPackageName());
            findViewById(bt_coin_box).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        }
    }

    /**
     * 置灰洗衣机
     */
    private void ashWashingMachineButton() {
        for (int i = 1; i <= 16; i++) {
            // 获取textView id
            int bt_washing_machine = getResources().getIdentifier("bt_washing_machine_" + i, "id", getPackageName());
            findViewById(bt_washing_machine).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        }
    }

    /**
     * 置灰测试项目
     */
    private void ashTestButton() {
        findViewById(R.id.bt_test_disinfection).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_disinfection_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_washing_liquid).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_washing_liquid_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_softening).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_softening_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_inlet).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_inlet_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_drain).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_drain_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_test_short_program).setBackgroundResource(R.drawable.c09_bt_ash_shape);
    }

    /**
     * 置灰定价项目
     */
    private void ashPriceButton() {
        findViewById(R.id.bt_drying).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_rinse).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_cowboy).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_sheets).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_standard).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_washing_liquid).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_softening).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_disinfection_ing).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_disinfection_before).setBackgroundResource(R.drawable.c09_bt_ash_shape);
    }

    /**
     * 置灰出币开关
     */
    private void ashOutMoneyButton() {
        findViewById(R.id.bt_on_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
        findViewById(R.id.bt_un_switch).setBackgroundResource(R.drawable.c09_bt_ash_shape);
    }

    /**
     * 修改密码逻辑
     */
    private void functionModifyPassword() {
        findViewById(R.id.tv_password_new_one).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 修改密码模式
                    model = MODIFY_PASSWORD;
                    getTvSelectModel = "1";
                    // 新1显示框亮起
                    v.setBackgroundResource(R.drawable.c09_tv_select_shape);
                    findViewById(R.id.tv_password_new_two).setBackgroundResource(R.drawable.c09_tv_price_shape);
                }
                return false;
            }
        });
        findViewById(R.id.tv_password_new_two).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 修改密码模式
                    model = MODIFY_PASSWORD;
                    getTvSelectModel = "2";
                    // 新2显示框亮起
                    v.setBackgroundResource(R.drawable.c09_tv_select_shape);
                    findViewById(R.id.tv_password_new_one).setBackgroundResource(R.drawable.c09_tv_price_shape);
                }
                return false;
            }
        });
    }

    /**
     * 定价键区按钮逻辑
     */
    private void functionPriceButton() {
        // 定价按钮
        findViewById(R.id.bt_accessories).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 播放按键声音
                    playSound();
                    // 修改模式
                    model = PRICE;
                    // 定价按钮常亮
                    v.setBackgroundColor(Color.parseColor("#ADFF2F"));
                }
                return false;
            }
        });
        // 刷干按钮


    }

    /**
     * 获取显示密码规则
     */
    private void showPasswordRule() {
        Intent i = getIntent();
        passwordRule = i.getParcelableExtra("passwordRule");
        TextView tv = findViewById(R.id.tv_password_new_one);
        tv.setText(passwordRule.getRule());
        tv = findViewById(R.id.tv_password_new_two);
        tv.setText(passwordRule.getRule());
    }

    /**
     * 数字键盘区按钮逻辑
     */
    private void functionNumButton() {
        // 数字键
        findViewById(R.id.bt_num_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("1");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("2");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("3");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_4).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("4");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_5).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("5");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_6).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("6");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_7).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("7");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_8).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("8");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_9).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("9");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // 数字键
        findViewById(R.id.bt_num_0).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            modifyPassword("0");
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // ok键
        findViewById(R.id.bt_num_ok).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            if (newPasswordOne.equals(newPasswordTwo)) {
                                sqLiteDbHelper = new SQLiteDbHelper(getApplicationContext());
                                SQLiteDatabase dbWrit = sqLiteDbHelper.getWritableDatabase();
                                dbWrit.execSQL("insert into password_rule(rule, state) values ('" + newPasswordOne + "', '1')");
                                dbWrit.execSQL("update password_rule set state = '0' where id = " + passwordRule.getId() + "");
                                dbWrit.close();
                                passwordRule.setRule(newPasswordOne);
                                resetModifyPassword();
                                alertMsg("Tips", "修改成功！");
                            } else {
                                resetModifyPassword();
                                alertMsg("Error", "两次密码输入不一致！");
                            }
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
        // Esc键
        findViewById(R.id.bt_num_esc).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    switch (model) {
                        case MODIFY_PASSWORD:
                            resetModifyPassword();
                            break;
                        case OUT_MONEY:

                            break;
                        case PRICE:

                            break;
                        case TEST:

                            break;
                    }

                }
                return false;
            }
        });
    }

    /**
     * 重置密码区
     */
    private void resetModifyPassword() {
        newPasswordOne = "";
        newPasswordTwo = "";
        getTvSelectModel = "";
        TextView tv = findViewById(R.id.tv_password_new_one);
        tv.setBackgroundResource(R.drawable.c09_tv_price_shape);
        tv.setText(passwordRule.getRule());
        tv = findViewById(R.id.tv_password_new_two);
        tv.setText(passwordRule.getRule());
        tv.setBackgroundResource(R.drawable.c09_tv_price_shape);
    }

    /**
     * 弹框提示
     *
     * @param tips
     * @param msg
     */
    private void alertMsg(String tips, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(C09Activity.this);
        builder.setTitle(tips);
        builder.setMessage(msg);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    /**
     * 修改密码显示
     *
     * @param num
     */
    private void modifyPassword(String num) {
        if (newPasswordOne.length() <= 5 && "1".equals(getTvSelectModel)) {
            newPasswordOne = newPasswordOne + num;
            TextView tv = findViewById(R.id.tv_password_new_one);
            tv.setText(newPasswordOne);
        }
        if (newPasswordTwo.length() <= 5 && "2".equals(getTvSelectModel)) {
            newPasswordTwo = newPasswordTwo + num;
            TextView tv = findViewById(R.id.tv_password_new_two);
            tv.setText(newPasswordTwo);
        }
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
}
