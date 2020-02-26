package com.buzzvil.buzzscreen.sample_lock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzvil.buzzscreen.sdk.BuzzScreen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    App app;

    SwitchCompat switchLockScreenEnable;
    SwitchCompat switchNotificationEnable;
    TextView tvSnoozeTo;
    TextView tvUserName;
    Button btSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (App) getApplicationContext();

        switchLockScreenEnable = (SwitchCompat) findViewById(R.id.main_lockscreen_enable);
        switchNotificationEnable = (SwitchCompat) findViewById(R.id.main_notification_enable);
        tvSnoozeTo = (TextView) findViewById(R.id.main_snooze);
        tvUserName = (TextView) findViewById(R.id.main_username);
        btSignOut = (Button) findViewById(R.id.main_sign_out);

        switchLockScreenEnable.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (switchLockScreenEnable.isChecked()) {
                        showSnoozeSettings();
                        return true;
                    }
                }
                return false;
            }
        });

        switchLockScreenEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    app.checkAndSetBuzzScreenProfile();
                    BuzzScreen.getInstance().activate();
                    updateSnoozeMessage();
                }
                switchNotificationEnable.setEnabled(isChecked);
            }
        });

        switchNotificationEnable.setChecked(app.isNotificationEnabled());
        switchNotificationEnable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                app.enableNotification(isChecked);
            }
        });

        tvUserName.setText(PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));

        btSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app.logout();
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.main_points).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Deep link to points page", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.main_store).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Deep link to store page", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.main_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Open page \"How to use\"", Toast.LENGTH_LONG).show();
            }
        });


        findViewById(R.id.main_terms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Open page \"Terms\"", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.main_privacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Open page \"Privacy\"", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        switchLockScreenEnable.setChecked(BuzzScreen.getInstance().isActivated() && !BuzzScreen.getInstance().isSnoozed());
        updateSnoozeMessage();
    }

    int selectedPosition = 0;

    private void showSnoozeSettings() {
        selectedPosition = 0;
        CharSequence snoozeItems[] = new CharSequence[]{getString(R.string.main_snooze_0), getString(R.string.main_snooze_1), getString(R.string.main_snooze_2)};

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Snooze?");
        adb.setSingleChoiceItems(snoozeItems, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedPosition = i;
            }
        });
        adb.setNegativeButton("Cancel", null);
        adb.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (selectedPosition) {
                    case 0:
                        BuzzScreen.getInstance().snooze(60 * 60 * 2);
                        break;
                    case 1:
                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        c.set(Calendar.HOUR_OF_DAY, 0);
                        c.set(Calendar.MINUTE, 0);
                        c.set(Calendar.SECOND, 0);
                        c.set(Calendar.MILLISECOND, 0);
                        int snoozeForSecs = (int) (c.getTimeInMillis() - System.currentTimeMillis()) / 1000 + 1;
                        BuzzScreen.getInstance().snooze(snoozeForSecs);
                        break;
                    case 2:
                        BuzzScreen.getInstance().deactivate();
                        break;
                }
                updateSnoozeMessage();
                switchLockScreenEnable.setChecked(false);
            }
        });
        adb.show();
    }

    private void updateSnoozeMessage() {
        if (BuzzScreen.getInstance().isSnoozed()) {
            int snoozeTo = BuzzScreen.getInstance().getSnoozeTo();
            Date dtSnoozeTo = new Date(snoozeTo * 1000L);
            String time = new SimpleDateFormat("h:mm aa", Locale.getDefault()).format(dtSnoozeTo);
            tvSnoozeTo.setText("Snoozed until" + time);
            tvSnoozeTo.setVisibility(View.VISIBLE);
        } else {
            tvSnoozeTo.setVisibility(View.GONE);
        }
    }
}
