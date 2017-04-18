package com.feicui.easyshop.easyshop.main.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.easyshop.easyshop.R;
import com.feicui.easyshop.easyshop.commons.ActivityUtils;
import com.feicui.easyshop.easyshop.user.login.LoginActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class MeFragment extends Fragment {

    private ActivityUtils activityUtils;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_me,container,false);
            activityUtils = new ActivityUtils(this);
            ButterKnife.bind(this,view);
        }

        return view;
    }
    @OnClick({R.id.tv_person_info, R.id.tv_login, R.id.tv_person_goods, R.id.tv_goods_upload})
    public void OnClick(View view){
    // TODO: 2017/4/16 判断用户是否登录，从而决定跳转的位置
        activityUtils.startActivity(LoginActivity.class);

    }
}
