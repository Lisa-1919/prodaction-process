package productionprocess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import productionprocess.services.EmployeeService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmployeeService employeeService;

    @Value("${spring.security.debug:false}")
    private boolean securityDebug;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/employees/*", "/home").hasAuthority("ADMIN")
                        .requestMatchers("/products/*", "/materials/*", "/operations/*", "/home").hasAuthority("TECHNOLOGIST")
                        .requestMatchers("/orders-p/*", "/orders-w/*", "/home").hasAuthority("OPERATOR")
                        .requestMatchers("/assembly/*", "/home").hasAuthority("ASSEMBLY_SHOP_MASTER")
                        .requestMatchers("/paint/*", "/home").hasAuthority("PAINT_SHOP_MASTER")
                        .requestMatchers("/packing/*", "/home").hasAuthority("PACKING_SHOP_MASTER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll());

        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(employeeService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/css/**", "/js/**", "/img/**");
    }
}