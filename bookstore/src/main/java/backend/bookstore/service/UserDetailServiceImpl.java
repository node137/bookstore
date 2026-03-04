package backend.bookstore.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.bookstore.model.AppUser;
import backend.bookstore.model.AppUserInterface;

//@Service // This is not needed if we have a bean of this class in WebSecurityConfig
public class UserDetailServiceImpl implements UserDetailsService {
    private final AppUserInterface repository;

    public UserDetailServiceImpl(AppUserInterface appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currUser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPasswordHash(),
                AuthorityUtils.createAuthorityList(currUser.getRole()));
        return user;
    }

}
