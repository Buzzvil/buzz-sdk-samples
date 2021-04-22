package com.buzzvil.buzzad.benefit.notiplussample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

import com.buzzvil.buzzad.benefit.BuzzAdBenefit;
import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdPush;
import com.buzzvil.buzzad.benefit.presentation.notification.PushHourParser;
import com.buzzvil.buzzad.benefit.presentation.notification.PushRepository;
import com.buzzvil.buzzad.benefit.privacy.PrivacyPolicyEventListener;
import com.buzzvil.buzzad.benefit.privacy.PrivacyPolicyManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SwitchCompat switchPushRegister;
    private Button btnRegister1;
    private Button btnRegister2;
    private Button btnCustomRegister;
    private Button btnUnregister;
    private CompoundButton.OnCheckedChangeListener pushSwitchCheckChangedListener;
    private BuzzAdPush buzzAdPush;
    private PrivacyPolicyManager privacyPolicyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchPushRegister = findViewById(R.id.switchPushRegister);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister2 = findViewById(R.id.btnRegister2);
        btnCustomRegister = findViewById(R.id.btnCustomRegister);
        btnUnregister = findViewById(R.id.btnUnregister);

        buzzAdPush = initBuzzAdPush();
        privacyPolicyManager = BuzzAdBenefit.getPrivacyPolicyManager();

        setListener(buzzAdPush);

        switchPushRegister.setChecked(buzzAdPush.isRegistered(getApplicationContext()));
    }

    private void setListener(final BuzzAdPush buzzAdPush) {
        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithDescription(buzzAdPush);
            }
        });

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithoutDescription(buzzAdPush);
            }
        });

        btnCustomRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (privacyPolicyManager.isConsentGranted()) {
                    customRegister(buzzAdPush);
                } else {
                    privacyPolicyManager.showConsentUI(MainActivity.this, new PrivacyPolicyEventListener() {
                        @Override
                        public void onUpdated(boolean accepted) {
                            if (accepted) {
                                customRegister(buzzAdPush);
                            }
                        }
                    });
                }
            }
        });

        btnUnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregister(buzzAdPush);
            }
        });

        pushSwitchCheckChangedListener = buildPushSwitchCheckChangedListener(buzzAdPush);
        switchPushRegister.setOnCheckedChangeListener(pushSwitchCheckChangedListener);
    }

    private CompoundButton.OnCheckedChangeListener buildPushSwitchCheckChangedListener(BuzzAdPush buzzAdPush) {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    setPushState(false);
                    buzzAdPush.registerWithDialog(MainActivity.this, true, new BuzzAdPush.OnRegisterListener() {
                        @Override
                        public void onSuccess() {
                            setPushState(true);
                        }

                        @Override
                        public void onCanceled() {
                            setPushState(false);
                        }
                    });
                } else {
                    buzzAdPush.unregisterWithDialog(MainActivity.this, new BuzzAdPush.OnRegisterListener() {
                        @Override
                        public void onSuccess() {
                            setPushState(false);
                        }

                        @Override
                        public void onCanceled() {
                            setPushState(true);
                        }
                    });
                }
            }
        };
    }

    private void updateButtonState(boolean isChecked) {
        btnRegister1.setEnabled(!isChecked);
        btnRegister2.setEnabled(!isChecked);
        btnCustomRegister.setEnabled(!isChecked);
        btnUnregister.setEnabled(isChecked);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePushStatus();
    }

    private void updatePushStatus() {
        final PrivacyPolicyManager privacyPolicyManager = BuzzAdBenefit.getPrivacyPolicyManager();
        if (privacyPolicyManager != null && buzzAdPush != null ) {
            setPushState(privacyPolicyManager.isConsentGranted() && buzzAdPush.isRegistered(this));
        }
    }

    private void setPushState(boolean check) {
        switchPushRegister.setOnCheckedChangeListener(null);
        switchPushRegister.setChecked(check);
        switchPushRegister.setOnCheckedChangeListener(pushSwitchCheckChangedListener);
        updateButtonState(check);
    }

    private void registerWithDescription(final BuzzAdPush buzzAdPush) {
        buzzAdPush.registerWithDialog(this, new BuzzAdPush.OnRegisterListener() {
            @Override
            public void onSuccess() {
                setPushState(true);
            }

            @Override
            public void onCanceled() {
                setPushState(false);
            }
        });
    }

    private void registerWithoutDescription(final BuzzAdPush buzzAdPush) {
        buzzAdPush.registerWithDialog(this, false, new BuzzAdPush.OnRegisterListener() {
            @Override
            public void onSuccess() {
                setPushState(true);
            }

            @Override
            public void onCanceled() {
                setPushState(false);
            }
        });
    }

    private void unregister(final BuzzAdPush buzzAdPush) {
        buzzAdPush.unregisterWithDialog(this, new BuzzAdPush.OnRegisterListener() {
            @Override
            public void onSuccess() {
                setPushState(false);
            }

            @Override
            public void onCanceled() {
                setPushState(true);
            }
        });
    }

    private void customRegister(final BuzzAdPush buzzAdPush) {
        buzzAdPush.fetchPushHoursOptionIfNeeded(
                this,
                new PushRepository.OnFetchResultListener() {
                    @Override
                    public void onFetchFail() {
                        showPushRegisterDialogStep2(
                                MainActivity.this,
                                buzzAdPush,
                                new BuzzAdPush.OnRegisterListener() {
                                    @Override
                                    public void onSuccess() {
                                        setPushState(true);
                                    }

                                    @Override
                                    public void onCanceled() {
                                        setPushState(false);
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFetchSuccess() {
                        showPushRegisterDialogStep2(
                                MainActivity.this,
                                buzzAdPush,
                                new BuzzAdPush.OnRegisterListener() {
                                    @Override
                                    public void onSuccess() {
                                        setPushState(true);
                                    }

                                    @Override
                                    public void onCanceled() {
                                        setPushState(false);
                                    }
                                }
                        );
                    }
                });
    }

    private BuzzAdPush initBuzzAdPush() {
        return new BuzzAdPush(
                App.UNIT_ID_FEED,
                CustomNotificationWorker.class,
                App.getPushDialogConfig()
        );
    }

    void showPushRegisterDialogStep2(
            @NonNull final Activity activity,
            @NonNull final BuzzAdPush buzzAdPush,
            @NonNull final BuzzAdPush.OnRegisterListener onRegisterListener) {
        View pushDialogStep2Layout = activity.getLayoutInflater().inflate(R.layout.view_settings_dialog_register_step_2, null);
        ViewGroup layoutPushCheckbox = pushDialogStep2Layout.findViewById(R.id.layouPushCheckbox);

        final CheckBox checkBoxCheckAll = layoutPushCheckbox.findViewById(R.id.checkboxPushAll);
        final List<CheckBox> checkBoxes = new ArrayList<>();

        final PushHourParser pushHourParser = new PushHourParser(activity);
        final List<Integer> checkBoxValues = new ArrayList<>();
        final List<Integer> list = buzzAdPush.getPushHoursOption(activity);
        for (int i = 0; i < list.size(); i++) {
            checkBoxValues.add(list.get(i));
            CheckBox checkBox = (CheckBox) activity.getLayoutInflater().inflate(R.layout.view_settings_dialog_register_step_2_checkbox, null);
            checkBox.setText(pushHourParser.convertHourToString(list.get(i)));
            layoutPushCheckbox.addView(checkBox);
            checkBoxes.add(checkBox);
        }

        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(pushDialogStep2Layout)
                .setPositiveButton(R.string.benefit_push_settings_dialog_register_step_2_confrim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Integer> result = new ArrayList<>();
                        for (int i = 0; i < checkBoxValues.size(); i++) {
                            if (checkBoxes.get(i).isChecked()) {
                                result.add(checkBoxValues.get(i));
                            }
                        }
                        buzzAdPush.setPushHours(activity, result);
                        onRegisterListener.onSuccess();
                        buzzAdPush.register(activity);
                    }
                })
                .setNegativeButton(R.string.benefit_push_settings_dialog_register_step_2_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onRegisterListener.onCanceled();
                    }
                })
                .setCancelable(false)
                .create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark));

        checkBoxCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckBoxState(checkBoxCheckAll.isChecked(), checkBoxes);
                checkEnableState(activity, checkBoxes, dialog);
            }
        });

        for (CheckBox cb : checkBoxes) {
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkIfAllChecked(checkBoxCheckAll, checkBoxes);
                    checkEnableState(activity, checkBoxes, dialog);
                }
            });
        }
    }

    private void setCheckBoxState(Boolean checked, List<CheckBox> checkBoxes) {
        for (CheckBox cb : checkBoxes) {
            cb.setChecked(checked);
        }
    }

    private void checkIfAllChecked(CheckBox checkBoxAll, List<CheckBox> checkBoxes) {
        boolean isChecked = true;
        for (CheckBox cb : checkBoxes) {
            if (!cb.isChecked()) {
                isChecked = false;
                break;
            }
        }
        checkBoxAll.setChecked(isChecked);
    }

    private void checkEnableState(Context context, List<CheckBox> checkBoxes, AlertDialog alertDialog) {
        boolean enable = false;
        for (CheckBox cb : checkBoxes) {
            if (cb.isChecked()) {
                enable = true;
                break;
            }
        }
        if (enable) {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        } else {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        }
    }
}
