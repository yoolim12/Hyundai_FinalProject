package com.hyundai.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.hyundai.project.handler.MemberLoginSuccessHandler;

import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
    public RoleHierarchyImpl roleHierarchyImpl() {
        log.info("실행");
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        roleHierarchyImpl.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE > ROLE_USER");
        return roleHierarchyImpl;
    }
	
	@Bean
    public MemberLoginSuccessHandler successHandler(){
        return new MemberLoginSuccessHandler(passwordEncoder());
    }//end CLu..
	

   @Bean
   PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }//end pass..

   @Override
   protected void configure(HttpSecurity http) throws Exception {
	 //ClubLoginSuccessHandler 등록
	    
	    
       //  /samle/all 모든 사용자 가능
       //  /sample/member USER 롤 사용자만
       http.authorizeRequests()
               .antMatchers("/mypage").hasRole("USER")
               .antMatchers("/cart").hasRole("USER")
               .antMatchers("/order").hasRole("USER")
               .antMatchers("/back/form").hasRole("ADMIN")
               ;

       //인가 인증 문제시 로그인 화면
       http.formLogin()
       		.loginPage("/member/login")
       		.usernameParameter("memail")
       		.passwordParameter("mpassword")
       		.loginProcessingUrl("/member/login")
       		.defaultSuccessUrl("/main")
       		.failureUrl("/member/loginfail")
//       	.permitAll()
       		.and()
       		.logout()
       		.logoutUrl("/member/logout")
       		.logoutSuccessUrl("/main")
       		.invalidateHttpSession(true)
       		.deleteCookies("remember-me","JSESSION_ID");
//       	.permitAll()
       		;
       
       // 구글 소셜 로그인
       http.oauth2Login().successHandler(successHandler());
       
       http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
       
       //crsf 비활성화 --> csrf().disable( ) 해야 get 방식으로 로그아웃 처리
       //http.csrf().disable();
       
       //로그 아웃 세팅
       http.logout();
   }//end configure http
   
}//end class