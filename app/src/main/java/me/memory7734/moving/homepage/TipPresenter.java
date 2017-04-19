package me.memory7734.moving.homepage;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.memory7734.moving.app.Constant;
import me.memory7734.moving.bean.CircleItem;
import me.memory7734.moving.bean.DataBean;
import me.memory7734.moving.bean.TipItem;
import me.memory7734.moving.database.DataDao;
import me.memory7734.moving.util.TimeUtils;

/**
 * Created by Elijah on 16/11/24.
 */

public class TipPresenter implements TipContract.Presenter {

    private TipContract.View view;
    private SharedPreferences sharedPreferences;

    private List<CircleItem> mCircleItemList = new ArrayList<>();
    private List<TipItem> mTipItemList = new ArrayList<>();
    private DataDao dao;

    public TipPresenter(Context context, TipContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
        dao = new DataDao(context);
        sharedPreferences = context.getSharedPreferences("system_settings", Context.MODE_PRIVATE);
    }

    @Override
    public void start() {
        loadPosts();
        view.showResults(mCircleItemList, mTipItemList);
    }

    @Override
    public void loadPosts() {
        int currentDate = TimeUtils.getIntFromCalendar(Calendar.getInstance());
        DataBean dataBean = dao.queryDataBean(currentDate);
        DataBean goalBean = dao.queryDataBean(19010101);
        if (dataBean != null) {
            for (int i = 0; i < Constant.DATA_TYPE_FITNESS_NUMBER; i++) {
                mCircleItemList.add(new CircleItem(Constant.DATA_TYPE_NAME_ZH_CN[i], goalBean.getValues()[i], dataBean.getValues()[i]));
            }
        } else {
            for (int i = 0; i < Constant.DATA_TYPE_FITNESS_NUMBER; i++) {
                mCircleItemList.add(new CircleItem(Constant.DATA_TYPE_NAME_ZH_CN[i], goalBean.getValues()[i], 0));
            }
        }



//        TipItem tipItem = new TipItem(1, "ti", "tttt");
//        mTipItemList.add(tipItem);
//        tipItem = new TipItem(2, "ddd", "ddddd");
//        mTipItemList.add(tipItem);
    }

}
