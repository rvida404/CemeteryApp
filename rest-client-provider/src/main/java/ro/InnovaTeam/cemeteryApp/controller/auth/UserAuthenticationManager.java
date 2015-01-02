package ro.InnovaTeam.cemeteryApp.controller.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.InnovaTeam.cemeteryApp.UserDTO;
import ro.InnovaTeam.cemeteryApp.restClient.AuthenticationRestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catalin Sorecau on 12/9/2014.
 */
public class UserAuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDTO userDTO = AuthenticationRestClient.checkCredentials(username, password);
        if (userDTO != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(userDTO.getRole()));
            return new UsernamePasswordAuthenticationToken(authentication.getName(), userDTO.getId(), authorities);
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    public static boolean hasAdminRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("admin"));
    }
}
