package test.taskService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class TaskDetailsService {
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder){
        // Создаем конкретную имплементацию
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Далее создаем пользователя
        manager.createUser(User.withUsername("user") // Создавая пользователя, задаем ему имя
                .password(bCryptPasswordEncoder.encode("password")) // Создаем пароль, кодируя его через bCryptPasswordEncoder
                .roles("USER") // Указываем роль
                .build()); // Создаем пользователя
        manager.createUser(User.withUsername("admin")// Создавая пользователя, задаем ему имя
                .password(bCryptPasswordEncoder.encode("admin")) // Создаем пароль, кодируя его через bCryptPasswordEncoder
                .roles("ADMIN", "USER") // Указываем роль
                .build()); // Создаем пользователя

        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
