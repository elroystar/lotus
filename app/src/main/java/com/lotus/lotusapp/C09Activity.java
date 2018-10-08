package com.lotus.lotusapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
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
import com.lotus.lotusapp.utils.DateUtil;
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
    public static final String PRICE_DRYING = "price_drying";
    public static final String PRICE_RINSE = "price_rinse";
    public static final String PRICE_COWBOY = "price_cowboy";
    public static final String PRICE_SHEETS = "price_sheets";
    public static final String PRICE_STANDARD = "price_standard";
    public static final String PRICE_WASHING_LIQUID = "price_washing_liquid";
    public static final String PRICE_SOFTENING = "price_softening";
    public static final String PRICE_DISINFECTION_ING = "price_disinfection_ing";
    public static final String PRICE_DISINFECTION_BEFORE = "price_disinfection_before";

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
    // 价格区变量
    private String dryingPriceMobile = "";
    private String dryingPriceCoin = "";
    private String rinsePriceMobile = "";
    private String rinsePriceCoin = "";
    private String sheetsPriceMobile = "";
    private String sheetsPriceCoin = "";
    private String cowboyPriceMobile = "";
    private String cowboyPriceCoin = "";
    private String standardPriceMobile = "";
    private String standardPriceCoin = "";
    private String washingLiquidPriceMobile = "";
    private String washingLiquidPriceCoin = "";
    private String softeningPriceMobile = "";
    private String softeningPriceCoin = "";
    private String disinfectionIngPriceMobile = "";
    private String disinfectionIngPriceCoin = "";
    private String disinfectionBeforePriceMobile = "";
    private String disinfectionBeforePriceCoin = "";

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
        // 有效洗衣机选择集合
        functionWashingMachinesSelect();
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
     * 有效洗衣机选择
     */
    private void functionWashingMachinesSelect() {
        findViewById(R.id.bt_washing_machine_1).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 1);
            }
        });
        findViewById(R.id.bt_washing_machine_2).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 2);
            }
        });
        findViewById(R.id.bt_washing_machine_3).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 3);
            }
        });
        findViewById(R.id.bt_washing_machine_4).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 4);
            }
        });
        findViewById(R.id.bt_washing_machine_5).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 5);
            }
        });
        findViewById(R.id.bt_washing_machine_6).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 6);
            }
        });
        findViewById(R.id.bt_washing_machine_7).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 7);
            }
        });
        findViewById(R.id.bt_washing_machine_8).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 8);
            }
        });
        findViewById(R.id.bt_washing_machine_9).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 9);
            }
        });
        findViewById(R.id.bt_washing_machine_10).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 10);
            }
        });
        findViewById(R.id.bt_washing_machine_11).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 11);
            }
        });
        findViewById(R.id.bt_washing_machine_12).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 12);
            }
        });
        findViewById(R.id.bt_washing_machine_13).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 13);
            }
        });
        findViewById(R.id.bt_washing_machine_14).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 14);
            }
        });
        findViewById(R.id.bt_washing_machine_15).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 15);
            }
        });
        findViewById(R.id.bt_washing_machine_16).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return selectEffectiveWash(v, event, 16);
            }
        });
    }

    /**
     * 选择洗衣机到洗衣机集合
     *
     * @param v
     * @param event
     * @param machinesId
     * @return
     */
    private boolean selectEffectiveWash(View v, MotionEvent event, int machinesId) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 播放按键声音
            playSound();
            // 定价按钮常亮
            v.setBackgroundResource(R.drawable.c09_bt_model_select_shape);
            // 添加到选择洗衣机集合
            for (WashingMachine machine : washingMachines) {
                if (machinesId == machine.getNum()) {
                    washingMachinesSelect.add(machine);
                }
            }
        }
        return false;
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
                return onTouchPrice(v, event, R.id.bt_drying, PRICE_DRYING);
            }
        });
        // 甩干移动支付
        findViewById(R.id.tv_drying_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_drying_price_mobile, "3");
            }
        });
        // 甩干硬币支付
        findViewById(R.id.tv_drying_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_drying_price_coin, "4");
            }
        });
        // 漂洗按钮
        findViewById(R.id.bt_rinse).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_rinse, PRICE_RINSE);
            }
        });
        // 漂洗移动支付
        findViewById(R.id.tv_rinse_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_rinse_price_mobile, "5");
            }
        });
        // 漂洗硬币支付
        findViewById(R.id.tv_rinse_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_rinse_price_coin, "6");
            }
        });
        // 牛仔按钮
        findViewById(R.id.bt_cowboy).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_cowboy, PRICE_COWBOY);
            }
        });
        // 牛仔移动支付
        findViewById(R.id.tv_cowboy_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_cowboy_price_mobile, "7");
            }
        });
        // 牛仔硬币支付
        findViewById(R.id.tv_cowboy_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_cowboy_price_coin, "8");
            }
        });
        // 被单按钮
        findViewById(R.id.bt_sheets).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_sheets, PRICE_SHEETS);
            }
        });
        // 被单移动支付
        findViewById(R.id.tv_sheets_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_sheets_price_mobile, "9");
            }
        });
        // 被单硬币支付
        findViewById(R.id.tv_sheets_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_sheets_price_coin, "10");
            }
        });
        // 标准按钮
        findViewById(R.id.bt_standard).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_standard, PRICE_STANDARD);
            }
        });
        // 标准移动支付
        findViewById(R.id.tv_standard_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_standard_price_mobile, "11");
            }
        });
        // 标准硬币支付
        findViewById(R.id.tv_standard_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_standard_price_coin, "12");
            }
        });
        // 洗液按钮
        findViewById(R.id.bt_washing_liquid).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_washing_liquid, PRICE_WASHING_LIQUID);
            }
        });
        // 洗液移动支付
        findViewById(R.id.tv_washing_liquid_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_washing_liquid_price_mobile, "13");
            }
        });
        // 洗液硬币支付
        findViewById(R.id.tv_washing_liquid_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_washing_liquid_price_coin, "14");
            }
        });
        // 柔顺按钮
        findViewById(R.id.bt_softening).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_softening, PRICE_SOFTENING);
            }
        });
        // 柔顺移动支付
        findViewById(R.id.tv_softening_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_softening_price_mobile, "15");
            }
        });
        // 柔顺硬币支付
        findViewById(R.id.tv_softening_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_softening_price_coin, "16");
            }
        });
        // 中消按钮
        findViewById(R.id.bt_disinfection_ing).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_disinfection_ing, PRICE_DISINFECTION_ING);
            }
        });
        // 中消移动支付
        findViewById(R.id.tv_disinfection_ing_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_disinfection_ing_price_mobile, "17");
            }
        });
        // 中消硬币支付
        findViewById(R.id.tv_disinfection_ing_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_disinfection_ing_price_coin, "18");
            }
        });
        // 前消按钮
        findViewById(R.id.bt_disinfection_before).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPrice(v, event, R.id.bt_disinfection_before, PRICE_DISINFECTION_BEFORE);
            }
        });
        // 前消移动支付
        findViewById(R.id.tv_disinfection_before_price_mobile).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_disinfection_before_price_mobile, "19");
            }
        });
        // 前消硬币支付
        findViewById(R.id.tv_disinfection_before_price_coin).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onTouchPricePay(v, event, R.id.tv_disinfection_before_price_coin, "20");
            }
        });
    }

    /**
     * 定价价格区逻辑
     *
     * @param v
     * @param event
     * @param tvId
     * @param tvSelectModel
     * @return
     */
    private boolean onTouchPricePay(View v, MotionEvent event, int tvId, String tvSelectModel) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (PRICE_DRYING.equals(modelPrice)) {
                // 甩干按钮选择状态
                v.setBackgroundResource(R.drawable.c09_tv_select_shape);
                // 还原其他定价按钮状态
                restoreTvPriceButton(tvId);
                getTvSelectModel = tvSelectModel;
            }
        }
        return false;
    }

    /**
     * 定价模式按钮逻辑
     *
     * @param v
     * @param event
     * @param btId
     * @param priceModel
     * @return
     */
    private boolean onTouchPrice(View v, MotionEvent event, int btId, String priceModel) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 播放按键声音
            playSound();
            if (!washingMachinesSelect.isEmpty()) {
                // 甩干按钮选择状态
                v.setBackgroundResource(R.drawable.c09_bt_accessories_select_shape);
                // 还原其他定价按钮状态
                restorePriceButton(btId);
                modelPrice = priceModel;
            } else {
                alertMsg("Tips", "请先选择洗衣机！");
            }
        }
        return false;
    }

    /**
     * 还原定价区价格状态
     *
     * @param tvId
     */
    private void restoreTvPriceButton(int tvId) {
        int[] ints = {R.id.tv_drying_price_mobile,
                R.id.tv_drying_price_coin,
                R.id.tv_rinse_price_mobile,
                R.id.tv_rinse_price_coin,
                R.id.tv_cowboy_price_mobile,
                R.id.tv_cowboy_price_coin,
                R.id.tv_sheets_price_mobile,
                R.id.tv_sheets_price_coin,
                R.id.tv_standard_price_mobile,
                R.id.tv_standard_price_coin,
                R.id.tv_washing_liquid_price_mobile,
                R.id.tv_washing_liquid_price_coin,
                R.id.tv_softening_price_mobile,
                R.id.tv_softening_price_coin,
                R.id.tv_disinfection_ing_price_mobile,
                R.id.tv_disinfection_ing_price_coin,
                R.id.tv_disinfection_before_price_mobile,
                R.id.tv_disinfection_before_price_coin};
        for (int i : ints) {
            if (i != tvId) {
                findViewById(i).setBackgroundResource(R.drawable.c09_tv_price_shape);
            }
        }
    }

    /**
     * 还原定价区按钮状态
     *
     * @param btId
     */
    private void restorePriceButton(int btId) {
        int[] ints = {R.id.bt_drying,
                R.id.bt_rinse,
                R.id.bt_cowboy,
                R.id.bt_sheets,
                R.id.bt_standard,
                R.id.bt_washing_liquid,
                R.id.bt_softening,
                R.id.bt_disinfection_ing,
                R.id.bt_disinfection_before};
        for (int i : ints) {
            if (i != btId) {
                findViewById(i).setBackgroundResource(R.drawable.c09_bt_accessories_shape);
            }
        }
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
                            insertPassword("1", getTvSelectModel);
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
                            insertPassword("2", getTvSelectModel);
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
                            insertPassword("3", getTvSelectModel);
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
                            insertPassword("4", getTvSelectModel);
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
                            insertPassword("5", getTvSelectModel);
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
                            insertPassword("6", getTvSelectModel);
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
                            insertPassword("7", getTvSelectModel);
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
                            insertPassword("8", getTvSelectModel);
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
                            insertPassword("9", getTvSelectModel);
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
                            insertPassword("0", getTvSelectModel);
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
                                try {
                                    dbWrit.execSQL("insert into password_rule(rule, state) values ('" + newPasswordOne + "', '1')");
                                    dbWrit.execSQL("update password_rule set state = '0' where id = " + passwordRule.getId() + "");
                                } finally {
                                    dbWrit.close();
                                }
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
                            if ("".equals(modelPrice)) {
                                // 验证密码点亮按钮
                                modelButtonLight(PRICE);
                            } else {
                                // 修改数据入库
                                modifyPrice();
                                // 重置定价参数
                                resetPriceProperty();
                            }
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
                            if ("".equals(modelPrice)) {
                                // 重置密码输入框
                                clearPassword();
                            } else {
                                // 重置定价模块价格框
                                cleatPriceModel();
                            }
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
     * 重置定价属性
     */
    private void resetPriceProperty() {
        // 定价按钮还原
        findViewById(R.id.bt_accessories).setBackgroundResource(R.drawable.c09_bt_accessories_shape);
        // 定价选择区按钮置灰
        ashPriceButton(false);
        // 模块选择还原
        model = "";
        // 显示框选择还原
        getTvSelectModel = "";
        // 定价区选择模块还原
        modelPrice = "";
        // 定价模块按钮还原
        restoreTvPriceButton(0);
        restorePriceButton(0);
        // 价格区变量还原
        dryingPriceMobile = "";
        dryingPriceCoin = "";
        rinsePriceMobile = "";
        rinsePriceCoin = "";
        sheetsPriceMobile = "";
        sheetsPriceCoin = "";
        cowboyPriceMobile = "";
        cowboyPriceCoin = "";
        standardPriceMobile = "";
        standardPriceCoin = "";
        washingLiquidPriceMobile = "";
        washingLiquidPriceCoin = "";
        softeningPriceMobile = "";
        softeningPriceCoin = "";
        disinfectionIngPriceMobile = "";
        disinfectionIngPriceCoin = "";
        disinfectionBeforePriceMobile = "";
        disinfectionBeforePriceCoin = "";
        // 洗衣机按钮置灰
        ashWashingMachineButton(washingMachinesSelect, false);
        // 选中洗衣机集合置空
        washingMachinesSelect = new ArrayList<>();

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
     * 修改定价入库
     */
    private void modifyPrice() {
        sqLiteDbHelper = new SQLiteDbHelper(getApplicationContext());
        SQLiteDatabase dbWrit = sqLiteDbHelper.getWritableDatabase();
        try {
            for (WashingMachine machine : washingMachinesSelect) {
                ContentValues cv = new ContentValues();
                cv.put("cowboy_price_coin", machine.getCowboyPriceCoin());
                cv.put("cowboy_price_mobile", machine.getCowboyPriceMobile());
                cv.put("disinfection_before_price_coin", machine.getDisinfectionBeforePriceCoin());
                cv.put("disinfection_before_price_mobile", machine.getDisinfectionBeforePriceMobile());
                cv.put("disinfection_ing_price_coin", machine.getDisinfectionIngPriceCoin());
                cv.put("disinfection_ing_price_mobile", machine.getDisinfectionIngPriceMobile());
                cv.put("drying_price_coin", machine.getDryingPriceCoin());
                cv.put("drying_price_mobile", machine.getDryingPriceMobile());
                cv.put("rinse_price_coin", machine.getRinsePriceCoin());
                cv.put("rinse_price_mobile", machine.getRinsePriceMobile());
                cv.put("sheets_price_coin", machine.getSheetsPriceCoin());
                cv.put("sheets_price_mobile", machine.getSheetsPriceMobile());
                cv.put("softening_price_coin", machine.getSofteningPriceCoin());
                cv.put("softening_price_mobile", machine.getSofteningPriceMobile());
                cv.put("standard_price_coin", machine.getStandardPriceCoin());
                cv.put("standard_price_mobile", machine.getStandardPriceMobile());
                cv.put("washing_liquid_price_coin", machine.getWashingLiquidPriceCoin());
                cv.put("washing_liquid_price_mobile", machine.getWashingLiquidPriceMobile());
                cv.put("update_time", DateUtil.getCurrDateTime());
                dbWrit.update("washing_machine",
                        cv,
                        "Id = ?",
                        new String[]{String.valueOf(machine.getId())});
            }
        } finally {
            dbWrit.close();
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
     * 重置定价模块价格框
     */
    private void cleatPriceModel() {
        TextView tv;
        switch (modelPrice) {
            case PRICE_DRYING:
                dryingPriceMobile = "";
                tv = findViewById(R.id.tv_drying_price_mobile);
                tv.setText(dryingPriceMobile);
                dryingPriceCoin = "";
                tv = findViewById(R.id.tv_drying_price_coin);
                tv.setText(dryingPriceCoin);
                break;
            case PRICE_RINSE:
                rinsePriceMobile = "";
                tv = findViewById(R.id.tv_rinse_price_mobile);
                tv.setText(rinsePriceMobile);
                rinsePriceCoin = "";
                tv = findViewById(R.id.tv_rinse_price_coin);
                tv.setText(rinsePriceCoin);
                break;
            case PRICE_COWBOY:
                cowboyPriceMobile = "";
                tv = findViewById(R.id.tv_cowboy_price_mobile);
                tv.setText(cowboyPriceMobile);
                cowboyPriceCoin = "";
                tv = findViewById(R.id.tv_cowboy_price_coin);
                tv.setText(cowboyPriceCoin);
                break;
            case PRICE_SHEETS:
                sheetsPriceMobile = "";
                tv = findViewById(R.id.tv_sheets_price_mobile);
                tv.setText(sheetsPriceMobile);
                sheetsPriceCoin = "";
                tv = findViewById(R.id.tv_sheets_price_coin);
                tv.setText(sheetsPriceCoin);
                break;
            case PRICE_STANDARD:
                standardPriceMobile = "";
                tv = findViewById(R.id.tv_standard_price_mobile);
                tv.setText(standardPriceMobile);
                standardPriceCoin = "";
                tv = findViewById(R.id.tv_standard_price_coin);
                tv.setText(standardPriceCoin);
                break;
            case PRICE_WASHING_LIQUID:
                washingLiquidPriceMobile = "";
                tv = findViewById(R.id.tv_washing_liquid_price_mobile);
                tv.setText(washingLiquidPriceMobile);
                washingLiquidPriceCoin = "";
                tv = findViewById(R.id.tv_washing_liquid_price_coin);
                tv.setText(washingLiquidPriceCoin);
                break;
            case PRICE_SOFTENING:
                softeningPriceMobile = "";
                tv = findViewById(R.id.tv_softening_price_mobile);
                tv.setText(softeningPriceMobile);
                softeningPriceCoin = "";
                tv = findViewById(R.id.tv_softening_price_coin);
                tv.setText(softeningPriceCoin);
                break;
            case PRICE_DISINFECTION_ING:
                disinfectionIngPriceMobile = "";
                tv = findViewById(R.id.tv_disinfection_ing_price_mobile);
                tv.setText(disinfectionIngPriceMobile);
                disinfectionIngPriceCoin = "";
                tv = findViewById(R.id.tv_disinfection_ing_price_coin);
                tv.setText(disinfectionIngPriceCoin);
                break;
            case PRICE_DISINFECTION_BEFORE:
                disinfectionBeforePriceMobile = "";
                tv = findViewById(R.id.tv_disinfection_before_price_mobile);
                tv.setText(disinfectionBeforePriceMobile);
                disinfectionBeforePriceCoin = "";
                tv = findViewById(R.id.tv_disinfection_before_price_coin);
                tv.setText(disinfectionBeforePriceCoin);
                break;
        }
    }

    /**
     * 密码输入
     *
     * @param num
     * @param getTvSelectModel
     */
    private void insertPassword(String num, String getTvSelectModel) {
        TextView tv;
        switch (getTvSelectModel) {
            case "3":
                if (dryingPriceMobile.length() < 2) {
                    dryingPriceMobile = dryingPriceMobile + num;
                } else if (dryingPriceMobile.length() == 2 && dryingPriceMobile.length() < 3) {
                    dryingPriceMobile = dryingPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_drying_price_mobile);
                tv.setText(dryingPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDryingPriceMobile(dryingPriceMobile);
                }
                break;
            case "4":
                if (dryingPriceCoin.length() < 2) {
                    dryingPriceCoin = dryingPriceCoin + num;
                } else if (dryingPriceCoin.length() == 2 && dryingPriceCoin.length() < 3) {
                    dryingPriceCoin = dryingPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_drying_price_coin);
                tv.setText(dryingPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDryingPriceCoin(dryingPriceCoin);
                }
                break;
            case "5":
                if (rinsePriceMobile.length() < 2) {
                    rinsePriceMobile = rinsePriceMobile + num;
                } else if (rinsePriceMobile.length() == 2 && rinsePriceMobile.length() < 3) {
                    rinsePriceMobile = rinsePriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_rinse_price_mobile);
                tv.setText(rinsePriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setRinsePriceMobile(rinsePriceMobile);
                }
                break;
            case "6":
                if (rinsePriceCoin.length() < 2) {
                    rinsePriceCoin = rinsePriceCoin + num;
                } else if (rinsePriceCoin.length() == 2 && rinsePriceCoin.length() < 3) {
                    rinsePriceCoin = rinsePriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_rinse_price_coin);
                tv.setText(rinsePriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setRinsePriceCoin(rinsePriceCoin);
                }
                break;
            case "7":
                if (cowboyPriceMobile.length() < 2) {
                    cowboyPriceMobile = cowboyPriceMobile + num;
                } else if (cowboyPriceMobile.length() == 2 && cowboyPriceMobile.length() < 3) {
                    cowboyPriceMobile = cowboyPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_cowboy_price_mobile);
                tv.setText(cowboyPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setCowboyPriceMobile(cowboyPriceMobile);
                }
                break;
            case "8":
                if (cowboyPriceCoin.length() < 2) {
                    cowboyPriceCoin = cowboyPriceCoin + num;
                } else if (cowboyPriceCoin.length() == 2 && cowboyPriceCoin.length() < 3) {
                    cowboyPriceCoin = cowboyPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_cowboy_price_coin);
                tv.setText(cowboyPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setCowboyPriceCoin(cowboyPriceCoin);
                }
                break;
            case "9":
                if (sheetsPriceMobile.length() < 2) {
                    sheetsPriceMobile = sheetsPriceMobile + num;
                } else if (sheetsPriceMobile.length() == 2 && sheetsPriceMobile.length() < 3) {
                    sheetsPriceMobile = sheetsPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_sheets_price_mobile);
                tv.setText(sheetsPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setSheetsPriceMobile(sheetsPriceMobile);
                }
                break;
            case "10":
                if (sheetsPriceCoin.length() < 2) {
                    sheetsPriceCoin = sheetsPriceCoin + num;
                } else if (sheetsPriceCoin.length() == 2 && sheetsPriceCoin.length() < 3) {
                    sheetsPriceCoin = sheetsPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_sheets_price_coin);
                tv.setText(sheetsPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setSheetsPriceCoin(sheetsPriceCoin);
                }
                break;
            case "11":
                if (standardPriceMobile.length() < 2) {
                    standardPriceMobile = standardPriceMobile + num;
                } else if (standardPriceMobile.length() == 2 && standardPriceMobile.length() < 3) {
                    standardPriceMobile = standardPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_standard_price_mobile);
                tv.setText(standardPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setStandardPriceMobile(standardPriceMobile);
                }
                break;
            case "12":
                if (standardPriceCoin.length() < 2) {
                    standardPriceCoin = standardPriceCoin + num;
                } else if (standardPriceCoin.length() == 2 && standardPriceCoin.length() < 3) {
                    standardPriceCoin = standardPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_standard_price_coin);
                tv.setText(standardPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setStandardPriceCoin(standardPriceCoin);
                }
                break;
            case "13":
                if (washingLiquidPriceMobile.length() < 2) {
                    washingLiquidPriceMobile = washingLiquidPriceMobile + num;
                } else if (washingLiquidPriceMobile.length() == 2 && washingLiquidPriceMobile.length() < 3) {
                    washingLiquidPriceMobile = washingLiquidPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_washing_liquid_price_mobile);
                tv.setText(washingLiquidPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setWashingLiquidPriceMobile(washingLiquidPriceMobile);
                }
                break;
            case "14":
                if (washingLiquidPriceCoin.length() < 2) {
                    washingLiquidPriceCoin = washingLiquidPriceCoin + num;
                } else if (washingLiquidPriceCoin.length() == 2 && washingLiquidPriceCoin.length() < 3) {
                    washingLiquidPriceCoin = washingLiquidPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_washing_liquid_price_coin);
                tv.setText(washingLiquidPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setWashingLiquidPriceCoin(washingLiquidPriceCoin);
                }
                break;
            case "15":
                if (softeningPriceMobile.length() < 2) {
                    softeningPriceMobile = softeningPriceMobile + num;
                } else if (softeningPriceMobile.length() == 2 && softeningPriceMobile.length() < 3) {
                    softeningPriceMobile = softeningPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_softening_price_mobile);
                tv.setText(softeningPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setSofteningPriceMobile(softeningPriceMobile);
                }
                break;
            case "16":
                if (softeningPriceCoin.length() < 2) {
                    softeningPriceCoin = softeningPriceCoin + num;
                } else if (softeningPriceCoin.length() == 2 && softeningPriceCoin.length() < 3) {
                    softeningPriceCoin = softeningPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_softening_price_coin);
                tv.setText(softeningPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setSofteningPriceCoin(softeningPriceCoin);
                }
                break;
            case "17":
                if (disinfectionIngPriceMobile.length() < 2) {
                    disinfectionIngPriceMobile = disinfectionIngPriceMobile + num;
                } else if (disinfectionIngPriceMobile.length() == 2 && disinfectionIngPriceMobile.length() < 3) {
                    disinfectionIngPriceMobile = disinfectionIngPriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_disinfection_ing_price_mobile);
                tv.setText(disinfectionIngPriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDisinfectionIngPriceMobile(disinfectionIngPriceMobile);
                }
                break;
            case "18":
                if (disinfectionIngPriceCoin.length() < 2) {
                    disinfectionIngPriceCoin = disinfectionIngPriceCoin + num;
                } else if (disinfectionIngPriceCoin.length() == 2 && disinfectionIngPriceCoin.length() < 3) {
                    disinfectionIngPriceCoin = disinfectionIngPriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_disinfection_ing_price_coin);
                tv.setText(disinfectionIngPriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDisinfectionIngPriceCoin(disinfectionIngPriceCoin);
                }
                break;
            case "19":
                if (disinfectionBeforePriceMobile.length() < 2) {
                    disinfectionBeforePriceMobile = disinfectionBeforePriceMobile + num;
                } else if (disinfectionBeforePriceMobile.length() == 2 && disinfectionBeforePriceMobile.length() < 3) {
                    disinfectionBeforePriceMobile = disinfectionBeforePriceMobile + "." + num;
                }
                tv = findViewById(R.id.tv_disinfection_before_price_mobile);
                tv.setText(disinfectionBeforePriceMobile);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDisinfectionBeforePriceMobile(disinfectionBeforePriceMobile);
                }
                break;
            case "20":
                if (disinfectionBeforePriceCoin.length() < 2) {
                    disinfectionBeforePriceCoin = disinfectionBeforePriceCoin + num;
                } else if (disinfectionBeforePriceCoin.length() == 2 && disinfectionBeforePriceCoin.length() < 3) {
                    disinfectionBeforePriceCoin = disinfectionBeforePriceCoin + "." + num;
                }
                tv = findViewById(R.id.tv_disinfection_before_price_coin);
                tv.setText(disinfectionBeforePriceCoin);
                for (WashingMachine machine : washingMachinesSelect) {
                    machine.setDisinfectionBeforePriceCoin(disinfectionBeforePriceCoin);
                }
                break;
            default:
                stringTx = stringTx + num;
                tv = findViewById(R.id.tv_password);
                tv.setText(stringTx);
                break;
        }
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
