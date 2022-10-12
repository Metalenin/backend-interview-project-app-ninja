package com.ninjaone.backendinterviewproject;


//@EnableWebSecurity // Enable Spring Security’s web security support
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // To configure method-level security
public class SecurityConfig {

	/*
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api-docs/**",
            "/actuator/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/**").authenticated()
                .and().oauth2Login();
                //.and().oauth2ResourceServer();
                //
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
   */ 
}

