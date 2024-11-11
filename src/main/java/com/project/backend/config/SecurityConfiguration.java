package com.project.backend.config;

import com.project.backend.security.AuthenticationFilter;
import com.project.backend.security.CustomAccessDeniedHandler;
import com.project.backend.security.CustomAuthenticationEntryPoint;
import com.project.backend.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring의 설정 클래스임을 나타내는 어노테이션
@Configuration
// Spring Security의 웹 보안 기능을 활성화하는 어노테이션
@EnableWebSecurity
// Spring Security에서 사용되는 필터 체인 구성, HttpSecurity 객체를 통해 보안 설정 세부 조정 가능
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.csrf((csrf) -> csrf.disable()) : CSRF 보호 기능 비활성화
        // http.cors((c) -> c.disable()) : CORS 설정 비활성화 = 기본적인 출처 간 제한이 해제됨 (클라이언트 - 서버 간 서로 다른 출처에서 요청을 주고받을 때 발생하는 보안문제 해결을 위해 사용됨)
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)

                // 특정 경로에 인증 없이 접근 허용
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/error",
                                "/users/login",
                                "/users/info",
                                "/api/aladin-books",
                                "/api/book-detail",
                                "/api/search"
                        ).permitAll() // 경로 허용 설정
                        .requestMatchers(
                                "/api/user/admin"
                        ).hasRole("admin")
                        .anyRequest().authenticated()) // 그 외 경로는 인증 필요
                .exceptionHandling((httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                                .accessDeniedHandler(new CustomAccessDeniedHandler())))
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter(tokenProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
