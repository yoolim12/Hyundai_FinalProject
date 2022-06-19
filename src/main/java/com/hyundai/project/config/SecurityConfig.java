package com.hyundai.project.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Bean
   PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }//end pass..

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       //  /samle/all 모든 사용자 가능
       //  /sample/member USER 롤 사용자만
//       http.authorizeRequests()
//               .antMatchers("/samle/all").permitAll()
//               .antMatchers("/sample/member").hasRole("USER");

       //인가 인증 문제시 로그인 화면
       http.formLogin().loginPage("/HandsomeLogin/HandsomeLoginPage");
       
       //crsf 비활성화 --> csrf().disable( ) 해야 get 방식으로 로그아웃 처리
       http.csrf().disable();
       
       //로그 아웃 세팅
       http.logout();
   }//end configure http
}//end class