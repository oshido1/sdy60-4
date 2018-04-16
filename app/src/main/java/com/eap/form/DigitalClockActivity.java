package com.eap.form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DigitalClock;
import android.widget.Toast;

public class DigitalClockActivity extends AppCompatActivity {
    DigitalClock simpleDigital_clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_clock);

        simpleDigital_clock = (DigitalClock)findViewById(R.id.simpleDigital_clock);
        simpleDigital_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), simpleDigital_clock.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
