package org.example.hgproject.user.service;

import org.example.hgproject.user.repository.UserRepository;
import org.example.hgproject.user.dto.JoinDto;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDto joinDto){
        String userName = joinDto.getUserName();
        String passWord = joinDto.getPassWord();

        Boolean isExist = userRepository.existsByUserName(userName);

        if (userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }

        UserEntity data = new UserEntity();

        data.setUserName(userName);
        data.setPassWord(bCryptPasswordEncoder.encode(passWord));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
