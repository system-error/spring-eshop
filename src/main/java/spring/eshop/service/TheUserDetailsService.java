package spring.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spring.eshop.model.TheUserDetails;
import spring.eshop.model.User;
import spring.eshop.repository.UserRepository;

public class TheUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);

        return (user == null) ? (UserDetails) new UsernameNotFoundException("Not Found "+ s) : new TheUserDetails(user);
    }
}
