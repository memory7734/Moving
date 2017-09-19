package me.memory7734.moving.homepage;

import java.util.List;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;
import me.memory7734.moving.bean.CircleItem;
import me.memory7734.moving.bean.TipItem;

/**
 * Created by Elijah on 16/10/25.
 */

public interface TipContract {

    interface View extends BaseView<Presenter> {

        void showResults(List<CircleItem> circleItemList, List<TipItem> tipItemList);

    }
    interface Presenter extends BasePresenter {

        void loadPosts();

    }

}
