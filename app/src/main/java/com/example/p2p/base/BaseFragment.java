package com.example.p2p.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p2p.utils.UiUtils;

import butterknife.ButterKnife;

/**
 * Created by chenyuelun on 2017/6/21.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        if(layoutId != 0) {
            View view = UiUtils.inflate(layoutId);
            ButterKnife.bind(this,view);
            return view;
        }else {
            TextView textView = new TextView(getActivity());
            textView.setText("布局编号请不要返回0");
            return textView;
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {

    }

    protected abstract void initData();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
