package com.example.springsecurityapplication.config;



import com.example.springsecurityapplication.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{


    private final PersonDetailsService personDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncode(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.csrf().disable()
        http
                .authorizeHttpRequests()
                .requestMatchers("/authentication", "/error", "/registration", "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/product", "/product/info/{id}", "/product/search", "/logout").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/authentication")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/person_account", true)
                .failureUrl("/authentication?error")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/authentication");

        return http.build();

    }

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

//    private final AuthenticationProvider authenticationProvider;


//    public SecurityConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }

    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncode());

    }
}
