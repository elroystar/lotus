package com.lotus.lotusapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.lotus.lotusapp.db.SQLiteDbHelper;
import com.lotus.lotusapp.dto.PasswordRule;
import com.lotus.lotusapp.dto.WashingMachine;
import com.lotus.lotusapp.utils.PasswordRuleUtil;

import java.util.ArrayList;
import java.util.List;

public class C09Activity extends Activity {

    public static final String MODIFY_PASSWORD = "modify_password";
    public static final String OUT_MONEY = "out_money";
    public static final String PRICE = "price";
    public static final String TEST = "test";
    public static final String WASHING_MACHINE = "washing_machine";
    public static final String COIN_BOX = "coin_box";

    // C界面模式选择，默认为修改密码模式
    private String model = "";
    // 定价区选择模式
    private String modelPrice = "";

    // 密码规则
    private PasswordRule passwordRule;

    // 数据库连接工具
    private SQLiteDbHelper sqLiteDbHelper;

    // 声明soundPool
    private SoundPool soundPool;

    // 定义一个整型用load(),来设置soundID
    private int music;

    // 有效洗衣机集合
    private List<WashingMachine> washingMachines = new ArrayList<>();
    // 选中洗衣机集合
    private List<WashingMachine> washingMachinesSelect = new ArrayList<>();

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

