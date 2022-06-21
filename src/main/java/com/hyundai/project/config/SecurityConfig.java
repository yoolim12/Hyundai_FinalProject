package com.hyundai.project.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hyundai.project.service.HandsomeUserDetailsService;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private HandsomeUserDetailsService handsomeUserDetailsService;

   @Bean
   PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }//end pass..

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       //  /samle/all 모든 사용자 가능
       //  /sample/member USER 롤 사용자만
       http.authorizeRequests()
               .antMatchers("/HandsomeLogin/HandsomeLoginPage")
               .permitAll()
               .antMatchers("/mypage/mypage").hasRole("USER")
               ;

       //인가 인증 문제시 로그인 화면
       http.formLogin()
       .loginPage("/member/login")
       .usernameParameter("memail")
       .passwordParameter("mpassword")
       .loginProcessingUrl("/member/login")
       .defaultSuccessUrl("/main")
       .failureUrl("/member/loginfail")
//       .permitAll()
       .and()
       .logout()
       .logoutUrl("/member/logout")
       .logoutSuccessUrl("/main")
//       .permitAll()
       ;
       
//       http.formLogin()
//       .loginPage("/mypage/modifyPage")
//       .usernameParameter("j_username")
//       .passwordParameter("j_password")
//       .loginProcessingUrl("/mypage/modifyPage")
//       .defaultSuccessUrl("/main")
//       .failureUrl("/mypage/modifyPage")
//       ;
       
       //crsf 비활성화 --> csrf().disable( ) 해야 get 방식으로 로그아웃 처리
       http.csrf().disable();
       
       //로그 아웃 세팅
       http.logout();
   }//end configure http
   
//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	   auth.authenticationProvider(authProvider());
//   }
   
//   @Bean
//   public DaoAuthenticationProvider authProvider() {
//	   DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//	   daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//	   daoAuthenticationProvider.setUserDetailsService(handsomeUserDetailsService);
//	   return daoAuthenticationProvider;
//   }
}//end class