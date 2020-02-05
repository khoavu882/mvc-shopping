package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springmvc.dao.UserDAO;
import springmvc.entities.Role;
import springmvc.entities.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserDAO userDAO;

  @Bean
  public BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    BCryptPasswordEncoder encoder = passwordEncoder();
    User user = userDAO.findByUserName(username);

    if (user == null)
      throw new UsernameNotFoundException("User not found");

    Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    Set<Role> roles = user.getRoles();
    System.out.println(roles);
    for (Role role : roles) {
      System.out.println(role.getRoleName());
      grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUserName(),
        encoder.encode(user.getPassword()), true, true, true, true, grantedAuthorities);
  }
}
