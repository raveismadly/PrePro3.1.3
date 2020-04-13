package com.raves.demo.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                response.sendRedirect("user");
                //  break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("login");
            }
        }
    }
}
//    тут надо дописать куда отправить админа и юзера
//     7. Настройте LoginSuccessHandler так, чтобы админа после логина направляло на страницу /admin,
//     а юзера на его страницу.

