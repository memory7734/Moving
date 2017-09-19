package me.memory7734.moving.insert;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;

/**
 * Created by Elijah on 16/10/24.
 */

public interface InsertContract {
    interface View extends BaseView<InsertContract.Presenter> {


        void initDataCategory();

        void initFitnessDataType();

        void initMedicalDataType();

        void initBodyDataType();

        void initDate();

        void initValue();
    }

    interface Presenter extends BasePresenter {

        void setCanBeSaved(boolean saved);

        float getValue(int dataType, int currentDate);

        void setValue(int dataType, int currentDate, float value);

        boolean save();

    }
}
