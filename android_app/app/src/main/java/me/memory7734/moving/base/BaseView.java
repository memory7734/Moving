package me.memory7734.moving.base;

import android.view.View;

/**
 * Created by Elijah on 16/10/20.
 */

public interface BaseView<T> {

    /**
     * set the presenter of mvp
     * @param presenter
     */
    void setPresenter(T presenter);

    /**
     * init the views of fragment
     * @param view
     */
    void initViews(View view);
}
