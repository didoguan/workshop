package com.deepspc.workshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText account = findViewById(R.id.loginAccount);
        EditText password = findViewById(R.id.loginPassword);
        Button login = findViewById(R.id.loginSubmit);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 Toast toast = Toast.makeText(LoginActivity.this, "账号或密码错误！", Toast.LENGTH_LONG);
                 toast.setGravity(Gravity.CENTER_VERTICAL,0, 120);
                 toast.show();
                 */
                //跳转到主界面
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
