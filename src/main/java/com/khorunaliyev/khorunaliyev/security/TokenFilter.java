package com.khorunaliyev.khorunaliyev.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Configuration
public class TokenFilter  extends OncePerRequestFilter {

    private final TokenGenerator tokenGenerator;
    private final CustomeUserDetailsService customeUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String token = tokenGenerator.getTokenFromRequest(request);
        if(token!=null && !token.isEmpty() && tokenGenerator.validateToken(token)){
            final String username = tokenGenerator.getUsernameByToken(token);
            final UserDetails userDetails = customeUserDetailsService.loadUserByUsername(username);
            final UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationFilter);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/api/test/**", request.getServletPath())
                || new AntPathMatcher().match("/api/register-info/**", request.getServletPath())
                ;
    }
}
