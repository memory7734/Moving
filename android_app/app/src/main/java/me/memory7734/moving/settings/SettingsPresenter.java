package me.memory7734.moving.settings;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;

import com.bumptech.glide.Glide;

import me.memory7734.moving.R;
import me.memory7734.moving.database.DataDao;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Elijah on 16/10/20.
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    private SettingsContract.View view;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AppCompatActivity activity;

    private static final int CLEAR_GLIDE_CACHE_DONE = 1;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CLEAR_GLIDE_CACHE_DONE:
                    view.showCleanGlideCacheDone();
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public SettingsPresenter(AppCompatActivity activity, SettingsContract.View view) {
        this.activity = activity;
        this.view = view;
        this.view.setPresenter(this);
        sharedPreferences = activity.getSharedPreferences("system_settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    @Override
    public void logout() {
        DataDao dao = new DataDao(activity.getBaseContext());
        dao.deleteAll();
        editor.clear();
        editor.commit();
    }

    @Override
    public void cleanGlideCache() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(activity).clearDiskCache();
                Message msg = new Message();
                msg.what = CLEAR_GLIDE_CACHE_DONE;
                handler.sendMessage(msg);
            }
        }).start();
        Glide.get(activity).clearMemory();
    }

    @Override
    public int getTimeOfSavingArticles() {
        return sharedPreferences.getInt("time_of_saving_articles", 7);
    }

    @Override
    public int getTimeOfShowingDatas() {
        return sharedPreferences.getInt("time_of_show_datas", 7);
    }

    @Override
    public void setTimeOfSavingArticles(Preference preference, Object newValue) {
        editor.putInt("time_of_saving_articles",  Integer.parseInt((String) newValue));
        editor.apply();
    }

    @Override
    public void setTimeOfShowingDatas(Preference preference, Object newValue) {
        editor.putInt("time_of_show_datas", Integer.parseInt((String) newValue));
        editor.apply();
    }

    @Override
    public void rate() {
        try {
            Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException exception) {
            view.showRateError();
        }
    }

    @Override
    public void openLicense() {
//        activity.startActivity(new Intent(Intent.ACTION_VIEW, OpenLicense.class));
    }

    @Override
    public void followOnGithub() {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(activity.getString(R.string.github_url))));
        } catch (ActivityNotFoundException exception) {
            view.showBrowserNotFoundError();
        }
    }

    @Override
    public void followOnWeibo() {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(activity.getString(R.string.weibo_url))));
        } catch (ActivityNotFoundException exception) {
            view.showBrowserNotFoundError();
        }
    }

    @Override
    public void feedback() {
        try {
            Uri uri = Uri.parse(activity.getString(R.string.sendto));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.mail_topic));
            intent.putExtra(Intent.EXTRA_TEXT,
                    activity.getString(R.string.device_model) + Build.MODEL + "\n"
                            + activity.getString(R.string.sdk_version) + Build.VERSION.RELEASE + "\n"
                            + activity.getString(R.string.version));
            activity.startActivity(intent);
        }catch (android.content.ActivityNotFoundException ex){
            view.showFeedbackError();
        }
    }

    @Override
    public void donate() {
        AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setTitle(R.string.donate);
        dialog.setMessage(activity.getString(R.string.donate_content));
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, activity.getString(R.string.positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 将指定账号添加到剪切板
                // add the alipay account to clipboard
                ClipboardManager manager = (ClipboardManager) activity.getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", activity.getString(R.string.donate_account));
                manager.setPrimaryClip(clipData);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getString(R.string.negative), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }

    long[] hits = new long[3];
    @Override
    public void showEasterEgg() {
        System.arraycopy(hits,1,hits,0,hits.length-1);
        hits[hits.length - 1] = SystemClock.uptimeMillis();
        if (hits[0] >= (SystemClock.uptimeMillis() - 500)) {
            AlertDialog dialog = new AlertDialog.Builder(activity).create();
            dialog.setTitle(R.string.easter_egg);
            dialog.setMessage(activity.getString(R.string.easter_egg_content));
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, activity.getString(R.string.of_course), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getString(R.string.of_course_not), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        }
    }

    @Override
    public void start() {

    }
}
