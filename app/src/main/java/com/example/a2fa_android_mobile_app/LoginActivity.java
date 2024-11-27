package com.example.a2fa_android_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText etEmail, etPwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        etEmail=findViewById(R.id.etEmail);
        etPwd=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString();
                String password = etPwd.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter an email address!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
                    Toast.makeText(LoginActivity.this, "Enter a valid email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.matches("^(?=.*[A-Z])(?=.*\\d).{7,}$")) {
                    Toast.makeText(LoginActivity.this, "Password must be at least 7 characters, contain at least one uppercase letter and one number." , Toast.LENGTH_SHORT).show();
                    return;
                }

                String otp = generateOTP();
                EmailSender.sendEmail(LoginActivity.this, email, otp);

                Intent otpIntent = new Intent(LoginActivity.this, OTPActivity.class);
                otpIntent.putExtra("email", email);
                otpIntent.putExtra("otp", otp);
                startActivity(otpIntent);
            }
        });
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = random.nextInt(9999 - 1000) + 1000;
        return String.valueOf(otp);


    }}
