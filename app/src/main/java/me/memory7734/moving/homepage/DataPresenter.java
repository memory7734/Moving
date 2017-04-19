package me.memory7734.moving.homepage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.memory7734.moving.bean.HealthItem;
import me.memory7734.moving.database.DataDao;
import me.memory7734.moving.app.Constant;
import me.memory7734.moving.util.TimeUtils;


/**
 * Created by Elijah on 16/10/26.
 */

public class DataPresenter implements DataContract.Presenter {

    private DataContract.View view;
    private Context context;
    private SharedPreferences sharedPreferences;

    private List<HealthItem> list = new ArrayList<>();
    private DataDao dao;

    public DataPresenter(Context context, DataContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        dao = new DataDao(context);
        sharedPreferences = context.getSharedPreferences("system_settings", Context.MODE_PRIVATE);
    }


    @Override
    public void start(int dataType) {
        list.clear();
        loadPosts(dataType);
        view.showResults(list);
    }



    @Override
    public void loadPosts(int dataType) {
        int duration = sharedPreferences.getInt("time_of_show_datas", 7);
        int currentDate = TimeUtils.getIntFromCalendar(Calendar.getInstance());
        if (dataType == Constant.DATA_TYPE_FITNESS) {
            for (int i = 0; i < Constant.DATA_TYPE_FITNESS_NUMBER; i++) {
                list.add(dao.queryDataItem(i, currentDate, duration));
            }
        } else if (dataType == Constant.DATA_TYPE_MEDICAL) {
            for (int i = 0; i < Constant.DATA_TYPE_MEDICAL_NUMBER; i++) {
                int j = i + Constant.DATA_TYPE_FITNESS_NUMBER;
                list.add(dao.queryDataItem(j, currentDate, duration));
            }
        } else {
            for (int i = 0; i < Constant.DATA_TYPE_BODY_NUMBER; i++) {
                int j = i + Constant.DATA_TYPE_FITNESS_NUMBER + Constant.DATA_TYPE_MEDICAL_NUMBER;
                list.add(dao.queryDataItem(j, currentDate, duration));
            }
        }
    }


    @Override
    public void start() {

    }
}
