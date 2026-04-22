package tacos.security;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import tacos.model.User;
import tacos.data.UserRepository;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    //    @Bean
    //    public WebSecurityCustomizer webSecurityCustomizer() {
    //        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    //    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
       return (username) -> {
           if (userRepo.findByUsername("belial") == null) {
               userRepo.save(new RegistrationForm("belial", "password", "", ""
                       , "", "", "", "").toUser(passwordEncoder()));
           }
           User user = userRepo.findByUsername(username);
           if (user != null){
               return user;
           }
           throw new UsernameNotFoundException("User" + username + "not found");
       };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth.requestMatchers(toH2Console()).permitAll())
                .headers(headers ->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(csrf->csrf.ignoringRequestMatchers(toH2Console()).disable());
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/","/login","/register").permitAll()
//                .requestMatchers("/design","/orders","/orders/**","/api/tacos/**").hasRole("USER"));
                .requestMatchers("/**").hasRole("USER"));
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/"));
        return http.build();
   }

}
