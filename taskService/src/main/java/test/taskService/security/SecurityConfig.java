package test.taskService.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Value("${spring.security.debug:false}")
    boolean securityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable) // Отключили csrf
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> // вызываем авторизацию всех http запросов:
                        authorizationManagerRequestMatcherRegistry // передаем менеджера сопоставления запросов с регистром,
                                .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN") // выбираем метод и к нему возможную роль
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN") // указываем для страницы админа и все последующие страницы,
                                // то есть, доступ к нашему endpoint admin имеет только администратор
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // так же делаем доступ для юзера
                                .requestMatchers("/login/**").permitAll() // на эту страницу могу заходить все
//                                .requestMatchers("/actuator/**").hasRole("ADMIN") // В actuator сможет зайти только админ
                                .anyRequest() // все последующие запросы
                                .authenticated()) // идут по аутентификации
                .httpBasic(Customizer.withDefaults()) // описывает базовую конфигурацию для доступа
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        // отключаем доступ к:
        return web -> web.debug(securityDebug)
                .ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
