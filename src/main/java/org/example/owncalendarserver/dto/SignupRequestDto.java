package org.example.owncalendarserver.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String nickName;
    private String userName;
    private String password;
    private boolean admin;
}
