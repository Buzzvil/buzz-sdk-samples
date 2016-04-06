package com.buzzvil.buzzstore.sample;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.buzzvil.buzzstore.sdk.BuzzStore;

public class MainActivity extends Activity {

    ToggleButton loginButton;
    Button loadStoreButton;
    SharedPreferences preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = getSharedPreferences("BuzzStore_Sample", Context.MODE_PRIVATE);

        loginButton = (ToggleButton)findViewById(R.id.loginButton);
        boolean status = preference.getBoolean("login", false);

        if (status) {
            loginButton.setChecked(true);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginButton.isChecked()) {
                    // 로그인 완료된 상황을 가정함
                    // 퍼블리셔 서버와 앱의 통신을 통해 user_token도 받아온 상태
                    preference.edit().putBoolean("login", true).apply();
                    Toast.makeText(MainActivity.this, "login!", Toast.LENGTH_LONG).show();

                    // 로그인이 완료된 후 user_id, user_token을 넣어서 start를 호출한다.
                    // user_id : 로그인이 완료된 유저의 id
                    // user_token : API 통신 이후 받아온 token
                    BuzzStore.start("user_id", "user_token");

                } else {
                    // 세션 종료, 로그아웃 되는 상황을 가정함
                    // 저장해 둔 스토어 로드용 유저 정보를 제거한다.
                    preference.edit().putBoolean("login", false).apply();
                    Toast.makeText(MainActivity.this, "logout!", Toast.LENGTH_LONG).show();

                    // 저장된 user_id, user_token을 제거한다.
                    BuzzStore.exit();

                }

            }
        });

        loadStoreButton = (Button)findViewById(R.id.showStoreButton);
        loadStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuzzStore.loadStore(MainActivity.this);
            }
        });
    }
}
