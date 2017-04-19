package me.memory7734.moving.settings;

import android.support.v7.preference.Preference;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;

/**
 * Created by Elijah on 16/10/20.
 */

public interface SettingsContract {

    interface View extends BaseView<Presenter> {

        void initTimeOfSavingArticles();

        void initTimeOfShowingDatas();

        void showCleanGlideCacheDone();

        void showRateError();

        void showFeedbackError();

        void showBrowserNotFoundError();

        void showNetworkError();

    }

    interface Presenter extends BasePresenter {

        void logout();

        void cleanGlideCache();

        int getTimeOfSavingArticles();

        int getTimeOfShowingDatas();

        void setTimeOfSavingArticles(Preference preference, Object newValue);

        void setTimeOfShowingDatas(Preference preference, Object newValue);

        void followOnGithub();

        void followOnWeibo();

        void rate();

        void feedback();

        void donate();

        void openLicense();

        void showEasterEgg();

    }

}
