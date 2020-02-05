package springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
@Transactional
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // Các User trong Database
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeRequests().
        antMatchers("/", "/home","/login", "/register").permitAll();

    http.authorizeRequests().antMatchers("/manager", "/product/list", "/product/additional" +
                                        "/product/additional-check" )
                                          .access("hasRole('ROLE_ADMIN')");
    http.authorizeRequests().antMatchers("/cart/accept" )
        .access("hasRole('ROLE_USER')");


    http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/404");

    // Cấu hình cho Login Form.
    http.authorizeRequests().and().formLogin()//

        // Submit URL của trang login
        .loginProcessingUrl("/login-check") // Submit URL
        .loginPage("/login")//
        .defaultSuccessUrl("/home")//
        .failureUrl("/login")//
        .usernameParameter("username")//
        .passwordParameter("password")

        // Cấu hình cho Logout Page.
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");
  }
}
