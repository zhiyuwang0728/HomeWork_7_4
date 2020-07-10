package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.DataAdapter;
import com.example.app.MyApp;
import com.example.bean.DatasBean;
import com.example.greendaodemo.db.DatasBeanDao;
import com.example.second.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeFragment extends Fragment {

    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    private DatasBeanDao datasBeanDao;
    private DataAdapter dataAdapter;
    DatasBean empty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.me, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        //initData();


        return inflate;
    }

    /*private void initData() {

        List<DatasBean> list = datasBeanDao.queryBuilder().list();
        dataAdapter.addData(list);
    }*/

    private void initView() {

        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        dataAdapter = new DataAdapter(getActivity());
        mRecycler.setAdapter(dataAdapter);

        registerForContextMenu(mRecycler);
        dataAdapter.setClickItem(new DataAdapter.ClickItem() {
            @Override
            public void onClick(DatasBean bean) {
                empty=bean;
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser==true&&mRecycler!=null) {
            datasBeanDao = MyApp.getDaoSession().getDatasBeanDao();
            List<DatasBean> list = datasBeanDao.queryBuilder().list();
            dataAdapter.addData(list);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "删除");
        menu.add(1, 2, 1, "修改");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                initDelete();
                break;
            case 2:
                initUpdate();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initUpdate() {
        empty.setTitle("666");
        datasBeanDao.update(empty);
        List<DatasBean> list = datasBeanDao.queryBuilder().list();
        dataAdapter.addData(list);
    }

    private void initDelete() {
        datasBeanDao.delete(empty);
        List<DatasBean> list = datasBeanDao.queryBuilder().list();
        dataAdapter.addData(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
