package me.memory7734.moving.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import me.memory7734.moving.R;
import me.memory7734.moving.homepage.DataFragment;
import me.memory7734.moving.homepage.DataPresenter;
import me.memory7734.moving.homepage.TipFragment;
import me.memory7734.moving.homepage.TipPresenter;
import me.memory7734.moving.app.Constant;

/**
 * Created by Elijah on 16/10/24.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private final Context context;
    private List<Fragment> list;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        titles = context.getResources().getStringArray(R.array.page_titles);
        list = new ArrayList<>();
        TipFragment tipFragment = TipFragment.newInstance();
        new TipPresenter(context, tipFragment);
        list.add(tipFragment);
        DataFragment dataFragment1 = DataFragment.newInstance(Constant.DATA_TYPE_FITNESS);
        new DataPresenter(context, dataFragment1);
        list.add(dataFragment1);
        DataFragment dataFragment2 = DataFragment.newInstance(Constant.DATA_TYPE_MEDICAL);
        new DataPresenter(context, dataFragment2);
        list.add(dataFragment2);
        DataFragment dataFragment3 = DataFragment.newInstance(Constant.DATA_TYPE_BODY);
        new DataPresenter(context, dataFragment3);
        list.add(dataFragment3);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
