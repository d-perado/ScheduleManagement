package org.example.schedulemanagement.util.component;

import org.example.schedulemanagement.util.exception.CustomException;
import org.example.schedulemanagement.util.exception.ErrorCode;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {
    public void validate(String storedPassword, String inputPassword) {
        if (!storedPassword.equals(inputPassword)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
