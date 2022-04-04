package cs.hse.telesfor.security.config;

import cs.hse.telesfor.security.PasswordEncoder;
import cs.hse.telesfor.user.UserRole;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/registration/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .antMatchers("/api/medicaments/add").hasRole("ADMIN")
                .antMatchers("/api/medicaments").hasAnyRole("ADMIN", "DOCTOR", "USER")
                .antMatchers("/api/medicaments/patient/**").hasAnyRole("ADMIN", "DOCTOR", "USER")
                .antMatchers("/api/users/all").hasAnyRole("ADMIN")
                .antMatchers("/api/users/patients").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers("/api/users/**").hasAnyRole("ADMIN", "DOCTOR", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(userService);

        return provider;
    }
}
