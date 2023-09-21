package com.works.services;

import com.works.entities.Customer;
import com.works.entities.Role;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService implements UserDetailsService {

    final CustomerRepository customerRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findByUsernameEquals(username);
        if (optionalCustomer.isPresent()) {
            Customer c = optionalCustomer.get();
            return new User(
                    c.getUsername(),
                    c.getPassword(),
                    c.getEnable(),
                    true,
                    true,
                    true,
                    parseRoles(c.getRoles())
            );
        }
        throw new UsernameNotFoundException("Username Not Found");
    }

    private Collection<? extends GrantedAuthority> parseRoles(List<Role> roles) {
        List<GrantedAuthority> list = new ArrayList<>();
        for ( Role item : roles ) {
            list.add( new SimpleGrantedAuthority(item.getName()));
        }
        return list;
    }


    public Customer register( Customer customer ) {
        customer.setPassword( passwordEncoder.encode(customer.getPassword()) );
        customerRepository.save(customer);
        return customer;
    }

}
