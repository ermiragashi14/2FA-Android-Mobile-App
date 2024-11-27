package com.example.a2fa_android_mobile_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {

    EditText etOtp;
    Button btnVerify;
    String generatedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);

        etOtp = findViewById(R.id.etOTP);
        btnVerify = findViewById(R.id.btnVerify);

        generatedOtp = getIntent().getStringExtra("otp");

        if (generatedOtp == null || generatedOtp.isEmpty()) {
            Toast.makeText(this, "OTP not received!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOtp = etOtp.getText().toString().trim();

                if (enteredOtp.equals(generatedOtp)) {
                    Toast.makeText(OTPActivity.this, "OTP Verified Successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OTPActivity.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
