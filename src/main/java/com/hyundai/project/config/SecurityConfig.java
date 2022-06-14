package com.hyundai.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.handler.ClubLoginSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    //ClubLoginSuccessHandler 등록
    @Bean
    public ClubLoginSuccessHandler successHandler(){
        return new ClubLoginSuccessHandler(passwordEncoder());
    }//end CLu..  
 
    @Bean
    public RoleHierarchyImpl roleHierarchyImpl() {
        log.info("실행");
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        roleHierarchyImpl
        .setHierarchy("ROLE_ADMIN > ROLE_MANAGER > ROLE_USER");
        return roleHierarchyImpl;
    } // end roleHierarchyImpl
    
    // 비밀번호 인코더
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } // end passwordEncoder
 
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// 인가 인증 문제시 로그인 화면
    	http.formLogin()
    		.defaultSuccessUrl("/home") //로그인 성공했을 때 이동
    		.loginPage("/loginForm")
    		.loginProcessingUrl("/login");
    	
    	http.oauth2Login();
        
        // crsf 비활성화
        http.csrf().disable();
        
        // 로그 아웃 세팅
        http.logout()
        	.logoutSuccessUrl("/home");

        //일반 from 로그인 rememberMe 설정
        http.rememberMe()  
           	.key("remember")
           	.rememberMeParameter("remember-me")
            .tokenValiditySeconds( 60 * 60 * 24 * 7) //7day
            .userDetailsService(userDetailsService())
            .authenticationSuccessHandler(successHandler());

    } // end configure
} // end class


