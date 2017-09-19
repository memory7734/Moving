package me.memory7734.moving.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.memory7734.moving.bean.DataBean;
import me.memory7734.moving.bean.HealthItem;
import me.memory7734.moving.app.Constant;
import me.memory7734.moving.util.TimeUtils;

/**
 * Created by Elijah on 2016-9-23.
 */

public class DataDao {
    private DataBaseHelper helper = null;
    private SQLiteDatabase database;

    public DataDao(Context context) {
        if (helper == null) {
            helper = new DataBaseHelper(context);
            database = helper.getWritableDatabase();
            if (queryDataBean(19010101) == null) {
                insertOrUpdateDataBean(new DataBean(19010101,Constant.DATA_TYPE_GOAL));
            }
        }
    }

    /**
     * 删除指定日期的数据
     * @param date
     */
    public void deleteDataBean(int date) {
        if (queryDataBean(date) != null) {
            database.delete(Constant.DATABASE_TABLE_NAME, "date_time=?", new String[]{String.valueOf(date)});
        }
    }

    /**
     * 删除全部数据
     */
    public void deleteAll() {
        database.delete(Constant.DATABASE_TABLE_NAME, null, null);
    }



    public void insertOrUpdateData(int dataType, int date, float value) {
        if (queryDataBean(date) == null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_time", date);
            contentValues.put(Constant.DATA_TYPE_NAME[dataType], value);
            database.insert(Constant.DATABASE_TABLE_NAME, null, contentValues);
        }else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constant.DATA_TYPE_NAME[dataType], value);
            database.update(Constant.DATABASE_TABLE_NAME, contentValues, "date_time=?", new String[]{String.valueOf(date)});

        }
    }

    /**
     * 更新某一条数据
     * @param dataBean
     */
    public void insertOrUpdateDataBean(DataBean dataBean) {
        if (queryDataBean(dataBean.getDate_time()) != null) {
            database.delete(Constant.DATABASE_TABLE_NAME, "date_time=?", new String[]{String.valueOf(dataBean.getDate_time())});
        }
        ContentValues contentValues = new ContentValues();
        //将数据按照一一对应的关系存储到ContentValues中
        contentValues.put("date_time", dataBean.getDate_time());
        //values的顺序是与名称相对应的，所以每个值的名称和数值也是相对应的
        float[] values = dataBean.getValues();
        for (int i = 0; i < values.length; i++) {
            contentValues.put(Constant.DATA_TYPE_NAME[i], values[i]);
        }
        database.insert(Constant.DATABASE_TABLE_NAME, null, contentValues);
    }

    /**
     * 查询某天某种类型的数据
     * @param date
     * @param dataType
     * @return
     */
    public float queryData(int dataType, int date) {
        Cursor cursor = database.rawQuery("SELECT " + Constant.DATA_TYPE_NAME[dataType] + " FROM " + Constant.DATABASE_TABLE_NAME + " WHERE date_time = " + date, null);
        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getFloat(cursor.getColumnIndex(Constant.DATA_TYPE_NAME[dataType]));
        }
        cursor.close();
        return 0;
    }

    /**
     * 查询最近n天的某类数据
     * @param dataType
     * @param date
     * @param amount
     * @return
     */
    public float[] queryDataValues(int dataType, int date, int amount) {
        float[] values = new float[amount];
        for (int i = 0; i < amount; i++) {
            values[i] = queryData(dataType, TimeUtils.add(date, i - amount + 1));
        }
        return values;
    }

    /**
     * 获取DataItem
     * @param dataType
     * @param date
     * @param amount
     * @return
     */
    public HealthItem queryDataItem(int dataType, int date, int amount) {
        return new HealthItem(dataType, queryDataValues(dataType, date, amount));
    }

    /**
     * 查询某一天的全部数据
     * @param date
     * @return
     */
    public DataBean queryDataBean(int date) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + Constant.DATABASE_TABLE_NAME + " WHERE date_time = " + date, null);
        if (cursor != null && cursor.moveToFirst()) {
            ArrayList<Float> floats = new ArrayList<>();
            for (int i = 0; i < Constant.DATA_TYPE_NAME.length; i++) {
                floats.add(cursor.getFloat(cursor.getColumnIndex(Constant.DATA_TYPE_NAME[i])));
            }

            float[] values = new float[floats.size()];
            for (int i = 0; i < floats.size(); i++) {
                values[i] = floats.get(i);
            }
            DataBean data = new DataBean(date, values);
            cursor.close();
            return data;
        }
        cursor.close();
        return null;
    }

    /**
     * 查询最近n天的全部数据
     * @param date 当前日期
     * @param amount 往前的天数
     * @return
     */
    public List<DataBean> queryDataBeanList(int date, int amount) {
        List<DataBean> list = new ArrayList<>();
        for (int i = -amount + 1; i <= 0; i++) {
            DataBean dataBean = queryDataBean(TimeUtils.add(date, i));
            if (dataBean == null) {
                dataBean = new DataBean(TimeUtils.add(date, i));
            }
            list.add(dataBean);
        }
        return list;
    }

    /**
     * 获得全部数据
     * @return
     */
    public List<DataBean> queryAll() {
        List<DataBean> list = new ArrayList<>();
        Cursor cursor = database.query(Constant.DATABASE_TABLE_NAME, null, null, null, null, null, "date_time ASC");
        while (cursor.moveToNext()) {
            int date = cursor.getInt(cursor.getColumnIndex("date_time"));
            DataBean data = queryDataBean(date);
            list.add(data);
        }
        cursor.close();
        return list;
    }
}
