package org.example.owncalendarserver.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.LoginRequestDto;
import org.example.owncalendarserver.dto.SignupRequestDto;
import org.example.owncalendarserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "회원가입이 성공적으로 이루어졌습니다.");
        response.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);

        Map<String, Object> responses = new HashMap<>();
        responses.put("message", "로그인이 성공적으로 이루어졌습니다.");
        responses.put("status", HttpStatus.OK.value());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
