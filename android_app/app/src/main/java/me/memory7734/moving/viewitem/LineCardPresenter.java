package me.memory7734.moving.viewitem;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Elijah on 16/10/25.
 */

public class LineCardPresenter implements LineCardContract.Presenter {

    private Calendar currentDate;
    private SharedPreferences sharedPreferences;

    public LineCardPresenter(Context context, LineCardContract.View view) {
        sharedPreferences = context.getSharedPreferences("system_settings", Context.MODE_PRIVATE);
        currentDate = Calendar.getInstance();
    }


    @Override
    public float getAverage(float[] values) {
        float sum = 0;
        for (float value : values) {
            sum += value;
        }
        if (String.valueOf(sum / values.length).length() >= 4) {
            return Float.valueOf(String.valueOf(sum / values.length).substring(0, 4));
        }
        return sum / values.length;
    }

    @Override
    public float getUpperLimit(float[] values) {
        float maxmium = values[0];
        for (float value : values) {
            if (value > maxmium) {
                maxmium = value;
            }
        }
        if (maxmium < 0) return 0;
        else if (maxmium < 1) return 1;
        else if (maxmium<5) return 5;
        else if (maxmium<10) return 10;
        else {
            String s = String.valueOf((int) maxmium);
            if (Integer.parseInt(s.substring(1, 2)) <= 5) {
                float f = Integer.parseInt(s.substring(0, 1)) * 10 + 5;
                for (int i = 0; i < s.length() - 2; i++) {
                    f *= 10;
                }
                return f;
            } else {
                float f = (Integer.parseInt(s.substring(0, 1)) + 1);
                for (int i = 0; i < s.length() - 1; i++) {
                    f *= 10;
                }
                return f;
            }
        }
    }

    @Override
    public String[] initLables() {
        return (sharedPreferences.getInt("time_of_show_datas", 7) == 7) ? initLablesAsWeek() : initLablesAsMonth();
    }

    @Override
    public String[] initLablesAsWeek() {
        List<String> labelsList = new ArrayList<>();
        Calendar previousDate = Calendar.getInstance();
        previousDate.add(Calendar.DATE, -6);
        if (previousDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)) {          //所有日期全部在一个月内
            labelsList.add((previousDate.get(Calendar.MONTH) + 1) + "月" + previousDate.get(Calendar.DAY_OF_MONTH) + "日");
            for (int i = previousDate.get(Calendar.DAY_OF_MONTH) + 1; i <= currentDate.get(Calendar.DAY_OF_MONTH); i++) {
                labelsList.add("" + i );
            }
        } else {                                                       //日期不在一个月内
            for (int i = previousDate.get(Calendar.DAY_OF_MONTH); i <= previousDate.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
                labelsList.add("" + i);
            }
            labelsList.add((currentDate.get(Calendar.MONTH) + 1) + "月1日");
            for (int i = 2; i <= currentDate.get(Calendar.DAY_OF_MONTH); i++) {
                labelsList.add("" + i);
            }
        }
        String[] strings = new String[labelsList.size()];
        labelsList.toArray(strings);
        return strings;
    }

    @Override
    public String[] initLablesAsMonth() {
        List<String> labelsList = new ArrayList<>();
        Calendar previousDate = (Calendar) currentDate.clone();
        previousDate.add(Calendar.DATE, -28);
        if (previousDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)) {          //所有日期全部在一个月内
            labelsList.add((previousDate.get(Calendar.MONTH) + 1) + "月" + previousDate.get(Calendar.DAY_OF_MONTH) + "日");
            int position = 7;
            while (position <= 28) {
                for (int i = 0; i < 6; i++) {
                    labelsList.add("");
                }
                previousDate.add(Calendar.DATE, 7);
                labelsList.add("" + previousDate.get(Calendar.DAY_OF_MONTH));
                position += 7;
            }
        } else {                                                                //日期不在一个月内
            labelsList.add("" + previousDate.get(Calendar.DAY_OF_MONTH));
            Calendar beforeDate = (Calendar) currentDate.clone();
            Calendar afterDate = (Calendar) currentDate.clone();
            beforeDate.add(Calendar.DATE, -28);
            afterDate.add(Calendar.DATE, -21);
            while (beforeDate.before(currentDate)) {
                for (int i = 0; i < 6; i++) {
                    labelsList.add("");
                }
                if (beforeDate.get(Calendar.DAY_OF_MONTH) > afterDate.get(Calendar.DAY_OF_MONTH)) {
                    labelsList.add((afterDate.get(Calendar.MONTH) + 1) + "月" + afterDate.get(Calendar.DAY_OF_MONTH) + "日");
                } else {
                    labelsList.add("" + afterDate.get(Calendar.DAY_OF_MONTH));
                }
                beforeDate = (Calendar) afterDate.clone();
                afterDate.add(Calendar.DATE, 7);
            }
        }
        String[] strings = new String[labelsList.size()];
        labelsList.toArray(strings);
        return strings;
    }

    @Override
    public void start() {

    }
}
