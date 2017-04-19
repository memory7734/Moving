package me.memory7734.moving.user;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import me.memory7734.moving.R;

/**
 * Created by Elijah on 16/10/23.
 */

public class UserPreferenceFragment extends PreferenceFragmentCompat implements UserContract.View {

    private UserContract.Presenter presenter;
    private Toolbar toolbar;

    public UserPreferenceFragment() {
        super();
    }


    public static UserPreferenceFragment newInstance() {
        return new UserPreferenceFragment();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.user_preference);

        initViews(getView());

        findPreference("username").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setNickname(preference, newValue);
                initNickname();
                return true;
            }
        });

        findPreference("gender").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setSex(preference, newValue);
                initSex();
                return true;
            }
        });

        findPreference("birthdate").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        int newValue = year * 10000 + (monthOfYear + 1) * 100 + dayOfMonth;
                        presenter.setBirthdate(preference, newValue);
                        initBirthdate();
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                        .show(getActivity().getFragmentManager(), "DatePikerDailog");
                return true;
            }
        });


        findPreference("city").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setCity(preference, newValue);
                initCity();
                return true;
            }
        });

        findPreference("blood_type").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setBloodType(preference, newValue);
                initBloodType();
                return true;
            }
        });

        findPreference("fitzpatrick_skin_type").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setFitzpatrickSkinType(preference, newValue);
                initFitzpatrickSkinType();
                return true;
            }
        });

        findPreference("wheelchair").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                presenter.setWheelChair(preference, newValue);
                initWheelChair();
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(toolbar, R.string.no_network_connected, Snackbar.LENGTH_SHORT);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        initNickname();
        initSex();
        initBirthdate();
        initCity();
        initBloodType();
        initFitzpatrickSkinType();
        initWheelChair();
    }

    @Override
    public void initNickname() {
        findPreference("username").setSummary(presenter.getNickname());
        TextView mTextNickname = (TextView) getActivity().findViewById(R.id.textUsername);
        mTextNickname.setText(presenter.getNickname());
    }

    @Override
    public void initSex() {
        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imgGender);
        if (presenter.getSex().equals("male")) {
            findPreference("gender").setSummary(getActivity().getString(R.string.male));
            imageView.setImageResource(R.drawable.male);
        } else if (presenter.getSex().equals("female")) {
            findPreference("gender").setSummary(getActivity().getString(R.string.female));
            imageView.setImageResource(R.drawable.female);
        } else {
            findPreference("gender").setSummary(getActivity().getString(R.string.not_configured));
            imageView.setImageResource(R.drawable.male);
        }

    }

    @Override
    public void initBirthdate() {
        if (presenter.getBirthdate() == 0) {
            findPreference("birthdate").setSummary(getActivity().getString(R.string.not_configured));
        } else {
            int birthdate = presenter.getBirthdate();
            findPreference("birthdate").setSummary(birthdate / 10000 + "-" + birthdate % 10000 / 100 + "-" + birthdate % 100);
        }
    }

    @Override
    public void initCity() {
        findPreference("city").setSummary(presenter.getCity());
    }

    @Override
    public void initBloodType() {
        if (presenter.getBloodType().equals("not_configured")) {
            findPreference("blood_type").setSummary(getActivity().getString(R.string.not_configured));
        } else {
            findPreference("blood_type").setSummary(presenter.getBloodType());
        }
    }

    @Override
    public void initFitzpatrickSkinType() {
        switch (presenter.getFitzpatrickSkinType()) {
            case "type1":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_1));
                break;
            case "type2":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_2));
                break;
            case "type3":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_3));
                break;
            case "type4":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_4));
                break;
            case "type5":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_5));
                break;
            case "type6":
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.fitzpatrick_skin_type_6));
                break;
            default:
                findPreference("fitzpatrick_skin_type").setSummary(getActivity().getString(R.string.not_configured));
                break;
        }
    }

    @Override
    public void initWheelChair() {
        if (presenter.getWheelChair().equals("true")) {
            findPreference("wheelchair").setSummary(getActivity().getString(R.string.yes));
        } else if (presenter.getWheelChair().equals("false")) {
            findPreference("wheelchair").setSummary(getActivity().getString(R.string.no));
        } else {
            findPreference("wheelchair").setSummary(getActivity().getString(R.string.not_configured));
        }
    }

}
