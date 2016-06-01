package com.buzzvil.buzzstore.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.buzzvil.buzzstore.sdk.BuzzStore;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize BuzzStore.
         * BuzzStore.init have to be called prior to BuzzStore.loadStore
         * @param appId : Unique key value to identify the publisher.
         * @param userId : user identifier used from publisher
         * @param this : Context
         */
        BuzzStore.init("appId", "userId", this);

        BuzzStore.setUserTokenListener(new BuzzStore.UserTokenListener() {
            @Override
            public void OnNeedAPICall() {
                * 해당 함수가 호출되면 퍼블리셔 서버로의 API 콜을 통해 해당 유저의 유저토큰을 전달받아 setUserToken 메소드를 통해 등록해야 한다.
                        * 보안상의 이유로 해당 유저토큰은 Server-To-Server로만 제공되므로 퍼블리셔앱은 반드시 퍼블리셔 서버를 통해서 버즈스토어로 요청해야 한다.
                        * 주의 : 아래의 코드는 참고를 위한 Pseudo-code 로 실제로 이와 같은 메소드가 제공되지는 않는다.
                        */

                Request(
                        new ResponseListener() {
                            OnSuccess(String userToken) {
                                /**
                                 * 서버로부터 userToken을 전달받은 후에 아래의 메소드를 호출한다.
                                 */
                                BuzzStore.setUserToken(userToken);
                            }
                            OnError() {
                                /**
                                 * 서버 통신 실패 시 아래와 같이 빈 스트링으로 setUserToken을 다시 요청한다.
                                 */
                                BuzzStore.setUserToken("");
                            }
                        }
                );
            }

            @Override
            public void OnFail() {
                /**
                 * 최대 횟수 만큼 SDK 내부에서 Validation 시도를 하였으나 최종 실패한 경우 이 함수가 호출된다.
                 * 버즈스토어 서버 혹은 퍼블리셔 서버가 작동하지 않을 때 이런 경우가 발생한다.
                 * 이 때, 퍼블리셔는 실패 UI 등을 유저에게 보이도록 처리하는 것이 권장된다.
                 * ex. 서버 장애 공지 메세지
                 */
                Toast.makeText(MainActivity.this, "서버 장애 발생. 현재 접근이 제한되어 있습니다.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.showStoreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Load BuzzStore(Default)
                 * MainActivity.this : Current activity
                 */
                BuzzStore.loadStore(MainActivity.this);
//                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.DEFAULT); // same
            }
        });

        findViewById(R.id.showCouponButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Load BuzzStore with 'coupons' tab shown first.
                 */
                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.COUPONS);
            }
        });

        findViewById(R.id.showPointButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Load BuzzStore with 'points' tab shown first.
                 */
                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.POINTS);
            }
        });
    }
}
