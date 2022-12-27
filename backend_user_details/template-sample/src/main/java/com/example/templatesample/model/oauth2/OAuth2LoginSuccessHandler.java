package com.example.templatesample.model.oauth2;

import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.AuthenticationProvider;
import com.example.templatesample.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ProfileService profileService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = customOAuth2User.getEmail();
        String name = customOAuth2User.getName();
        Student student = profileService.getStudentByEmail(email);
        if(student == null) {
            //create new profile here
            profileService.createStudentAfterGoogleLogin(name, email, AuthenticationProvider.GOOGLE);
        } else {
            profileService.updateStudentAfterGoogleLogin(name, AuthenticationProvider.GOOGLE, student);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
