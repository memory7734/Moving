package me.memory7734.moving.viewitem;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;
import me.memory7734.moving.bean.HealthItem;

/**
 * Created by Elijah on 16/10/25.
 */

public interface LineCardContract {
    interface View extends BaseView<LineCardContract.Presenter> {

        void initData();

        void showView(HealthItem healthItem);

        void refreshView(HealthItem healthItem);

    }

    interface Presenter extends BasePresenter {

        float getAverage(float[] values);

        float getUpperLimit(float[] values);

        String[] initLables();

        String[] initLablesAsWeek();

        String[] initLablesAsMonth();

    }
}
