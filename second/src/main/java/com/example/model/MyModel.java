package com.example.model;

import com.example.api.ApiService;
import com.example.bean.Bean;
import com.example.bean.DatasBean;
import com.example.view.ICallBack;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel implements IModel {
    @Override
    public void getResult(final ICallBack callBack) {

        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean value) {
                        List<DatasBean> datas = value.getData().getDatas();
                        callBack.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onField(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
