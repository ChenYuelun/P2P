package com.example.p2p.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.p2p.R;
import com.example.p2p.fragment.HomeFragment;
import com.example.p2p.fragment.InvestFragment;
import com.example.p2p.fragment.MoreFragment;
import com.example.p2p.fragment.PropertyFragment;

public class MainActivity extends AppCompatActivity {
    private FrameLayout fl_main;
    private RadioGroup rg_main;
    
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private PropertyFragment propertyFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        rg_main.check(R.id.rb_home);

    }

    private void initListener() {
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
        
    }

    private void switchFragment(int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hiddenFragment(transaction);
        switch (checkedId) {
            case  R.id.rb_home:
                if(homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main,homeFragment);
                }else {
                    transaction.show(homeFragment);
                }
                break;
            case  R.id.rb_invest:
                if(investFragment == null) {
                    investFragment = new InvestFragment();
                    transaction.add(R.id.fl_main,investFragment);
                }else {
                    transaction.show(investFragment);
                }
                break;
            case  R.id.rb_propety:
                if(propertyFragment == null) {
                    propertyFragment = new PropertyFragment();
                    transaction.add(R.id.fl_main,propertyFragment);
                }else {
                    transaction.show(propertyFragment);
                }
                break;
            case  R.id.rb_more:
                if(moreFragment == null) {
                    moreFragment = new MoreFragment();
                    transaction.add(R.id.fl_main,moreFragment);
                }else {
                    transaction.show(moreFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hiddenFragment(FragmentTransaction transaction) {
        if(homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if(investFragment != null) {
            transaction.hide(investFragment);
        }
        if(propertyFragment != null) {
            transaction.hide(propertyFragment);
        }
        if(moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }


    private void initData() {

    }


    //控件初始化
    private void initView() {
        fl_main = instance(R.id.fl_main);
        rg_main = instance(R.id.rg_main);
    }
    public <T> T instance(int id) {
        return (T) findViewById(id);
    }
}
