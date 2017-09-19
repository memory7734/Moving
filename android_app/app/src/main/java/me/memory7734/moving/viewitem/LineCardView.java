package me.memory7734.moving.viewitem;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.memory7734.moving.R;
import me.memory7734.moving.bean.HealthItem;

/**
 * Created by Elijah on 16/10/25.
 */

public class LineCardView implements LineCardContract.View {

//    private LineCardContract.Presenter presenter;


    private final LineChartView chart;
    private final CardView card;
    private SharedPreferences sharedPreferences;
    HealthItem mHealthItem;

    public LineCardView(CardView card) {
        sharedPreferences = card.getContext().getSharedPreferences("system_settings", Context.MODE_PRIVATE);
        this.chart = (LineChartView) card.findViewById(R.id.chart);
        this.card = card;
    }


    @Override
    public void initData() {
        // Data
        LineSet dataset = new LineSet(initLables(), mHealthItem.getValues());
        dataset.setColor(Color.parseColor("#0581F5"))   //线的颜色
                .setFill(Color.parseColor("#98CBFB"))   //阴影色
                .setDotsColor(Color.parseColor("#f1c40f"))  //点色
                .setThickness(4);
        chart.addData(dataset);
    }

    @Override
    public void showView(HealthItem healthItem) {
        this.mHealthItem = healthItem;
        initViews(card);
        initData();
        chart.show();
    }

    @Override
    public void refreshView(HealthItem healthItem) {
        this.mHealthItem = healthItem;
        initData();
        chart.show();
    }


    @Override
    public void initViews(View view) {
        TextView chartTitle = (TextView) card.findViewById(R.id.chart_title);           //图表的标题
        TextView chartAverage = (TextView) card.findViewById(R.id.chart_average);       //图表的平均值
        TextView chartToday = (TextView) card.findViewById(R.id.chart_today);           //图表的今天值
        TextView chartMost = (TextView) card.findViewById(R.id.chart_most);             //图表的最大值
        float[] values = mHealthItem.getValues();
        chartAverage.setText(card.getContext().getString(R.string.chart_average) +
                getAverage(values) +
                mHealthItem.getDataTypeMeasuringUnit());
        chartToday.setText(card.getContext().getString(R.string.chart_today) + values[values.length - 1] + mHealthItem.getDataTypeMeasuringUnit());
        chartTitle.setText(mHealthItem.getDataTypeName());
        chartMost.setText("" + getUpperLimit(values));
        // Chart
        chart.setBorderSpacing(Tools.fromDpToPx(15))
                .setAxisBorderValues(0, (int) getUpperLimit(values))
                .setYLabels(AxisController.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#666565"))    //标签色
                .setXAxis(false)
                .setYAxis(false);
    }


    @Override
    public void setPresenter(LineCardContract.Presenter presenter) {
        if (presenter == null) {
//            this.presenter = presenter;
        }
    }

    public float getAverage(float[] values) {
        float sum = 0;
        for (float value : values) {
            sum += value;
        }
        BigDecimal b = new BigDecimal(sum / values.length);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

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


    public String[] initLables() {
        return (sharedPreferences.getInt("time_of_show_datas", 7) == 7) ? initLablesAsWeek() : initLablesAsMonth();
    }

    public String[] initLablesAsWeek() {
        List<String> labelsList = new ArrayList<>();
        Calendar currentDate = Calendar.getInstance();
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


    public String[] initLablesAsMonth() {
        List<String> labelsList = new ArrayList<>();
        Calendar currentDate = Calendar.getInstance();
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

}
