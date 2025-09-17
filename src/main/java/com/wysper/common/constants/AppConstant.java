package com.wysper.common.constants;

public class AppConstant {
    // ===== Subject Constants =====
    public static final String SIGNUP_GREETING_WITH_OTP_SUBJECT = "Welcome to Wysper – Verify Your Account";
    public static final String LOGIN_OTP_SUBJECT = "Your Wysper Login Code";
    public static final String PIN_RESET_SUBJECT = "Reset Your Wysper PIN";
    public static final String RESEND_OTP_SUBJECT = "Your Wysper OTP – Resent";
    public static final String ACCOUNT_VERIFIED_SUBJECT = "Your Wysper Account is Verified";
    public static final String SECURITY_ALERT_SUBJECT = "Wysper Security Alert";

    // ===== Account Creation (Greeting + OTP) =====
    public static String signupGreetingWithOtpMessage(String name, String otp) {
        return String.format("""
                Hello %s,

                Welcome to Wysper! We’re excited to have you join our secure chatting platform.
                To get started, please verify your account using the following OTP:

                %s

                Enter this code in the app to complete your signup.

                Happy chatting!
                – The Wysper Team
                """, name, otp);
    }

    // ===== Login OTP =====
    public static String loginOtpMessage(String name, String otp) {
        return String.format("""
                Hello %s,

                Your OTP for logging into Wysper is: %s
                If you didn’t request this, please ignore this email.
                """, name, otp);
    }

    // ===== PIN Reset OTP =====
    public static String pinResetMessage(String name, String otp) {
        return String.format("""
                Hello %s,

                We received a request to reset your Wysper PIN. Use the following OTP to proceed:

                %s

                If you didn’t request this, please ignore this email.
                """, name, otp);
    }

    // ===== Resend OTP =====
    public static String resendOtpMessage(String name, String otp) {
        return String.format("""
                Hello %s,

                Here is your OTP as requested:

                %s

                Use this code to complete your action. If you didn’t request this, please ignore this email.
                """, name, otp);
    }

    // ===== Account Verification Success =====
    public static String accountVerifiedMessage(String name) {
        return String.format("""
                Hello %s,

                Your Wysper account has been successfully verified. You can now enjoy secure messaging and calling!

                Thank you for using Wysper.
                """, name);
    }

    // ===== Security Alert =====
    public static String securityAlertMessage(String name) {
        return String.format("""
                Hello %s,

                We noticed a login attempt to your account. If this was you, no action is needed.
                If you didn’t attempt to log in, please change your PIN immediately.
                """, name);
    }
}
