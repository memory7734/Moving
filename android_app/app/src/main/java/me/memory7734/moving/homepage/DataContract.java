package me.memory7734.moving.homepage;

import java.util.List;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;
import me.memory7734.moving.bean.HealthItem;

/**
 * Created by Elijah on 16/10/25.
 */

public interface DataContract {

    interface View extends BaseView<Presenter> {

        void showResults(List<HealthItem> list);

    }

    interface Presenter extends BasePresenter {

        void start(int dataType);

        void loadPosts(int dataType);

    }

}
