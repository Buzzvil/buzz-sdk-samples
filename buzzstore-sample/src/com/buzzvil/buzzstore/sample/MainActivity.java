package com.buzzvil.buzzstore.sample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.buzzvil.buzzstore.sdk.BuzzStore;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize BuzzStore.
         * BuzzStore.init have to be called prior to BuzzStore.loadStore
         * appId : Unique key value to identify the publisher.
         * userId : user identifier used from publisher
         * this : Context
         */
        BuzzStore.init("appId", "userId", this);

        BuzzStore.setBuzzStoreListener(new BuzzStore.BuzzStoreListener() {
            @Override
            public String OnNeedAPICall() {
                /**
                * 해당 함수가 호출되면 퍼블리셔 서버로의 API 콜을 통해 해당 유저의 유저토큰을 전달받아 리턴해야 한다.
                * 보안상의 이유로 해당 유저토큰은 Server-To-Server로만 제공되므로 퍼블리셔앱은 반드시 퍼블리셔 서버를 통해서 버즈스토어로 요청해야 한다.
                */
                return "EXAMPLE_USER_TOKEN";
            }

            @Override
            public void onFail() {
                /**
                * 최대 횟수 만큼 SDK 내부에서 Validation 시도를 하였으나 최종 실패한 경우 이 함수가 호출된다.
                * 이 때, 퍼블리셔는 실패 UI 등을 유저에게 보이도록 처리하는 것이 권장된다.
                * ex. 서버 장애로 인한 접속 불가 메세지 알림
                */
            }
        });

        findViewById(R.id.showStoreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Load BuzzStore.
                 * MainActivity.this : Current activity
                 */
                BuzzStore.loadStore(MainActivity.this);
            }
        });
    }
}
