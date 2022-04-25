package com.fundamentosplatzi.fundamentos.configuration;

import com.fundamentosplatzi.fundamentos.caseuser.GetUser;
import com.fundamentosplatzi.fundamentos.caseuser.GetUserImplement;
import com.fundamentosplatzi.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {
    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
