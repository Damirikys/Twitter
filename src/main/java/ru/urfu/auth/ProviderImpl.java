package ru.urfu.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.urfu.entities.User;
import ru.urfu.model.interfaces.UserDao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Named
public class ProviderImpl implements AuthenticationProvider, UserDetailsService
{
    @Autowired
    private UserDao userStorage;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        String passwordEncrypted = ru.urfu.auth.SecurityConfig.byteArrayToHexString(password.getBytes());

        LocalUserDetails userDetails = (LocalUserDetails) loadUserByUsername(username);

        if (userDetails.getPassword().equals(passwordEncrypted))
        {
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new UsernamePasswordAuthenticationToken(userDetails.user, password, roles);
        }
        else
        {
            throw new BadCredentialsException("Wrong password.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws BadCredentialsException
    {
        Optional<User> user = userStorage.getUser(s);

        if (!user.isPresent())
            throw new BadCredentialsException("User not found");

        return new LocalUserDetails(user.get());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}