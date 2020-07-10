package com.example.view;

import com.example.bean.DatasBean;

import java.util.List;

public interface IView {

    void onSuccess(List<DatasBean> data);

    void onField(String message);

}
