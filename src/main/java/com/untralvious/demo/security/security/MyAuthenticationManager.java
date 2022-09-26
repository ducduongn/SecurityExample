package com.untralvious.demo.security.security;

import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager extends ProviderManager implements AuthenticationManager {

    public MyAuthenticationManager(List<AuthenticationProvider> providers) {
        super(providers);
        providers.forEach(e -> System.out.println("Registered providers " + e.getClass().getName()));
    }
}
