package me.memory7734.moving.user;

import android.support.v7.preference.Preference;

import me.memory7734.moving.base.BasePresenter;
import me.memory7734.moving.base.BaseView;

/**
 * Created by Elijah on 16/10/23.
 */

public interface UserContract {
    interface View extends BaseView<UserContract.Presenter> {

        void initNickname();

        void initSex();

        void initBirthdate();

        void initCity();

        void initBloodType();

        void initFitzpatrickSkinType();

        void initWheelChair();

        void showNetworkError();

    }

    interface Presenter extends BasePresenter {

        void setNickname(Preference preference, Object newValue);

        void setSex(Preference preference, Object newValue);

        void setBirthdate(Preference preference, Object newValue);

        void setCity(Preference preference, Object newValue);

        void setBloodType(Preference preference, Object newValue);

        void setFitzpatrickSkinType(Preference preference, Object newValue);

        void setWheelChair(Preference preference, Object newValue);

        String getNickname();

        String getSex();

        int getBirthdate();

        String getCity();

        String getBloodType();

        String getFitzpatrickSkinType();

        String getWheelChair();

    }
}
