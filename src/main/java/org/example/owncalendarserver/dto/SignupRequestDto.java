package org.example.owncalendarserver.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String nickName;

    @Size(min = 4, max = 10, message = "4자 이상 10자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "소문자와 숫자로만 구성되어야 합니다.")
    private String userName;

    @Size(min = 8, max = 15, message = "8자 이상, 15자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "알파벳과 숫자로만 구성되어야 합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}