    // 数字输入框字符串
    private String stringTx = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c09);
        // 加载声音
        initSound();
        // 加载有效洗衣机
        initEffectiveWash();
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
     *
     * @param model
     */
    private void ashNoChoiceButton(String model) {
        switch (model) {
            case OUT_MONEY:
                ashOutMoneyButton();
                break;
            case PRICE:
                ashPriceButton(false);
                break;
            case TEST:
                ashTestButton();
                break;
            case WASHING_MACHINE:
                ashWashingMachineButton(null, false);
                break;
            case COIN_BOX:
                ashCoinBoxButton();
                break;
            default:
                ashOutMoneyButton();
                ashPriceButton(false);
                ashTestButton();
                ashWashingMachineButton(null, false);
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
            ashButton(bt_coin_box, R.drawable.c09_bt_ash_shape, false);
        }
    }

    /**
     * 置灰洗衣机
     *
     * @param washingMachines
     * @param buttonLight
     */
    private void ashWashingMachineButton(List<WashingMachine> washingMachines, Boolean buttonLight) {
        if (washingMachines == null) {
            // 置灰所有
            for (int i = 1; i <= 16; i++) {
                // 获取textView id
                int bt_washing_machine = getResources().getIdentifier("bt_washing_machine_" + i, "id", getPackageName());
                if (buttonLight) {
                    ashButton(bt_washing_machine, R.drawable.c09_bt_model_shape, true);
                } else {
                    ashButton(bt_washing_machine, R.drawable.c09_bt_ash_shape, false);
                }
            }
        } else {
            // 置灰选择的
            for (WashingMachine washingMachine : washingMachines) {
                // 获取textView id
                int bt_washing_machine = getResources().getIdentifier("bt_washing_machine_" + washingMachine.getNum(), "id", getPackageName());
                if (buttonLight) {
                    ashButton(bt_washing_machine, R.drawable.c09_bt_model_shape, true);
                } else {
                    ashButton(bt_washing_machine, R.drawable.c09_bt_ash_shape, false);
                }
            }
        }
    }

    /**
     * 置灰测试项目
     */
    private void ashTestButton() {
        ashButton(R.id.bt_test_disinfection, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_disinfection_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_washing_liquid, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_washing_liquid_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_softening, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_softening_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_inlet, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_inlet_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_drain, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_drain_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_test_short_program, R.drawable.c09_bt_ash_shape, false);
    }

    /**
     * 置灰定价项目
     *
     * @param buttonLight
     */
    private void ashPriceButton(Boolean buttonLight) {
        if (buttonLight) {
            ashButton(R.id.bt_drying, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_rinse, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_cowboy, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_sheets, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_standard, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_washing_liquid, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_softening, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_disinfection_ing, R.drawable.c09_bt_accessories_shape, true);
            ashButton(R.id.bt_disinfection_before, R.drawable.c09_bt_accessories_shape, true);
        } else {
            ashButton(R.id.bt_drying, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_rinse, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_cowboy, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_sheets, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_standard, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_washing_liquid, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_softening, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_disinfection_ing, R.drawable.c09_bt_ash_shape, false);
            ashButton(R.id.bt_disinfection_before, R.drawable.c09_bt_ash_shape, false);
        }
    }

    /**
     * 置灰出币开关
     */
    private void ashOutMoneyButton() {
        ashButton(R.id.bt_on_switch, R.drawable.c09_bt_ash_shape, false);
        ashButton(R.id.bt_un_switch, R.drawable.c09_bt_ash_shape, false);
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
                    v.setBackgroundResource(R.drawable.c09_bt_accessories_select_shape);
                }
                return false;
            }
        });
        // 甩干按钮
        findViewById(R.id.bt_drying).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 播放按键声音
                    playSound();
                    if (!washingMachinesSelect.isEmpty()) {
                        // 甩干按钮选择状态
                        v.setBackgroundResource(R.drawable.c09_bt_accessories_select_shape);
                        modelPrice = "price_drying";
                    } else {
                        alertMsg("Tips", "请先选择洗衣机！");
                    }
                }
                return false;
            }
        });


    }

    /**
     * 按钮置灰加不可点击
     *
     * @param bt_coin_box
     * @param drawable
     * @param enable
     */
    private void ashButton(int bt_coin_box, int drawable, boolean enable) {
        findViewById(bt_coin_box).setBackgroundResource(drawable);
        findViewById(bt_coin_box).setEnabled(enable);
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
                            insertPassword("1");
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
                            insertPassword("2");
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
                            insertPassword("3");
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
                            insertPassword("4");
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
                            insertPassword("5");
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
                            insertPassword("6");
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
                            insertPassword("7");
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
                            insertPassword("8");
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
                            insertPassword("9");
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
                            insertPassword("0");
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
                            modelButtonLight(PRICE);
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
                            clearPassword();
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
     * 验证密码点亮模块按钮
     *
     * @param model
     */
    private void modelButtonLight(String model) {
        sqLiteDbHelper = new SQLiteDbHelper(getApplicationContext());
        SQLiteDatabase dbRead = sqLiteDbHelper.getReadableDatabase();
        SQLiteDatabase dbWrit = sqLiteDbHelper.getWritableDatabase();
        try {
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
                        switch (model) {
                            case OUT_MONEY:

                                break;
                            case PRICE:
                                // 定价区按钮亮起
                                ashPriceButton(true);
                                // 点亮有效洗衣机
                                ashWashingMachineButton(washingMachines, true);
                                break;
                            case TEST:

                                break;
                        }
                    } else {
                        alertMsg("Tips", "请验证后重新输入！");
                    }
                } else {
                    alertMsg("Error", "查询不到密码规则！");
                }
            }
        } finally {
            // 关闭数据库连接
            dbRead.close();
            dbWrit.close();
        }
    }

    /**
     * 加载有效洗衣机
     */
    private void initEffectiveWash() {
        // 查询有效洗衣机
        sqLiteDbHelper = new SQLiteDbHelper(getApplicationContext());
        SQLiteDatabase dbRead = sqLiteDbHelper.getReadableDatabase();
        try {
            // select * from washer where state = '1'
            Cursor cursor = dbRead.query("washing_machine",
                    null,
                    "state = ?",
                    new String[]{"1"},
                    null,
                    null,
                    null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    WashingMachine washingMachine = new WashingMachine();
                    washingMachine.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    washingMachine.setNum(cursor.getInt(cursor.getColumnIndex("num")));
                    washingMachine.setCowboyPriceCoin(cursor.getString(cursor.getColumnIndex("cowboy_price_coin")));
                    washingMachine.setCowboyPriceMobile(cursor.getString(cursor.getColumnIndex("cowboy_price_mobile")));
                    washingMachine.setDisinfectionBeforePriceCoin(cursor.getString(cursor.getColumnIndex("disinfection_before_price_coin")));
                    washingMachine.setDisinfectionBeforePriceMobile(cursor.getString(cursor.getColumnIndex("disinfection_before_price_mobile")));
                    washingMachine.setDisinfectionIngPriceCoin(cursor.getString(cursor.getColumnIndex("disinfection_ing_price_coin")));
                    washingMachine.setDisinfectionIngPriceMobile(cursor.getString(cursor.getColumnIndex("disinfection_ing_price_mobile")));
                    washingMachine.setDryingPriceCoin(cursor.getString(cursor.getColumnIndex("drying_price_coin")));
                    washingMachine.setDryingPriceMobile(cursor.getString(cursor.getColumnIndex("drying_price_mobile")));
                    washingMachine.setRinsePriceCoin(cursor.getString(cursor.getColumnIndex("rinse_price_coin")));
                    washingMachine.setRinsePriceMobile(cursor.getString(cursor.getColumnIndex("rinse_price_mobile")));
                    washingMachine.setSheetsPriceCoin(cursor.getString(cursor.getColumnIndex("sheets_price_coin")));
                    washingMachine.setSheetsPriceMobile(cursor.getString(cursor.getColumnIndex("sheets_price_mobile")));
                    washingMachine.setSofteningPriceCoin(cursor.getString(cursor.getColumnIndex("softening_price_coin")));
                    washingMachine.setSofteningPriceMobile(cursor.getString(cursor.getColumnIndex("softening_price_mobile")));
                    washingMachine.setStandardPriceCoin(cursor.getString(cursor.getColumnIndex("standard_price_coin")));
                    washingMachine.setStandardPriceMobile(cursor.getString(cursor.getColumnIndex("standard_price_mobile")));
                    washingMachine.setWashingLiquidPriceCoin(cursor.getString(cursor.getColumnIndex("washing_liquid_price_coin")));
                    washingMachine.setWashingLiquidPriceMobile(cursor.getString(cursor.getColumnIndex("washing_liquid_price_mobile")));
                    washingMachine.setDisinfectionState(cursor.getString(cursor.getColumnIndex("disinfection_state")));
                    washingMachine.setRinseState(cursor.getString(cursor.getColumnIndex("rinse_state")));
                    washingMachine.setWashingLiquidState(cursor.getString(cursor.getColumnIndex("washing_liquid_state")));
                    washingMachine.setState(cursor.getString(cursor.getColumnIndex("state")));
                    washingMachines.add(washingMachine);
                }
            } else {
                alertMsg("Error", "没有找到可以用的洗衣机！");
            }
        } finally {
            // 关闭数据库连接
            dbRead.close();
        }
    }

    /**
     * 清空密码输入框
     */
    private void clearPassword() {
        stringTx = "";
        TextView tv = findViewById(R.id.tv_password);
        tv.setText(stringTx);
    }

    /**
     * 密码输入
     *
     * @param num
     */
    private void insertPassword(String num) {
        stringTx = stringTx + num;
        TextView tv = findViewById(R.id.tv_password);
        tv.setText(stringTx);
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
