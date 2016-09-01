package com.example.mikael.durationpicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mikael on 02/02/16.
 */

public class DurationPickerFragment extends AppCompatDialogFragment {
    private int days = 0;
    private int hours = 0;
    private int minutes = 0;
    private TextView r0;
    private TextView r1;
    private TextView r2;
    private TextView r3;
    private TextView r4;
    private TextView r5;
    private String mMetric54 = "s";
    private String mMetric32 = "m";
    private String mMetric10 = "h";

    public Listener mListener;

    // Empty constructor required for DialogFragment
    public DurationPickerFragment() {

    }

    public void setMetrics(String metric54, String metric32, String metric10) {
        mMetric54 = metric54;
        mMetric32 = metric32;
        mMetric10 = metric10;
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    interface Listener {
        void onDurationPickerResult(int requestCode, int days, int hours, int minutes);
    }

    private void leftShiftResultDigits(CharSequence value) {
        r5.setText(r4.getText());
        r4.setText(r3.getText());
        r3.setText(r2.getText());
        r2.setText(r1.getText());
        r1.setText(r0.getText());
        r0.setText(value);
    }

    private void rightShiftResultDigits(CharSequence value) {
        r0.setText(r1.getText());
        r1.setText(r2.getText());
        r2.setText(r3.getText());
        r3.setText(r4.getText());
        r4.setText(r5.getText());
        r5.setText(value);
    }

    private void updateResult() {
        days = Integer.valueOf(r5.getText().toString() + r4.getText());
        hours = Integer.valueOf(r3.getText().toString() + r2.getText());
        minutes = Integer.valueOf(r1.getText().toString() + r0.getText());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.duration_picker, null);

        r0 = (TextView) view.findViewById(R.id.result_0);
        r1 = (TextView) view.findViewById(R.id.result_1);
        r2 = (TextView) view.findViewById(R.id.result_2);
        r3 = (TextView) view.findViewById(R.id.result_3);
        r4 = (TextView) view.findViewById(R.id.result_4);
        r5 = (TextView) view.findViewById(R.id.result_5);

        r0.setText("0");
        r1.setText("0");
        r2.setText("0");
        r3.setText("0");
        r4.setText("0");
        r5.setText("0");

        TextView metricView54 = (TextView) view.findViewById(R.id.metric_5_4);
        TextView metricView32 = (TextView) view.findViewById(R.id.metric_3_2);
        TextView metricView10 = (TextView) view.findViewById(R.id.metric_1_0);

        metricView54.setText(mMetric54);
        metricView32.setText(mMetric32);
        metricView10.setText(mMetric10);


        View.OnClickListener keypadListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                leftShiftResultDigits(((Button) v).getText());
            }
        };

        view.findViewById(R.id.button_0).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_1).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_2).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_3).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_4).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_5).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_6).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_7).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_8).setOnClickListener(keypadListener);
        view.findViewById(R.id.button_9).setOnClickListener(keypadListener);

        View.OnClickListener backspaceListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                rightShiftResultDigits("0");
            }
        };

        view.findViewById(R.id.button_backspace).setOnClickListener(backspaceListener);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                DurationPickerFragment.Listener activity = (DurationPickerFragment.Listener) getActivity();
                                updateResult();
                                activity.onDurationPickerResult(Activity.RESULT_OK, days, hours, minutes);
                            }
                        }
                )
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        DurationPickerFragment.Listener activity = (DurationPickerFragment.Listener) getActivity();
                        updateResult();
                        activity.onDurationPickerResult(Activity.RESULT_CANCELED, days, hours, minutes);
                    }
                });
                return builder.create();
    }
}
