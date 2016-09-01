package com.example.mikael.durationpicker;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity implements DurationPickerFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Remove old dialogs
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                // Create and show the dialog.
                DurationPickerFragment dialog = new DurationPickerFragment();
                dialog.setMetrics("d", "h", "m");
                dialog.show(ft, "dialog");
            }
        });
    }

    @Override
    public void onDurationPickerResult(int resultCode, int days, int hours, int minutes) {
        if (resultCode == RESULT_OK) {
            //Add all selected time together
            long seconds =
                    days * 24 * 60 * 60 * 60 +
                            hours * 60 * 60 * 60 +
                            minutes * 60 * 60;

            View view = findViewById(android.R.id.content);
            Snackbar.make(view, "You picked a duration of " + days + " days, " + hours +
                    " hours and " + minutes + " minutes... that equals " + seconds + " seconds." ,
                    Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

}
