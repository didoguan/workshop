package com.deepspc.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deepspc.workshop.model.ResponseData;
import com.deepspc.workshop.utils.HttpUtil;
import com.deepspc.workshop.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.loginSubmit);
        String spec = "http://10.0.2.2:8080/login/checkValid";
        login.setOnClickListener(v -> {
            EditText account = findViewById(R.id.loginAccount);
            EditText password = findViewById(R.id.loginPassword);
            String accountStr = account.getText().toString();
            String passwordStr = password.getText().toString();
            if ("".equals(accountStr) || "".equals(passwordStr)) {
                showToast("账号或密码不能为空！");
            } else {
                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 1) {
                            ResponseData responseData = (ResponseData) msg.obj;
                            if ("200".equals(responseData.getCode())) {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("token", responseData.getData().toString());
                                startActivity(intent);
                            } else {
                                showToast(responseData.getMessage());
                            }
                        } else {
                            showToast(msg.obj.toString());
                        }
                    }
                };
                Map<String, Object> params = new HashMap<>();
                params.put("account", account.getText().toString());
                params.put("password", password.getText().toString());

                new Thread(() -> {
                    Message msg= new Message();
                    String state = HttpUtil.post(spec, params);
                    try {
                        msg.what = 1;
                        msg.obj = JsonUtil.parseSimpleObj(state, ResponseData.class);
                    } catch (Exception e) {
                        msg.what = -1;
                        msg.obj = e.getMessage();
                    }
                    handler.sendMessage(msg);
                }).start();
            }
        });
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 120);
        toast.show();
    }

}
