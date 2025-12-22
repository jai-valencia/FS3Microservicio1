package cl.duoc.microuser.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends jakarta.servlet.GenericFilter {

  private final JwtService jwtService;

  public JwtFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain
  ) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) request;
    String header = req.getHeader("Authorization");

    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        Claims claims = jwtService.parseClaims(token);
        String correo = claims.getSubject();
        String rol = String.valueOf(claims.get("rol"));

        UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
                correo,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + rol))
            );

        SecurityContextHolder.getContext().setAuthentication(auth);

      } catch (Exception ex) {
        SecurityContextHolder.clearContext();
      }
    }

    chain.doFilter(request, response);
  }
}
