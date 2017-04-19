package me.memory7734.moving.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.memory7734.moving.R;
import me.memory7734.moving.account.ChangePasswordActivity;
import me.memory7734.moving.account.LoginActivity;


public class SettingsPreferenceFragment extends PreferenceFragmentCompat implements SettingsContract.View {

    private SettingsContract.Presenter presenter;
    private Toolbar toolbar;

    public SettingsPreferenceFragment() {
        super();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        addPreferencesFromResource(R.xml.settings_preference);

        initViews(getView());
//
//        findPreference("jawbone_up").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
//            @Override
//            public boolean onPreferenceClick(Preference preference) {
//
//
////                getAuthorization;
//
//                return true;
//            }
//        });

        findPreference("password").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                getActivity().startActivity(new Intent(getActivity(), ChangePasswordActivity.class));
                return true;
            }
        });

        findPreference("logout").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.logout();
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                return true;
            }
        });

        findPreference("clear_glide_cache").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.cleanGlideCache();
                return false;
            }
        });

        findPreference("time_of_saving_articles").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setTimeOfSavingArticles(preference, newValue);
                initTimeOfSavingArticles();
                return true;
            }
        });

        findPreference("time_of_showing_datas").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setTimeOfShowingDatas(preference, newValue);
                initTimeOfShowingDatas();
                return true;
            }
        });

        findPreference("follow_me_on_github").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.followOnGithub();
                return true;
            }
        });

        findPreference("follow_me_on_weibo").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.followOnWeibo();
                return true;
            }
        });

        findPreference("rate").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.rate();
                return true;
            }
        });

        findPreference("feedback").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.feedback();
                return true;
            }
        });

        findPreference("coffee").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.donate();
                return true;
            }
        });

        findPreference("open_source_license").setVisible(false);

        findPreference("open_source_license").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.openLicense();
                return true;
            }
        });

        findPreference("author").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                presenter.openLicense();
                return true;
            }
        });

    }

    public static SettingsPreferenceFragment newInstance() {
        return new SettingsPreferenceFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showCleanGlideCacheDone() {
        Snackbar.make(toolbar, R.string.clear_image_cache_successfully, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showRateError() {
        Snackbar.make(toolbar, R.string.no_app_store_found,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showFeedbackError() {
        Snackbar.make(toolbar, R.string.no_mail_app,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showBrowserNotFoundError() {
        Snackbar.make(toolbar, R.string.no_browser_found,Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void showNetworkError() {
        Snackbar.make(toolbar, R.string.no_network_connected, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(SettingsContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        initTimeOfSavingArticles();
        initTimeOfShowingDatas();
    }

    @Override
    public void initTimeOfSavingArticles() {
        findPreference("time_of_saving_articles").setSummary(presenter.getTimeOfSavingArticles() + " 天");
    }

    @Override
    public void initTimeOfShowingDatas() {
        if (presenter.getTimeOfShowingDatas() == 29) {
            findPreference("time_of_showing_datas").setSummary("1 月");
        } else {
            findPreference("time_of_showing_datas").setSummary("1 周");
        }
    }
}