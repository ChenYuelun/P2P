package com.example.p2p.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p2p.R;
import com.example.p2p.utils.UiUtils;

/**
 * Created by chenyuelun on 2017/6/20.
 */

public class HomeFragment extends Fragment {

    private String TAG = HomeFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"布局初始化");
//        return View.inflate(getActivity(), R.layout.fragment_home,null);
        return UiUtils.inflate(R.layout.fragment_home);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
