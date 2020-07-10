package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adapter.DataAdapter;
import com.example.app.MyApp;
import com.example.bean.DatasBean;
import com.example.greendaodemo.db.DatasBeanDao;
import com.example.presenter.MyPresenter;
import com.example.second.R;
import com.example.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements IView {

    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    private DataAdapter dataAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.home, container, false);

        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initPresenter();
        return inflate;
    }

    private void initPresenter() {
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.toModel();
    }

    private void initView() {

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        dataAdapter = new DataAdapter(getActivity());
        mRecycler.setAdapter(dataAdapter);

        dataAdapter.setClickItem(new DataAdapter.ClickItem() {
            @Override
            public void onClick(DatasBean bean) {
                initInsert(bean);
            }
        });
    }

    private void initInsert(DatasBean bean) {
        DatasBeanDao datasBeanDao = MyApp.getDaoSession().getDatasBeanDao();
        datasBeanDao.insertOrReplace(bean);
        Toast.makeText(getActivity(), "插入成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(List<DatasBean> data) {
        dataAdapter.addData(data);
    }

    @Override
    public void onField(String message) {

    }
}
