package me.memory7734.moving.account;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText phone;
    private EditText password;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().matches("[a-zA-Z0-9]+")) {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("错误").setMessage("用户名只能为英文字母或数字").setPositiveButton("确定", null).create().show();
                }else if (phone.getText().toString().length() != 11) {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("错误").setMessage("请输入11位手机号").setPositiveButton("确定", null).create().show();
                }else if (password.getText().toString().length() == 0) {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("错误").setMessage("请输入密码").setPositiveButton("确定", null).create().show();
                } else if (!NetworkState.networkConnected(getBaseContext())) {
                    new AlertDialog.Builder(RegisterActivity.this).setTitle("错误").setMessage("请检查网络").setPositiveButton("确定", null).create().show();
                } else {
                    CacheService.register(v, username.getText().toString(), phone.getText().toString(), password.getText().toString(), new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }
    private void initViews() {
        username = (EditText) findViewById(R.id.register_username);
        phone = (EditText) findViewById(R.id.register_phone);
        password = (EditText) findViewById(R.id.register_password);
        button = (Button) findViewById(R.id.register_button);
    }

}
