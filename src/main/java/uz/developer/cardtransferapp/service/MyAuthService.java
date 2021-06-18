package uz.developer.cardtransferapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    List<User> users;





  @Autowired
   PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        users=new ArrayList<>(
                Arrays.asList(
                        new User("user1", passwordEncoder.encode( "1111"),new ArrayList<>()),
                        new User("user2",passwordEncoder.encode("2222"),new ArrayList<>()),
                        new User("user3",passwordEncoder.encode("3333"),new ArrayList<>()),
                        new User("user4",passwordEncoder.encode("4444"),new ArrayList<>())

                )
        );


        for (User user : users) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UsernameNotFoundException("user not found");
    }
}
