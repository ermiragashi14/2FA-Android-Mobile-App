package com.example.a2fa_android_mobile_app;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    private static final String TAG = "EmailSender";
    public static void sendEmail(Context context, String recipientEmail, String otp) {

        String senderEmail = "ermiragashi177@gmail.com";
        String senderPassword = "*********";

        String host = "smtp.gmail.com";
        String port = "587";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otp);

            new Thread(() -> {
                try {
                    Transport.send(message);
                    Log.d(TAG, "Email sent successfully.");
                } catch (Exception e) {
                    Log.e(TAG, "Error sending email: " + e.getMessage());
                }
            }).start();
        } catch (Exception e) {
            Log.e(TAG, "Error creating email message: " + e.getMessage());
            Toast.makeText(context, "Error creating email message.", Toast.LENGTH_LONG).show();
        }
    }
}
