package com.buzzvil.benefit.web;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.js.BuzzAdBenefitJavascriptInterface;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;

public class MainActivity extends AppCompatActivity {

    private ToggleButton loginButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loginButton = findViewById(R.id.login);
        this.webView = findViewById(R.id.webview);

        setupView();
    }

    private void setupView() {
        setupLoginButton();
        setupWebView();
    }

    private void setupLoginButton() {
        this.loginButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean toLogin) {
                if (toLogin) {
                    login();
                } else {
                    logout();
                }
            }
        });
    }

    private void setupWebView() {
        final BuzzAdBenefitJavascriptInterface javascriptInterface = new BuzzAdBenefitJavascriptInterface(webView);
        webView.getSettings().setJavaScriptEnabled(true); // JS를 사용하여 광고를 로드하기 때문에 필수임
        webView.addJavascriptInterface(javascriptInterface, BuzzAdBenefitJavascriptInterface.INTERFACE_NAME);
    }

    private void login() {
        // Simulate a user's login
        final UserProfile.Builder builder = new UserProfile.Builder(BuzzAdBenefit.getUserProfile());
        final UserProfile userProfile = builder
                .userId("Your_Service_User_ID")
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);

        this.loginButton.setChecked(true);
        openWebPage();
    }

    private void logout() {
        BuzzAdBenefit.setUserProfile(null);

        this.loginButton.setChecked(false);
        closeWebPage();
    }

    private void openWebPage() {
        webView.loadUrl(App.MY_WEB_PAGE);
    }

    private void closeWebPage() {
        webView.loadUrl("about:blank");
    }
}
