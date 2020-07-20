package com.buzzvil.pedometer.sample;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.buzzvil.pedometer.BuzzPedometer.OnMilestoneListener;
import com.buzzvil.pedometer.BuzzPedometer.OnRewardResponseListener;
import com.buzzvil.pedometer.BuzzPedometer.OnStepHistoryListener;
import com.buzzvil.pedometer.BuzzPedometer.OnStepListener;
import com.buzzvil.pedometer.model.Milestone;
import com.buzzvil.pedometer.model.StepHistory;
import com.buzzvil.pedometer.model.UserProfile;
import com.buzzvil.pedometer.standalone.BuzzPedometerSdk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btActivate;
    private Button btDeactivate;
    private Button btDailyHistory;
    private Button btRequestReward;
    private Button btSetUserProfile;
    private Button btGetUserProfile;
    private TextView tvStepCount;
    private TextView tvActivateStatus;
    private TextView tvRewarded;
    private TextView tvRedeemable;
    private EditText etStepHistoryRange;
    private EditText etHeight;
    private EditText etWeight;

    private OnStepListener onStepListener = new OnStepListener() {
        @Override
        public void onStep(long count) {
            updateStepUI(count);
        }

        @Override
        public void onTimeSyncError() {
            Toast.makeText(MainActivity.this, "Please set to the correct time", Toast.LENGTH_SHORT).show();
        }
    };

    private OnMilestoneListener onMilestoneListener = new OnMilestoneListener() {
        @Override
        public void onReach(List<Milestone> milestones) {
            Toast.makeText(MainActivity.this, milestones.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onUpdate(List<Milestone> milestones) {
            updateMilestoneUI(milestones);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            BuzzPedometerSdk.getPedometer().setStepListener(onStepListener);
            BuzzPedometerSdk.getPedometer().setMilestoneListener(onMilestoneListener);
            updateActivationUI();
            updateMilestoneUI(BuzzPedometerSdk.getPedometer().getMilestones());
            updateStepUI(BuzzPedometerSdk.getPedometer().getStepCount());
        } else {
            disableAll();
        }
    }

    private void disableAll() {
        btLogin.setEnabled(false);
        btGetUserProfile.setEnabled(false);
        btSetUserProfile.setEnabled(false);
        btDailyHistory.setEnabled(false);
        btDeactivate.setEnabled(false);
        btActivate.setEnabled(false);
        btRequestReward.setEnabled(false);
        tvStepCount.setText("None");
        tvActivateStatus.setText("Disabled");
        tvRedeemable.setText("None");
        tvRewarded.setText("None");
        etWeight.setEnabled(false);
        etHeight.setEnabled(false);
        etStepHistoryRange.setEnabled(false);
    }

    private void initUI() {
        tvStepCount = findViewById(R.id.tvStepCount);
        tvActivateStatus = findViewById(R.id.tvActivateStatus);
        tvRedeemable = findViewById(R.id.tvRedeemable);
        tvRewarded = findViewById(R.id.tvRewarded);
        btRequestReward = findViewById(R.id.btRequestReward);

        btLogin = findViewById(R.id.btLogin);
        btActivate = findViewById(R.id.btActivate);
        btDeactivate = findViewById(R.id.btDeactivate);
        btRequestReward = findViewById(R.id.btRequestReward);
        btDailyHistory = findViewById(R.id.btGetDailyStepHistory);
        etStepHistoryRange = findViewById(R.id.etStepHistoryRange);
        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        btSetUserProfile = findViewById(R.id.btSetUserProfile);
        btGetUserProfile = findViewById(R.id.btGetUserProfile);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = "USER-ID";
                BuzzPedometerSdk.login(userId, new BuzzPedometerSdk.LoginListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "Login Succeeded", Toast.LENGTH_LONG).show();
                        updateActivationUI();
                    }

                    @Override
                    public void onFail(Throwable exception) {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        btActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuzzPedometerSdk.getPedometer().activate();
                updateActivationUI();
            }
        });

        btDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuzzPedometerSdk.getPedometer().deactivate();
                updateActivationUI();
            }
        });
        btRequestReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Milestone> milestones = BuzzPedometerSdk.getPedometer().getMilestones();
                Milestone redeemMilestone = null;

                for (Milestone milestone : milestones) {
                    if (!milestone.getRewarded() && milestone.getReached()) {
                        redeemMilestone = milestone;
                        break;
                    }
                }

                BuzzPedometerSdk.getPedometer().requestReward(redeemMilestone, new OnRewardResponseListener() {
                    @Override
                    public void onSuccess(Milestone milestone) {
                        Toast.makeText(MainActivity.this, "Request reward Succeeded, reward point: " + milestone.getPoint() + ", rewarded step: " + milestone.getStep(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPending() {
                        Toast.makeText(MainActivity.this, "Wait response from server", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "Request reward Failed..", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btDailyHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextListFragment dialog = new TextListFragment();
                String intervalString = etStepHistoryRange.getText().toString();
                if (!intervalString.isEmpty()) {
                    BuzzPedometerSdk.getPedometer().getDailyStepHistory(Integer.parseInt(intervalString), new OnStepHistoryListener() {
                        @Override
                        public void onLoad(List<StepHistory> stepHistories) {
                            ArrayList<String> stringList = new ArrayList<String>();
                            for (StepHistory stepHistory : stepHistories) {
                                String date = formatDate(stepHistory.getDate());
                                stringList.add(date + " : " + stepHistory.getStep() + " Step");
                            }
                            dialog.list = stringList;
                            dialog.show(getSupportFragmentManager(), TextListFragment.TAG);
                        }
                    });
                }
            }
        });
        btSetUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int height = Integer.parseInt(etHeight.getText().toString());
                final int weight = Integer.parseInt(etWeight.getText().toString());
                BuzzPedometerSdk.getPedometer().setUserProfile(new UserProfile(height, weight));
            }
        });

        btGetUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile up = BuzzPedometerSdk.getPedometer().getUserProfile();
                Toast.makeText(MainActivity.this, up.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void updateMilestoneUI(List<Milestone> milestones) {
        int redeemableMilestones = 0;
        int rewardedMilestones = 0;

        for (Milestone milestone : milestones) {
            if (!milestone.getRewarded() && milestone.getReached()) {
                redeemableMilestones++;
            }
            if (milestone.getRewarded()) {
                rewardedMilestones++;
            }
        }

        tvRedeemable.setText(String.valueOf(redeemableMilestones));
        tvRewarded.setText(String.valueOf(rewardedMilestones));
        btRequestReward.setEnabled("0" != tvRedeemable.getText());
    }

    private void updateStepUI(long value) {
        tvStepCount.setText(String.valueOf(value));
    }

    private void updateActivationUI() {
        if (BuzzPedometerSdk.isLoggedIn()) {
            btLogin.setEnabled(false);
            if (BuzzPedometerSdk.getPedometer().isActivated()) {
                btActivate.setEnabled(false);
                btActivate.setVisibility(View.GONE);
                btDeactivate.setEnabled(true);
                btDeactivate.setVisibility(View.VISIBLE);
                tvActivateStatus.setText("Activated!");
            } else {
                btActivate.setEnabled(true);
                btActivate.setVisibility(View.VISIBLE);
                btDeactivate.setEnabled(false);
                btDeactivate.setVisibility(View.GONE);
                tvActivateStatus.setText("Deactivated!");
            }
        } else {
            btLogin.setEnabled(true);
            btActivate.setEnabled(false);
            btActivate.setVisibility(View.VISIBLE);
            btDeactivate.setEnabled(false);
            btDeactivate.setVisibility(View.GONE);
            tvActivateStatus.setText("Deactivated!");
        }
    }

    String formatDate(String unFormattedTime) {
        String formattedTime = "";
        try {
            SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = isoDateFormat.parse(unFormattedTime);
            SimpleDateFormat customDateFormat = new SimpleDateFormat("yyyy/M/dd");
            formattedTime = customDateFormat.format(date);
            return formattedTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}