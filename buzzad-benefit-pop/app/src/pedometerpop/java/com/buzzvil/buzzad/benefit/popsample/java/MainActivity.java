package com.buzzvil.buzzad.benefit.popsample.java;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.core.models.UserProfile;
import com.buzzvil.buzzad.benefit.pop.BuzzAdPop;
import com.buzzvil.buzzad.benefit.pop.pedometer.BuzzAdPopPedometer;
import com.buzzvil.buzzad.benefit.popsample.BuildConfig;
import com.buzzvil.buzzad.benefit.popsample.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private static final int ACTIVATION_REQUEST_CODE = 2000;

    private TextView textAppVersion;
    private TextView textSdkSdkVersion;
    private Button popSetUserProfile;
    private Button popShowButton;
    private Button popUnregisterButton;
    private Switch pedometerPopSwitch;

    private BuzzAdPop buzzAdPop;
    private BroadcastReceiver sessionReadyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("SessionKey", "Session is Ready. Ads can be loaded now.");
            Toast.makeText(MainActivity.this, "Session is Ready. Ads can be loaded now.", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVersionText();

        BuzzAdBenefit.registerSessionReadyBroadcastReceiver(MainActivity.this, sessionReadyReceiver);

        this.buzzAdPop = BuzzAdPop.getInstance();
        this.popSetUserProfile = findViewById(R.id.pop_set_user_profile);
        popSetUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Caution: Replace `SAMPLE_USER_ID` with User's ID
                // Caution: Replace `UserProfile.Gender.FEMALE` with User's gender
                // Caution: Replace `1993` with User's BirthYear
                final UserProfile userProfile = new UserProfile.Builder(BuzzAdBenefit.getUserProfile())
                        .userId("SAMPLE_USER_ID")
                        .gender(UserProfile.Gender.FEMALE)
                        .birthYear(1993)
                        .build();
                BuzzAdBenefit.setUserProfile(userProfile);
            }
        });

        this.popShowButton = findViewById(R.id.pop_show_button);
        popShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!requestActivityRecognitionPermissionIfNeeded(MainActivity.this)) {
                    showPop();
                }
            }
        });

        this.pedometerPopSwitch = findViewById(R.id.pedometer_pop_switch);
        pedometerPopSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pedometerPopSwitch.isChecked()) {
                    pedometerPopSwitch.setChecked(false);
                    if (!requestActivityRecognitionPermissionIfNeeded(MainActivity.this)) {
                        showPop();
                    }
                } else {
                    unregisterPop();
                }
            }
        });

        this.popUnregisterButton = findViewById(R.id.pop_unregister_button);
        popUnregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterPop();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (buzzAdPop != null) {
            pedometerPopSwitch.setChecked(buzzAdPop.isPopEnabled());
        }
    }

    private void unregisterPop() {
        pedometerPopSwitch.setChecked(false);
        buzzAdPop.deactivate(this);
    }

    private void setVersionText() {
        textAppVersion = findViewById(R.id.text_app_version);
        String strAppVersion = BuildConfig.VERSION_NAME;
        textAppVersion.setText(strAppVersion);
        textSdkSdkVersion = findViewById(R.id.text_sdk_version);
        String strBenefitSdkVersion = BuzzAdBenefit.getSdkVersion();
        textSdkSdkVersion.setText(strBenefitSdkVersion);
    }

    private void showPop() {
        pedometerPopSwitch.setChecked(true);
        activatePedometerPop();
    }

    private void activatePedometerPop() {
        buzzAdPop.activate(new BuzzAdPop.PopActivateListener() {
            public void onActivated() {
                buzzAdPop.show();
                // Pop 활성화 완료 후, 만보기 기능 활성화
                BuzzAdPopPedometer.activate(new BuzzAdPopPedometer.OnActivationListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "BuzzAdPopPedometer.activate onSuccess");
                    }

                    @Override
                    public void onPermissionNotReady() {
                        Log.w(TAG, "BuzzAdPopPedometer.activate onPermissionNotReady");
                    }

                    @Override
                    public void onSensorError() {
                        Log.w(TAG, "BuzzAdPopPedometer.activate onSensorError");
                    }
                });
            }

            public void onActivationFailed(@Nullable Throwable error) {
            }
        });
    }

    private boolean requestActivityRecognitionPermissionIfNeeded(Activity activity) {
        if (activity != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
                requestActivityRecognitionPermission(activity);
                return true;
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.Q)
    private void requestActivityRecognitionPermission(Activity activity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACTIVITY_RECOGNITION)) {
            if (!activity.isFinishing()) {
                new AlertDialog.Builder(activity)
                        .setTitle(R.string.pop_pedometer_dialog_permission_title)
                        .setMessage(R.string.pop_pedometer_dialog_permission_description)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, ACTIVATION_REQUEST_CODE);
                            }
                        }).show();
            }
        } else {
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, ACTIVATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ACTIVATION_REQUEST_CODE: {
                if ((grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    showPop();
                } else {
                    pedometerPopSwitch.setChecked(false);
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BuzzAdBenefit.unregisterSessionReadyBroadcastReceiver(this, sessionReadyReceiver);
    }
}
