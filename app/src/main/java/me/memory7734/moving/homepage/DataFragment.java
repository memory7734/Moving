package me.memory7734.moving.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.memory7734.moving.R;
import me.memory7734.moving.adapter.DataRecyclerAdapter;
import me.memory7734.moving.app.VolleyCallback;
import me.memory7734.moving.base.DividerItemDecoration;
import me.memory7734.moving.bean.HealthItem;
import me.memory7734.moving.insert.InsertPreferenceActivity;
import me.memory7734.moving.service.CacheService;
import me.memory7734.moving.util.NetworkState;

public class DataFragment extends Fragment implements DataContract.View {

    private final String TAG = "INSERT";

    private RecyclerView recyclerView;
    private DataRecyclerAdapter adapter;

    private DataContract.Presenter presenter;

    private int dataType;
    private boolean needRefresh;

    public static final String ARGS_DATA_TYPE = "data_type";

    public DataFragment() {
    }

    public static DataFragment newInstance(int dataType) {
        Bundle args = new Bundle();
        args.putInt(ARGS_DATA_TYPE, dataType);
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataType = getArguments().getInt(ARGS_DATA_TYPE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        initViews(view);
        if (NetworkState.networkConnected(getContext())) {
            CacheService.update_health_all_data(view);
            CacheService.update_user_data(view, new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                }
            });
        }
        return view;
    }

    @Override
    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InsertPreferenceActivity.class));
            }
        });

    }


    @Override
    public void onStart() {
        presenter.start(dataType);
        super.onStart();
    }



    @Override
    public void setPresenter(DataContract.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void showResults(List<HealthItem> list) {
//        adapter = new DataRecyclerAdapter(getContext(), list);
        recyclerView.setAdapter(new DataRecyclerAdapter(getContext(), list));
    }

}
