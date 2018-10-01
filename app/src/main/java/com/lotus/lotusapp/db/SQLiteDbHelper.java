package com.lotus.lotusapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lotus.lotusapp.utils.DateUtil;

/**
 * 数据库工具类
 */
public class SQLiteDbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 3;
    private static final String DB_NAME = "lotusDB";
    private static final String TABLE_USER = "user";
    private static final String TABLE_PASSWORD_BANK = "password_bank";
    private static final String TABLE_PASSWORD_RULE = "password_rule";
    private static final String TABLE_WASHING_MACHINE = "washing_machine";
    // 创建 user 表的 sql 语句
    private static final String USER_CREATE_TABLE_SQL = "create table " + TABLE_USER + "("
            + "id integer primary key autoincrement,"
            + "phone varchar(11) not null,"
            + "washing_num integer default 0,"
            + "washing_total integer default 0,"
            + "reward_num integer default 0,"
            + "reward_total integer default 0,"
            + "create_time timestamp default (datetime('now','localtime')),"
            + "update_time timestamp default (datetime('now','localtime'))"
            + ");";
    // 创建 password_bank 表的 sql 语句
    private static final String PASSWORD_BANK_CREATE_TABLE_SQL = "create table " + TABLE_PASSWORD_BANK + "("
            + "id integer primary key autoincrement,"
            + "password varchar(64) not null,"
            + "create_time timestamp default (datetime('now','localtime'))"
            + ");";
    // 创建 password_rule 表的 sql 语句
    private static final String PASSWORD_RULE_CREATE_TABLE_SQL = "create table " + TABLE_PASSWORD_RULE + "("
            + "id integer primary key autoincrement,"
            + "rule varchar(6) not null,"
            + "state varchar(2) not null default '0',"
            + "create_time timestamp default (datetime('now','localtime'))"
            + ");";
    // 创建 wash_price 表的 sql 语句
    private static final String WASHING_MACHINE_CREATE_TABLE_SQL = "create table " + TABLE_WASHING_MACHINE + "("
            + "id integer primary key autoincrement,"
            + "num integer not null,"
            + "state varchar(2) not null default '1',"
            + "washing_liquid_state varchar(2) not null default '1',"
            + "rinse_state varchar(2) not null default '1',"
            + "disinfection_state varchar(2) not null default '1',"
            + "cowboy_price varchar(10) not null default '0',"
            + "disinfection_before_price varchar(10) not null default '0',"
            + "disinfection_ing_price varchar(10) not null default '0',"
            + "drying_price varchar(10) not null default '0',"
            + "rinse_price varchar(10) not null default '0',"
            + "sheets_price varchar(10) not null default '0',"
            + "softening_price varchar(10) not null default '0',"
            + "standard_price varchar(10) not null default '0',"
            + "washing_liquid_price varchar(10) not null default '0',"
            + "create_time timestamp default (datetime('now','localtime')),"
            + "update_time timestamp default (datetime('now','localtime'))"
            + ");";
    // 密码规则库默认规则333333
    private static final String PASSWORD_RULE_INSERT_DEFAULT_SQL = "insert into password_rule(rule, state) values ('333333', '1')";
    private static final String WASHING_MACHINE_INSERT_TEST_SQL = "insert into washing_machine(num) values (1)";

    public SQLiteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 在这里通过 db.execSQL 函数执行 SQL 语句创建所需要的表
        // 创建 user 表
        db.execSQL(USER_CREATE_TABLE_SQL);
        // 创建 password_bank 表
        db.execSQL(PASSWORD_BANK_CREATE_TABLE_SQL);
        // 创建 password_rule 表
        db.execSQL(PASSWORD_RULE_CREATE_TABLE_SQL);
        // 创建 wash_price 表
        db.execSQL(WASHING_MACHINE_CREATE_TABLE_SQL);
        // 创建 washing_machine 表
        db.execSQL(WASHING_MACHINE_CREATE_TABLE_SQL);
        // 添加默认密码规则
        db.execSQL(PASSWORD_RULE_INSERT_DEFAULT_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 创建 washing_machine 表
        db.execSQL(WASHING_MACHINE_CREATE_TABLE_SQL);
        // washing_machine 表 添加测试数据
        db.execSQL(WASHING_MACHINE_INSERT_TEST_SQL);

    }
}
