package me.memory7734.moving.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.memory7734.moving.R;
import me.memory7734.moving.app.VolleyCallback;
import me.memory7734.moving.homepage.MainActivity;
import me.memory7734.moving.service.CacheService;
import me.memory7734.moving.util.NetworkState;

public class LoginActivity extends AppCompatActivity {


    private EditText phone;
    private EditText password;
    private Button loginButton;
    private Button loginForget;
    private Button loginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        SharedPreferences sp = getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        if (sp.getInt("userkey", 0) != 0) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        loginForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phone.getText().toString().length() != 11) {
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("请输入11位手机号").setPositiveButton("确定", null).show();
                }else if (password.getText().toString().length() == 0) {
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("请输入密码").setPositiveButton("确定", null).show();
                } else if (!NetworkState.networkConnected(getBaseContext())) {
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误").setMessage("请检查网络").setPositiveButton("确定", null).show();
                } else {
                    CacheService.login(v, phone.getText().toString(), password.getText().toString(), new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Snackbar.make(loginButton, "登陆成功", Snackbar.LENGTH_LONG).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    });

                }
            }
        });
    }

    private void initViews() {
        phone = (EditText) findViewById(R.id.login_phone);
        password = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);
        loginForget = (Button) findViewById(R.id.login_forget);
        loginRegister = (Button) findViewById(R.id.login_register);
    }


}
