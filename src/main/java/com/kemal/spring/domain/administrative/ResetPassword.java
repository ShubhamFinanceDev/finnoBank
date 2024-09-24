package com.kemal.spring.domain.administrative;

import lombok.Data;

@Data
public class ResetPassword {
    String email;
    String newPassword;
    String confirmPassword;
}
