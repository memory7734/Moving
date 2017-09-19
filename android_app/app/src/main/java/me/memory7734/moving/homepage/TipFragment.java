package me.memory7734.moving.homepage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.memory7734.moving.R;
import me.memory7734.moving.adapter.TipRecyclerAdapter;
import me.memory7734.moving.base.DividerItemDecoration;
import me.memory7734.moving.bean.CircleItem;
import me.memory7734.moving.bean.TipItem;

public class TipFragment extends Fragment implements TipContract.View {

    private RecyclerView recyclerView;
    private TipRecyclerAdapter adapter;

    private TipContract.Presenter presenter;


    public TipFragment() {
    }


    public static TipFragment newInstance() {
        TipFragment fragment = new TipFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onStart() {
        presenter.start();
        super.onStart();
    }

    @Override
    public void showResults(List<CircleItem> circleItemList, List<TipItem> tipItemList) {
        adapter = new TipRecyclerAdapter(getContext(), circleItemList, tipItemList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(TipContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_tip);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
    }
}
