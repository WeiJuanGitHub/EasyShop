package com.feicui.easyshop.easyshop.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.easyshop.easyshop.R;


/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class UnLoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_un_login,container,false);
        return view;
    }
}
