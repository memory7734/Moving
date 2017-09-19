package me.memory7734.moving.insert;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import me.memory7734.moving.R;
import me.memory7734.moving.database.DataDao;

/**
 * Created by Elijah on 16/10/24.
 */
public class InsertPresenter implements InsertContract.Presenter {

    private InsertContract.View view;
    private AppCompatActivity activity;

    private DataDao dao;
    private boolean saved;
    private int dataType;
    private int currentDate;
    private float value;

    public InsertPresenter(AppCompatActivity activity, InsertContract.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);
        dao = new DataDao(activity.getBaseContext());
    }

    @Override
    public void start() {

    }

    @Override
    public float getValue(int dataType, int currentDate) {
        return dao.queryData(dataType, currentDate);
    }

    @Override
    public void setCanBeSaved(boolean saved) {
        this.saved = saved;
    }


    @Override
    public void setValue(int dataType, int currentDate, float value) {
        this.dataType = dataType;
        this.currentDate = currentDate;
        this.value = value;
    }

    @Override
    public boolean save() {
        if (saved) {
            dao.insertOrUpdateData(dataType, currentDate, value);
            return true;
        } else {
            Snackbar.make(activity.findViewById(R.id.toolbar), activity.getString(R.string.please_input_value), Snackbar.LENGTH_SHORT).show();
            return false;
        }
    }
}
