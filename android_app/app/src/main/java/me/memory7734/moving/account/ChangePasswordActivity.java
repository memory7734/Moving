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

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText password;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        password = (EditText) findViewById(R.id.change_password);
        button = (Button) findViewById(R.id.change_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().length() == 0) {
                    new AlertDialog.Builder(ChangePasswordActivity.this).setTitle("错误").setMessage("请输入密码").setPositiveButton("确定", null).create().show();
                }else if (!NetworkState.networkConnected(getBaseContext())) {
                    new AlertDialog.Builder(ChangePasswordActivity.this).setTitle("错误").setMessage("请检查网络").setPositiveButton("确定", null).create().show();
                } else {
                    CacheService.update_user_password(v, password.getText().toString(), new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                }
            }
        });
    }
}
