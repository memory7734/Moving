package me.memory7734.moving.insert;

import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import me.memory7734.moving.R;
import me.memory7734.moving.util.TimeUtils;


public class InsertPreferenceFragment extends PreferenceFragmentCompat implements InsertContract.View  {

    private InsertContract.Presenter presenter;
    private Toolbar toolbar;

    private int selectedDataCategory;
    private int selectedDataType;
    private int currentDate;
    private float value;

    public InsertPreferenceFragment() {
        super();
        selectedDataCategory = 1;
        selectedDataType = 0;
        currentDate = TimeUtils.getIntFromCalendar(Calendar.getInstance());
    }

    public static InsertPreferenceFragment newInstance() {
        return new InsertPreferenceFragment();
    }


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.insert_preference);
        initViews(getView());

        findPreference("data_category").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                selectedDataCategory = Integer.parseInt((String) newValue);
                initDataCategory();
                initValue();
                return true;
            }
        });

        findPreference("fitness_data_type").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                selectedDataType = Integer.parseInt((String) newValue);
                initFitnessDataType();
                initValue();
                return true;
            }
        });

        findPreference("medical_data_type").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                selectedDataType = Integer.parseInt((String) newValue);
                initMedicalDataType();
                initValue();
                return true;
            }
        });

        findPreference("body_data_type").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                selectedDataType = Integer.parseInt((String) newValue);
                initBodyDataType();
                initValue();
                return true;
            }
        });

        findPreference("date_time").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        int newValue = year * 10000 + (monthOfYear + 1) * 100 + dayOfMonth;
                        currentDate = newValue;
                        initDate();
                        initValue();
                    }
                }, currentDate / 10000, currentDate % 10000 / 100 - 1, currentDate % 100)
                        .show(getActivity().getFragmentManager(), "DatePikerDailog");
                return true;
            }
        });


        findPreference("value").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (String.valueOf(newValue).length() == 0) {
                    presenter.setCanBeSaved(false);
                } else {
                    value = Float.parseFloat((String) newValue);
                    presenter.setValue(selectedDataType, currentDate, value);
                    presenter.setCanBeSaved(true);
                }
                initValue();
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
    public void setPresenter(InsertContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        initDataCategory();
        initDate();
        initValue();
    }



    @Override
    public void initDataCategory() {
        switch (selectedDataCategory) {
            case 1:
                findPreference("data_category").setSummary(getActivity().getString(R.string.fitness_data_type));
                initFitnessDataType();
                selectedDataType = 0;
                break;
            case 2:
                findPreference("data_category").setSummary(getActivity().getString(R.string.medical_data_type));
                initMedicalDataType();
                selectedDataType = 6;
                break;
            case 3:
                findPreference("data_category").setSummary(getActivity().getString(R.string.body_data_type));
                initBodyDataType();
                selectedDataType = 9;
                break;
            default:
                findPreference("data_category").setSummary(getActivity().getString(R.string.fitness_data_type));
                initFitnessDataType();
                break;
        }
    }

    @Override
    public void initFitnessDataType() {
        findPreference("fitness_data_type").setVisible(true);
        findPreference("medical_data_type").setVisible(false);
        findPreference("body_data_type").setVisible(false);
        switch (selectedDataType) {
            case 0:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.activity));
                break;
            case 1:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.activitie_energy));
                break;
            case 2:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.cycling_distance));
                break;
            case 3:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.exercise_mintues));
                break;
            case 4:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.walking_running_distance));
                break;
            case 5:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.step));
                break;
            default:
                findPreference("fitness_data_type").setSummary(getActivity().getString(R.string.activity));
                break;
        }
    }

    @Override
    public void initMedicalDataType() {
        findPreference("fitness_data_type").setVisible(false);
        findPreference("medical_data_type").setVisible(true);
        findPreference("body_data_type").setVisible(false);
        switch (selectedDataType) {
            case 6:
                findPreference("medical_data_type").setSummary(getActivity().getString(R.string.blood_glucose));
                break;
            case 7:
                findPreference("medical_data_type").setSummary(getActivity().getString(R.string.forced_vital_vapacity));
                break;
            case 8:
                findPreference("medical_data_type").setSummary(getActivity().getString(R.string.blood_alcohol_content));
                break;
            default:
                findPreference("medical_data_type").setSummary(getActivity().getString(R.string.blood_glucose));
                break;
        }
    }

    @Override
    public void initBodyDataType() {
        findPreference("fitness_data_type").setVisible(false);
        findPreference("medical_data_type").setVisible(false);
        findPreference("body_data_type").setVisible(true);
        switch (selectedDataType) {
            case 9:
                findPreference("body_data_type").setSummary(getActivity().getString(R.string.height));
                break;
            case 10:
                findPreference("body_data_type").setSummary(getActivity().getString(R.string.weight));
                break;
            default:
                findPreference("body_data_type").setSummary(getActivity().getString(R.string.height));
                break;
        }
    }

    @Override
    public void initDate() {
        findPreference("date_time").setSummary(currentDate / 10000 + "-" + currentDate % 10000 / 100 + "-" + currentDate % 100);
    }

    @Override
    public void initValue() {
        value = presenter.getValue(selectedDataType, currentDate);
        if (value == 0) {
            findPreference("value").setSummary("0");
        }
        findPreference("value").setSummary(String.valueOf(value));
    }
}
