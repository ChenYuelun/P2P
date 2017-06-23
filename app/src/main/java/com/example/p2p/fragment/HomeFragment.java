package com.example.p2p.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.p2p.R;
import com.example.p2p.base.BaseFragment;
import com.example.p2p.bean.IndexBean;
import com.example.p2p.utils.AppNetConfig;
import com.example.p2p.utils.HttpUtils;
import com.example.p2p.view.ProgressView;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by chenyuelun on 2017/6/20.
 */

public class HomeFragment extends BaseFragment {

    @Bind(R.id.iv_back_title)
    ImageView ivBackTitle;
    @Bind(R.id.iv_setting_title)
    ImageView ivSettingTitle;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_recommend)
    TextView tvRecommend;
    @Bind(R.id.proView)
    ProgressView proView;
    @Bind(R.id.join)
    Button join;

    @Override
    public void initData() {
        getDataFromNet();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    private void getDataFromNet() {
//        AsyncHttpClient httpClient = new AsyncHttpClient();
//        httpClient.get(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, String content) {
//                super.onSuccess(statusCode, content);
//                Log.e("TAG", "请求成功");
//                processData(content);
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//                Log.e("TAG", "请求失败");
//            }
//        });

        HttpUtils.getInstance().get(AppNetConfig.INDEX, new HttpUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(int statusCode, String content) {
                Log.e("TAG", "请求成功");
                processData(content);
            }

            @Override
            public void onFailure(Throwable error, String content) {
                Log.e("TAG", "请求失败error:" + error.getMessage());
            }
        });
    }

    private void processData(String content) {
        //使用FastJson解析数据
        IndexBean indexBean = JSON.parseObject(content, IndexBean.class);


        //手动解析JSON数据
//        if (!TextUtils.isEmpty(content)) {
//            IndexBean indexBean = new IndexBean();
//
//            try {
//                List<IndexBean.ImageArrBean> arrBeanList = new ArrayList<>();
//                JSONObject jsonObject = new JSONObject(content);
//                JSONArray jsonArray = jsonObject.optJSONArray("imageArr");
//                if(jsonArray != null) {
//                    for(int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
//                        IndexBean.ImageArrBean imageArrBean = new IndexBean.ImageArrBean();
//                        imageArrBean.setID(jsonObject1.getString("ID"));
//                        imageArrBean.setIMAURL(jsonObject1.getString("IMAURL"));
//                        imageArrBean.setIMAPAURL(jsonObject1.getString("IMAPAURL"));
//                        arrBeanList.add(imageArrBean);
//                    }
//                    indexBean.setImageArr(arrBeanList);
//                }
//
//
//                JSONObject jsonObject2 = jsonObject.optJSONObject("proInfo");
//                if(jsonObject2 != null) {
//                    IndexBean.ProInfoBean proInfoBean = new IndexBean.ProInfoBean();
//                    proInfoBean.setId(jsonObject2.optString("id"));
//                    proInfoBean.setMemberNum(jsonObject2.optString("memberNum"));
//                    proInfoBean.setMinTouMoney(jsonObject2.optString("minTouMoney"));
//                    proInfoBean.setMoney(jsonObject2.optString("money"));
//                    proInfoBean.setName(jsonObject2.optString("name"));
//                    proInfoBean.setProgress(jsonObject2.optString("progress"));
//                    proInfoBean.setSuodingDays(jsonObject2.optString("suodingDays"));
//                    proInfoBean.setYearRate(jsonObject2.optString("yearRate"));
//                    indexBean.setProInfo(proInfoBean);
//                }
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            initBanner(indexBean);
//        }

        initBanner(indexBean);
        initProgressView(indexBean);
    }

    private void initProgressView(IndexBean indexBean) {
        int parseInt = Integer.parseInt(indexBean.getProInfo().getProgress());
        Log.e("TAG","setSweeoAngle++==" + parseInt);
        proView.setSweeoAngle(parseInt);
    }

    private void initBanner(IndexBean indexBean) {
        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> images = new ArrayList<>();
        for (int i = 0; i < imageArr.size(); i++) {
            images.add(AppNetConfig.BASE_URL + imageArr.get(i).getIMAURL());
        }
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            //Picasso 加载图片简单用法
            Picasso.with(context).load((String) path).into(imageView);

        }
    }
}
