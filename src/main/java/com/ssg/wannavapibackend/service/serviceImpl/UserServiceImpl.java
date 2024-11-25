package com.ssg.wannavapibackend.service.serviceImpl;

import com.ssg.wannavapibackend.domain.User;
import com.ssg.wannavapibackend.dto.response.KakaoResponseDTO;
import com.ssg.wannavapibackend.repository.UserRepository;
import com.ssg.wannavapibackend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Boolean saveUser(User user) {

        return null;
    }

    @Override
    public KakaoResponseDTO getUser(Long userId) {
        User user = userRepository.findAllById(userId);
        return new KakaoResponseDTO(user.getId(), user.getEmail(), user.getUsername());
    }
}
