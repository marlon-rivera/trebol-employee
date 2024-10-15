package com.trebol.auth.configuration;

import com.trebol.auth.utils.Constants;
import com.trebol.auth.adapters.driven.jpa.mysql.entity.EmployeeEntity;
import com.trebol.auth.adapters.driven.jpa.mysql.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final IEmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        EmployeeEntity user = employeeRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.EMPLOYEE_NOT_FOUND_EXCEPTION));
        GrantedAuthority authority = new SimpleGrantedAuthority(Constants.ROLE_SECURITY_PREFIX + user.getRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPassword(),
                List.of(authority)
        );
    }
}
