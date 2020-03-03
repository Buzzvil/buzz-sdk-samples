package com.buzzvil.buzzscreen.sample_lock_light;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buzzvil.buzzscreen.migration.MigrationClient;
import com.buzzvil.buzzscreen.sdk.BuzzScreen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Views
    private View topLayout;
    private ProgressBar pbLoading;
    private ImageView ivStatus;
    private TextView tvTitle;
    private TextView tvMessage;

    private View errorLayout;
    private Button btnError;
    private Button btnDeactivateOnError;
    private View switchLayout;
    private Button btnSwitchOn;
    private Button btnSwitchOff;

    private int snoozeSelectedPosition = 0;

    // for migration
    private MigrationClient migrationClient = new MigrationClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // init views
        topLayout = (RelativeLayout) findViewById(R.id.main_top_layout);
        pbLoading = (ProgressBar) findViewById(R.id.main_top_loading);
        ivStatus = (ImageView) findViewById(R.id.main_top_status);
        tvTitle = (TextView)findViewById(R.id.main_title);
        tvMessage = (TextView) findViewById(R.id.main_message);

        errorLayout = findViewById(R.id.main_error_layout);
        btnError = findViewById(R.id.main_error_button);
        btnDeactivateOnError = findViewById(R.id.main_error_deactivate_button);
        switchLayout = findViewById(R.id.main_switch_layout);
        btnSwitchOn = (Button)findViewById(R.id.main_switch_on);
        btnSwitchOff = (Button)findViewById(R.id.main_switch_off);

        btnSwitchOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuzzScreen.getInstance().activate();
                updateSwitchLayout();
            }
        });

        btnSwitchOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnoozeSettings();
            }
        });

        findViewById(R.id.main_go_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchApp(App.MAIN_APP_PACKAGE);
            }
        });

        BuzzScreen.getInstance().launch();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAvailability();
    }

    private void checkAvailability() {
        showLoading();
        migrationClient.checkAvailability(new MigrationClient.OnCheckAvailabilityListener() {
            @Override
            public void onAvailable(boolean autoActivated) {
                if (autoActivated) {
                    showAutoActivatedDialog();
                }
                showSwitchLayout();
            }

            @Override
            public void onError(MigrationClient.AvailabilityCheckError error) {
                switch (error) {
                    case MAIN_APP_NOT_INSTALLED:
                        showErrorLayout(
                                R.string.main_error_install_title,
                                R.string.main_error_install_message,
                                R.string.main_error_install_button,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        launchMainAppMarket();
                                    }
                                });
                        break;
                    case MAIN_APP_MIGRATION_NOT_SUPPORTED:
                        showErrorLayout(
                                R.string.main_error_update_title,
                                R.string.main_error_update_message,
                                R.string.main_error_update_button,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        launchMainAppMarket();
                                    }
                                });
                        break;
                    case NOT_ENOUGH_USER_INFO:
                        showErrorLayout(
                                R.string.main_error_agree_title,
                                R.string.main_error_agree_message,
                                R.string.main_error_agree_button,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (TextUtils.isEmpty(App.DEEP_LINK_ONBOARDING)) {
                                            launchApp(App.MAIN_APP_PACKAGE);
                                        } else {
                                            launchLink(App.DEEP_LINK_ONBOARDING);
                                        }
                                    }
                                });
                        break;
                    case UNKNOWN_ERROR:
                        showErrorLayout(
                                R.string.main_error_unknown_title,
                                R.string.main_error_unknown_message,
                                R.string.main_error_unknown_button,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        checkAvailability();
                                    }
                                });
                        break;
                }
            }
        });
    }

    private void showAutoActivatedDialog() {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.main_auto_activated_title);
        adb.setMessage(R.string.main_auto_activated_message);
        adb.setNegativeButton(R.string.main_auto_activated_close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        adb.setPositiveButton(R.string.main_auto_activated_show_lock_screen, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                BuzzScreen.getInstance().showLockScreen();
                finish();
            }
        });
        adb.show();
    }

    private void showLoading() {
        tvTitle.setText(R.string.main_loading);
        switchLayout.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        topLayout.setBackgroundColor(getResources().getColor(R.color.topDark));
        pbLoading.setVisibility(View.VISIBLE);
    }

    private void showErrorLayout(int titleResId, int messageResId, int btnResId, View.OnClickListener buttonClickListener) {
        pbLoading.setVisibility(View.GONE);
        ivStatus.setImageResource(R.drawable.ic_circle_warning);
        errorLayout.setVisibility(View.VISIBLE);
        tvTitle.setText(titleResId);
        tvMessage.setVisibility(View.VISIBLE);
        tvMessage.setText(messageResId);
        btnError.setText(btnResId);
        btnError.setOnClickListener(buttonClickListener);

        if (BuzzScreen.getInstance().isActivated()) {
            btnDeactivateOnError.setVisibility(View.VISIBLE);
            btnDeactivateOnError.setText(getString(R.string.main_switch_off));
            btnDeactivateOnError.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BuzzScreen.getInstance().deactivate();
                    btnDeactivateOnError.setVisibility(View.GONE);
                }
            });
        } else {
            btnDeactivateOnError.setVisibility(View.GONE);
        }
    }

    private void showSwitchLayout() {
        pbLoading.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        switchLayout.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.VISIBLE);
        updateSwitchLayout();
    }

    private void updateSwitchLayout() {
        if (BuzzScreen.getInstance().isActivated() && !BuzzScreen.getInstance().isSnoozed()) {
            ivStatus.setImageResource(R.drawable.ic_circle_check);
            topLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            btnSwitchOn.setVisibility(View.GONE);
            btnSwitchOff.setVisibility(View.VISIBLE);
            tvTitle.setText(R.string.main_switch_on_status);
        } else {
            ivStatus.setImageResource(R.drawable.ic_circle_x);
            topLayout.setBackgroundColor(getResources().getColor(R.color.topDark));
            btnSwitchOn.setVisibility(View.VISIBLE);
            btnSwitchOff.setVisibility(View.GONE);
            tvTitle.setText(R.string.main_switch_off_status);
        }
        updateSnoozeMessage();
    }


    @Override
    protected void onPause() {
        super.onPause();
        migrationClient.abort();
    }

    private void showSnoozeSettings() {
        snoozeSelectedPosition = 0;
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.main_snooze_title);
        adb.setSingleChoiceItems(R.array.snooze_strings, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                snoozeSelectedPosition = i;
            }
        });
        adb.setNegativeButton(android.R.string.cancel, null);
        adb.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (snoozeSelectedPosition) {
                    case 0:
                        BuzzScreen.getInstance().snooze(60 * 60 * 2);
                        break;
                    case 1:
                        BuzzScreen.getInstance().snooze(60 * 60 * 6);
                        break;
                    case 2:
                        BuzzScreen.getInstance().snooze(60 * 60 * 24);
                        break;
                    case 3:
                        BuzzScreen.getInstance().deactivate();
                        break;
                }
                updateSwitchLayout();
            }
        });
        adb.show();
    }

    // TODO localization
    private void updateSnoozeMessage() {
        if (BuzzScreen.getInstance().isSnoozed()) {
            int snoozeTo = BuzzScreen.getInstance().getSnoozeTo();
            Date dtSnoozeTo = new Date(snoozeTo * 1000L);

            String dateTemplate;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                dateTemplate = DateFormat.getBestDateTimePattern(Locale.getDefault(), "E d MMM h:mm aa");
            } else {
                Locale locale = Locale.getDefault();
                if (locale.equals(Locale.KOREA)) {
                    dateTemplate = "MMM d일 h:mm aa";
                } else if (locale.equals(Locale.JAPAN)) {
                    dateTemplate = "MMM d日 h:mm aa";
                } else if (locale.equals(Locale.US)) {
                    dateTemplate = "MMM d h:mm aa";
                } else {
                    dateTemplate = "d MMM h:mm aa";
                }
            }

            String time = new SimpleDateFormat(dateTemplate, Locale.getDefault()).format(dtSnoozeTo);

            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(time + getString(R.string.main_snooze_on_time));
        } else {
            tvMessage.setVisibility(View.GONE);
        }
    }

    private void launchMainAppMarket() {
        try {
            launchLink("market://details?id=" + App.MAIN_APP_PACKAGE);
        } catch (ActivityNotFoundException e) {
            launchLink("https://play.google.com/store/apps/details?id=" + App.MAIN_APP_PACKAGE);
        }
    }

    private void launchApp(String packageName) {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void launchLink(String actionLink) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(actionLink));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
