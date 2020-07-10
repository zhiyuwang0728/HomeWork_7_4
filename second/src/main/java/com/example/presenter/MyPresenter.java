package com.example.presenter;

import com.example.bean.DatasBean;
import com.example.model.MyModel;
import com.example.view.ICallBack;
import com.example.view.IView;

import java.util.List;

public class MyPresenter implements IPresenter {

    private final MyModel myModel;
    private final IView view;

    public MyPresenter(IView view) {
        myModel = new MyModel();
        this.view=view;
    }

    @Override
    public void toModel() {
        myModel.getResult(new ICallBack<List<DatasBean>>() {

            @Override
            public void onSuccess(List<DatasBean> data) {
                view.onSuccess(data);
            }

            @Override
            public void onField(String message) {
                view.onField(message);
            }
        });
    }
}
