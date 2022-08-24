package ru.kata.spring.boot_security.demo.configs.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.configs.handler.LoginSuccessHandler;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService uds;

    public WebSecurityConfig(UserDetailsService userDetailsService) {

        this.uds = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()//Настроить страницу входа
//        loginPage()  — пользовательская страница входа
//        loginProcessingUrl() — URL-адрес для отправки имени пользователя и пароля.
//        defaultSuccessUrl() — целевая страница после успешного входа в систему
//        failureUrl() — целевая страница после неудачного входа в систему.
//                logoutUrl() — пользовательский выход
                .successHandler(new LoginSuccessHandler())
                .loginProcessingUrl("/login");//Путь к отправке формы на странице входа
        http
                .authorizeRequests()// Метод объекта, возвращаемый методом, чтобы настроить детали безопасности уровня запроса
                .antMatchers("/login").anonymous()//Это antMatchers()HTTP-метод Springboot, используемый для настройки URL-адресов, с которых система безопасности приложения Springboot должна разрешать запросы на основе ролей пользователя.
                .antMatchers("/").authenticated()//Этот antmatchers()метод представляет собой перегруженный метод, который получает как методы HTTP-запроса, так и определенные URL-адреса в качестве своих аргументов.
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
                .and().csrf().disable();//Закройте междоменные запросы. Неправильная настройка, может продолжать появляться403(Вне зависимости от того, есть ли у вас права доступа)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //in-memory authentication
        auth.userDetailsService(uds);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}