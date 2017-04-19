package me.memory7734.moving.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;

import me.memory7734.moving.R;

/**
 * Created by Elijah on 16/10/23.
 */

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View view;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppCompatActivity activity;

    public UserPresenter(AppCompatActivity activity, UserContract.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);
        sharedPreferences = activity.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void start() {

    }

    @Override
    public void setNickname(Preference preference, Object newValue) {
        editor.putString("username", (String) newValue);
        editor.apply();
    }

    @Override
    public void setSex(Preference preference, Object newValue) {
        editor.putString("gender", (String) newValue);
        editor.apply();
    }

    @Override
    public void setBirthdate(Preference preference, Object newValue) {
        editor.putInt("birthdate", (int) newValue);
        editor.apply();
    }

    @Override
    public void setCity(Preference preference, Object newValue) {
        editor.putString("city", (String) newValue);
        editor.apply();
    }

    @Override
    public void setBloodType(Preference preference, Object newValue) {
        editor.putString("blood_type", (String) newValue);
        editor.apply();
    }

    @Override
    public void setFitzpatrickSkinType(Preference preference, Object newValue) {
        editor.putString("fitzpatrick_skin_type", (String) newValue);
        editor.apply();
    }

    @Override
    public void setWheelChair(Preference preference, Object newValue) {
        editor.putString("wheelchair", (String) newValue);
        editor.apply();
    }

    @Override
    public String getNickname() {
        return sharedPreferences.getString("username", activity.getString(R.string.not_configured));
    }

    @Override
    public String getSex() {
        return sharedPreferences.getString("gender", activity.getString(R.string.not_configured));
    }

    @Override
    public int getBirthdate() {
        return sharedPreferences.getInt("birthdate", 0);
    }

    @Override
    public String getCity() {
        return sharedPreferences.getString("city", activity.getString(R.string.not_configured));
    }

    @Override
    public String getBloodType() {
        return sharedPreferences.getString("blood_type", activity.getString(R.string.not_configured));
    }

    @Override
    public String getFitzpatrickSkinType() {
        return sharedPreferences.getString("fitzpatrick_skin_type", activity.getString(R.string.not_configured));
    }

    @Override
    public String getWheelChair() {
        return sharedPreferences.getString("wheelchair", activity.getString(R.string.not_configured));
    }
}
