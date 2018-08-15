package com.bhc.startstop.user.service;

public interface PasswordWebService {

    public void resetPassword(String userName, String newPassword, String token);

    public String generateResetToken(String username);

    public boolean changePassword(String userName, String oldPassword, String newPassword, String ipAddress,
            String userAgent);
}
