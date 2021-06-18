package uz.developer.cardtransferapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.developer.cardtransferapp.service.MyAuthService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTprovider jwTprovider;

    @Autowired
    MyAuthService myAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {



        //Requestdan tokenni olish
        String token = httpServletRequest
                .getHeader("Authorization");
        System.out.println(token);

        if (token!=null&& token.startsWith("Bearer")){
            token=token.substring(7);
            //tokenni valedatsiyadan o'tkazdik(bunda token buzilganmi buzilmaganligini muddati o'tmaganini...)
            boolean validateToken = jwTprovider.validateToken(token);
            if (validateToken){
                //tokendan usernameni olish
                String userName = jwTprovider.getUserNameFromToken(token);
                //username orqali userdetails ni oldik
                UserDetails userDetails = myAuthService.loadUserByUsername(userName);

                //userdetails orqali authentication yaratib oldik
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                System.out.println(SecurityContextHolder.getContext().getAuthentication());
                //sistemaga kim kirganligini o'rnatdik

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }

        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
