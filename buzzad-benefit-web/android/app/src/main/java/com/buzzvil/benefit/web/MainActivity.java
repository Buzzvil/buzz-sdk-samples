package com.buzzvil.benefit.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.js.BuzzAdBenefitJavascriptInterface;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText userIdView;
    private ToggleButton loginButton;
    private WebView webView;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.userIdView = findViewById(R.id.userid);
        this.loginButton = findViewById(R.id.login);
        this.webView = findViewById(R.id.webview);

        this.random = new Random();

        setupView();
    }

    private void setupView() {
        setupUserIdView();
        setupLoginButton();
        setupWebView();
    }

    /**
     * Create random userId when app is launched
     */
    private void setupUserIdView() {
        userIdView.setText("TEST_" + (random.nextInt() & Integer.MAX_VALUE));
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

    /**
     * IMPORTANT
     * <p>
     * * Javascript must be enabled
     * * Apply BuzzAdBenefitJavascriptInterface
     */
    private void setupWebView() {
        final BuzzAdBenefitJavascriptInterface javascriptInterface = new BuzzAdBenefitJavascriptInterface(webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(javascriptInterface, BuzzAdBenefitJavascriptInterface.INTERFACE_NAME);
    }

    private void setLoginUi(final String userId) {
        this.userIdView.setText(userId);
        this.loginButton.setChecked(true);
        this.userIdView.setEnabled(false);
    }

    private void setLogoutUi() {
        this.loginButton.setChecked(false);
        this.userIdView.setEnabled(true);
    }

    /**
     * IMPORTANT
     * <p>
     * Create UserProfile from user's info,
     * apply to BuzzAdBenefit
     * and open the web page which benefit JS SDK is integrated in.
     */
    private void login() {
        // Simulate a user's login
        final String userId = userIdView.getText().toString();
        if (TextUtils.isEmpty(userId)) {
            this.setLogoutUi();
            return;
        }
        final UserProfile.Builder builder = new UserProfile.Builder(BuzzAdBenefit.getUserProfile());
        final UserProfile userProfile = builder
                .userId(userId)
                .gender(UserProfile.Gender.MALE)
                .birthYear(1985)
                .build();

        BuzzAdBenefit.setUserProfile(userProfile);
        this.setLoginUi(userId);
        openWebPage();
    }

    private void logout() {
        BuzzAdBenefit.setUserProfile(null);
        setLogoutUi();
        closeWebPage();
    }

    /**
     * IMPORTANT
     * <p>
     * Open the page which benefit JS SDK is integrated in.
     * It must not be called before UserProfile is set.
     */
    private void openWebPage() {
        webView.loadUrl(App.MY_WEB_PAGE);
    }

    private void closeWebPage() {
        webView.loadUrl("about:blank");
    }
}
