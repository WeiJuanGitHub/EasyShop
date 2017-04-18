package com.feicui.easyshop.easyshop.user.register;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.feicui.easyshop.easyshop.R;
import com.feicui.easyshop.easyshop.commons.ActivityUtils;
import com.feicui.easyshop.easyshop.commons.RegexUtils;
import com.feicui.easyshop.easyshop.model.UserResult;
import com.feicui.easyshop.easyshop.network.EasyShopClient;
import com.feicui.easyshop.easyshop.network.UICallBack;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by dian on 2017/4/14.
 */

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_username)
    EditText et_userName;
    @BindView(R.id.et_pwd)
    EditText et_pwd;
    @BindView(R.id.et_pwdAgain)
    EditText et_pwdAgain;
    @BindView(R.id.btn_register)
    Button btn_register;

    private ActivityUtils activityUtils;
    private String username;
    private String password;
    private String pwd_again;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        activityUtils = new ActivityUtils(this);
        init();
    }

    private void init() {
        //给左上叫加上一个返回图标
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //给EditText添加时间监听
        et_userName.addTextChangedListener(textWatcher);
        et_pwd.addTextChangedListener(textWatcher);
        et_pwdAgain.addTextChangedListener(textWatcher);
    }
    //设置为ture,显示返回按钮,但是点击效果需要实现点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果点击的是返回键，则finish();
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
    //给EditText监听
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            username = et_userName.getText().toString();
            password = et_pwd.getText().toString();
            pwd_again = et_pwdAgain.getText().toString();
            //判断用户名和密码是否为空
            boolean canLogin = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pwd_again));
            btn_register.setEnabled(canLogin);

        }
    };
    @OnClick(R.id.btn_register)
    public void onClick() {
        if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.username_rules);
            return;
        } else if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.password_rules);
            return;
        } else if (!TextUtils.equals(password, pwd_again)) {
            activityUtils.showToast(R.string.username_equal_pwd);
            return;
        }
//都封装到了EasyShopCline类中 网络模块功能
//                1.创建客户端
//        OkHttpClient okHttpClient = new OkHttpClient();
//                2.构建请求
//        Request request = new Request.Builder();
//                    2.1 添加url（服务器地址，接口）
//                    2.2 添加请求方式（get，post）
//                    2.3 添加请求头（根据服务器要求来添加，通常不需要）
//                    2.4 添加请求体（可以为空，根据服务器要求）
//                3.客户端发送请求给服务器 -> 拿到响应
//                4.解析响应
//                    4.1 判断响应码（是否请求成功）
//                    4.2 响应码 = 200 - 299 ->  取出响应体（解析，展示）
        Call call = EasyShopClient.getInstance().register(username,password);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureUI(Call call, IOException e) {

            }

            @Override
            public void onResponseUI(Call call, String body) {

                UserResult userResult = new Gson().fromJson(body,UserResult.class);
            }
        });

    }
}
