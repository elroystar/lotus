package com.lotus.lotusapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.lotus.lotusapp.db.SQLiteDbHelper;
import com.lotus.lotusapp.dto.PasswordRule;

public class C09Activity extends AppCompatActivity {

    public static final String MODIFY_PASSWORD = "modify_password";
    public static final String OUT_MONEY = "out_money";
    public static final String PRICE = "price";
    public static final String TEST = "test";

    // C界面模式选择，默认为""为修改密码模式
    private String model = MODIFY_PASSWORD;

    // 密码规则
    private PasswordRule passwordRule;

    // 数据连接工具
    private SQLiteDbHelper sqLiteDbHelper;

    private String newPasswordOne = "";
    private String newPasswordTwo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c09);
        // 获取显示密码规则
        showPasswordRule();
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
        TextView tv = findViewById(R.id.tv_password_new_one);
        tv.setText(passwordRule.getRule());
        tv = findViewById(R.id.tv_password_new_two);
        tv.setText(passwordRule.getRule());
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
        if (newPasswordOne.length() <= 5) {
            newPasswordOne = newPasswordOne + num;
            TextView tv = findViewById(R.id.tv_password_new_one);
            tv.setText(newPasswordOne);
        } else if (newPasswordOne.length() == 6 && newPasswordTwo.length() <= 5) {
            newPasswordTwo = newPasswordTwo + num;
            TextView tv = findViewById(R.id.tv_password_new_two);
            tv.setText(newPasswordTwo);
        }
    }
}
