package com.sparta.springcode.security;

import com.sparta.springcode.table.User;
import com.sparta.springcode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


// UserDetails는 interface(UserDetailsImpl class의)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        // 회원정보가 존재하지 않을 시 error 발생.

        return new UserDetailsImpl(user);
        // 객체를 새로 만들어서 보내준다? (user: 조회된 회원정보, UserDetails로 변환)
    }
}