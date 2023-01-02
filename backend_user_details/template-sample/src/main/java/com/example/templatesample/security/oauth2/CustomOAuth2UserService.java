package com.example.templatesample.security.oauth2;

import com.example.templatesample.exception.OAuth2AuthenticationProcessingException;
import com.example.templatesample.model.ProfileDetails;
import com.example.templatesample.model.Student;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.security.oauth2.user.OAuth2UserInfo;
import com.example.templatesample.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);


        try {
            System.out.println("request" + oAuth2UserRequest);

            System.out.println("a " + oAuth2User);
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        }
        catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(oAuth2UserInfo.getEmail().isEmpty()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<Student> studentOptional = profileRepository.findStudentByEmail(oAuth2UserInfo.getEmail());
        Student student;
        if(studentOptional.isPresent()) {
            student = studentOptional.get();
            student = updateExistingUser(student, oAuth2UserInfo);
        } else {
            throw new OAuth2AuthenticationProcessingException("This email does not link with any account");
        }
        System.out.println("Student" + student);
        System.out.println("oauth2User" + oAuth2User.getAttributes());

        return ProfileDetails.create(student, oAuth2User.getAttributes());

    }

    private Student updateExistingUser(Student existingStudent, OAuth2UserInfo oAuth2UserInfo) {
        existingStudent.setName(oAuth2UserInfo.getName());
        existingStudent.setAvatar(oAuth2UserInfo.getImageUrl());
        return profileRepository.save(existingStudent);
    }

}
