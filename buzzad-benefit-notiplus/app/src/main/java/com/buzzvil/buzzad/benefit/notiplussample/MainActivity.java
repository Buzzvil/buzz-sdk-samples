package com.buzzvil.buzzad.benefit.notiplussample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;

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

import com.buzzvil.buzzad.benefit.presentation.notification.BuzzAdNotiPlus;
import com.buzzvil.buzzad.benefit.presentation.notification.NotiPlusHourParser;
import com.buzzvil.buzzad.benefit.presentation.notification.NotiPlusRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SwitchCompat switchAdNotiRegister;
    private Button btnRegister1;
    private Button btnRegister2;
    private Button btnCustomRegister;
    private Button btnUnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchAdNotiRegister = findViewById(R.id.switchAdNotiRegister);
        btnRegister1 = findViewById(R.id.btnRegister1);
        btnRegister2 = findViewById(R.id.btnRegister2);
        btnCustomRegister = findViewById(R.id.btnCustomRegister);
        btnUnregister = findViewById(R.id.btnUnregister);

        BuzzAdNotiPlus buzzAdNotiPlus = initBuzzAdNotiPlus();

        setListener(buzzAdNotiPlus);

        switchAdNotiRegister.setChecked(buzzAdNotiPlus.isRegistered());

    }

    private void setListener(final BuzzAdNotiPlus buzzAdNotiPlus) {
        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithDescription(buzzAdNotiPlus);
            }
        });

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerWithoutDescription(buzzAdNotiPlus);
            }
        });

        btnCustomRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customRegister(buzzAdNotiPlus);
            }
        });

        btnUnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregister(buzzAdNotiPlus);
            }
        });

        switchAdNotiRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchAdNotiRegister.isChecked()) {
                    registerWithDescription(buzzAdNotiPlus);
                } else {
                    unregister(buzzAdNotiPlus);
                }
            }
        });

        switchAdNotiRegister.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                btnRegister1.setEnabled(!isChecked);
                btnRegister2.setEnabled(!isChecked);
                btnCustomRegister.setEnabled(!isChecked);
                btnUnregister.setEnabled(isChecked);
            }
        });
    }

    private void registerWithDescription(final BuzzAdNotiPlus buzzAdNotiPlus) {
        buzzAdNotiPlus.registerWithDialog(this, new BuzzAdNotiPlus.OnRegisterListener() {
            @Override
            public void onSuccess() {
                switchAdNotiRegister.setChecked(true);
            }

            @Override
            public void onCanceled() {
                switchAdNotiRegister.setChecked(false);
            }
        });
    }

    private void registerWithoutDescription(final BuzzAdNotiPlus buzzAdNotiPlus) {
        buzzAdNotiPlus.registerWithDialog(this, false, new BuzzAdNotiPlus.OnRegisterListener() {
            @Override
            public void onSuccess() {
                switchAdNotiRegister.setChecked(true);
            }

            @Override
            public void onCanceled() {
                switchAdNotiRegister.setChecked(false);
            }
        });
    }

    private void unregister(final BuzzAdNotiPlus buzzAdNotiPlus) {
        buzzAdNotiPlus.unregisterWithDialog(this, new BuzzAdNotiPlus.OnRegisterListener() {
            @Override
            public void onSuccess() {
                switchAdNotiRegister.setChecked(false);
            }

            @Override
            public void onCanceled() {
                switchAdNotiRegister.setChecked(true);
            }
        });
    }

    private void customRegister(final BuzzAdNotiPlus buzzAdNotiPlus) {
        buzzAdNotiPlus.fetchNotiPlusHoursOptionIfNeeded(
                this,
                new NotiPlusRepository.OnFetchResultListener() {
                    @Override
                    public void onFetchFail() {
                        showNotiPlusRegisterDialogStep2(
                                MainActivity.this,
                                buzzAdNotiPlus,
                                new BuzzAdNotiPlus.OnRegisterListener() {
                                    @Override
                                    public void onSuccess() {
                                        switchAdNotiRegister.setChecked(true);
                                    }

                                    @Override
                                    public void onCanceled() {
                                        switchAdNotiRegister.setChecked(false);
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFetchSuccess() {
                        showNotiPlusRegisterDialogStep2(
                                MainActivity.this,
                                buzzAdNotiPlus,
                                new BuzzAdNotiPlus.OnRegisterListener() {
                                    @Override
                                    public void onSuccess() {
                                        switchAdNotiRegister.setChecked(true);
                                    }

                                    @Override
                                    public void onCanceled() {
                                        switchAdNotiRegister.setChecked(false);
                                    }
                                }
                        );
                    }
                });
    }

    private BuzzAdNotiPlus initBuzzAdNotiPlus() {
        return new BuzzAdNotiPlus(
                App.UNIT_ID_NOTI_PLUS,
                CustomNotificationWorker.class,
                App.getNotiPlusDialogConfig()
        );
    }

    void showNotiPlusRegisterDialogStep2(
            @NonNull final Activity activity,
            @NonNull final BuzzAdNotiPlus buzzAdNotiPlus,
            @NonNull final BuzzAdNotiPlus.OnRegisterListener onRegisterListener) {
        View notiPlusDialogStep2Layout = activity.getLayoutInflater().inflate(R.layout.view_settings_dialog_register_step_2, null);
        ViewGroup layoutNotiPlusCheckbox = notiPlusDialogStep2Layout.findViewById(R.id.layoutNotiPlusCheckbox);

        final CheckBox checkBoxCheckAll = layoutNotiPlusCheckbox.findViewById(R.id.checkboxNotiPlusAll);
        final List<CheckBox> checkBoxes = new ArrayList<>();

        final NotiPlusHourParser notiPlusHourParser = new NotiPlusHourParser(activity);
        final List<Integer> checkBoxValues = new ArrayList<>();
        final List<Integer> list = buzzAdNotiPlus.getNotiPlusHoursOption(activity);
        for (int i = 0; i < list.size(); i++) {
            checkBoxValues.add(list.get(i));
            CheckBox checkBox = (CheckBox) activity.getLayoutInflater().inflate(R.layout.view_settings_dialog_register_step_2_checkbox, null);
            checkBox.setText(notiPlusHourParser.convertHourToString(list.get(i)));
            layoutNotiPlusCheckbox.addView(checkBox);
            checkBoxes.add(checkBox);
        }

        final AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(notiPlusDialogStep2Layout)
                .setPositiveButton(R.string.benefit_notiplus_settings_dialog_register_step_2_confrim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<Integer> result = new ArrayList<>();
                        for (int i = 0; i < checkBoxValues.size(); i++) {
                            if (checkBoxes.get(i).isChecked()) {
                                result.add(checkBoxValues.get(i));
                            }
                        }
                        buzzAdNotiPlus.setNotiPlusHours(activity, result);
                        onRegisterListener.onSuccess();
                        buzzAdNotiPlus.register(activity);
                    }
                })
                .setNegativeButton(R.string.benefit_notiplus_settings_dialog_register_step_2_cancel, new DialogInterface.OnClickListener() {
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
