package org.example.owncalendarserver.service;

import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.dto.LoginRequestDto;
import org.example.owncalendarserver.dto.SignupRequestDto;
import org.example.owncalendarserver.entity.User;
import org.example.owncalendarserver.entity.UserRoleEnum;
import org.example.owncalendarserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String nickName = requestDto.getNickName();
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();

        // 닉네임 중복 확인
        Optional<User> checkNickName = userRepository.findByNickName(nickName);
        if (checkNickName.isPresent()) {
            throw new IllegalStateException("중복된 닉네임이 존재합니다.");
        }

        // 회원 중복 확인
        Optional<User> checkUserName = userRepository.findByUserName(userName);
        if(checkUserName.isPresent()) {
            throw new IllegalStateException("중복된 사용자가 있습니다.");
        }

        // 사용 권환 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(nickName, userName, password, role);
        userRepository.save(user);

    }

    public void login(LoginRequestDto requestDto) {
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 이름입니다.."));

        if(password.equals(user.getPassword())) {
            // 로그인 성공 로직
            // 토큰 생성해서 저장해줘야 함?
        } else {
            throw new IllegalStateException("비밀번호를 확인해주세요.");
        }
    }
}
