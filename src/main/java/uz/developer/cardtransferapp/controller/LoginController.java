package uz.developer.cardtransferapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.developer.cardtransferapp.payload.LoginDto;
import uz.developer.cardtransferapp.security.JWTprovider;
import uz.developer.cardtransferapp.service.MyAuthService;

@RestController
@RequestMapping("/api/user/login")
public class LoginController {

    @Autowired
    MyAuthService myAuthService;

    @Autowired
    JWTprovider jwTprovider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity login(@RequestBody LoginDto loginDto){



        try {

         ///bu avtomat loadUserByUsername methodni ishlatadi
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            authenticationManager.authenticate(authentication);
            String generateToken = jwTprovider.generateToken(loginDto.getUsername());

            return ResponseEntity.ok(generateToken);


        }catch (BadCredentialsException e){
            return ResponseEntity.ok("login yoki parol xtao");

        }
    }

}
