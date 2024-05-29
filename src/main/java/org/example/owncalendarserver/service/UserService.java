package org.example.owncalendarserver.service;

import lombok.RequiredArgsConstructor;
import org.example.owncalendarserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
