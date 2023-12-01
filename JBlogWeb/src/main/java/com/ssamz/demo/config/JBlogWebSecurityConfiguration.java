package com.ssamz.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ssamz.demo.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class JBlogWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	// 사용자가 입력한 username으로 User객체를 검색하고 password를 비교한다
	// configure 메소드는 Spring Security의 인증메커니즘을 설정하는 곳.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// antMatcher 메소드는 Spring Security에서 HTTP 요청 경로를 패턴으로 매칭하는 역할
	// Ant 스타일의 경로 패턴은 규칙을 가지고 있는데,
	// ? : 한 문자와 매칭 | *: 한 경로 세그먼트와 매칭. ex) /* = /test는 되지만 /123은 안된다.
	// **: 여러경로 세그먼트와 매칭 | ex) /** = /test, /123 모두 매칭 가능.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// 인증 없이 접근을 허용하는 경로
    	http.authorizeRequests().antMatchers("/webjars/**", "/js/**", "/image/**",
    			"/", "/auth/**", "/jblog/view").permitAll();
    
    	// 나머지 경로는 인증이 필요하다.
    	http.authorizeRequests().anyRequest().authenticated();
    	
    	// CSRF 토큰을 받지 않음
    	http.csrf().disable();
    	
    	// 기본 로그인 화면 제공
    	http.formLogin().loginPage("/auth/login")	// 로그인 페이지 URL
    	.loginProcessingUrl("/auth/securitylogin")	// 로그인 정보 처리 URL
    	.defaultSuccessUrl("/jblog/view", true);  // 로그인 성공 후 /jblog/view로 리다이렉트;
    	
    	// 로그아웃 설정
    	http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/jblog/view");
    }
}
