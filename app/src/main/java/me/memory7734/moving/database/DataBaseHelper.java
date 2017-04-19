package me.memory7734.moving.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.memory7734.moving.app.Constant;


/**
 * Created by Elijah on 2016-9-23.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private final static int VERSION = 1;


    public DataBaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, VERSION);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建数据库
     * 从Constant中读取数据名称
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.DATABASE_TABLE_NAME);
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE " + Constant.DATABASE_TABLE_NAME +
                "(date_time integer PRIMARY KEY NOT NULL");
        for (String string : Constant.DATA_TYPE_NAME) {
            sql.append("," + string + " float");
        }
        sql.append(")");

        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

}
