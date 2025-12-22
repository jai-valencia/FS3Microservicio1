package cl.duoc.microuser.service;

import cl.duoc.microuser.dto.*;
import cl.duoc.microuser.model.Rol;
import cl.duoc.microuser.model.Usuario;
import cl.duoc.microuser.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final UsuarioRepository usuarioRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(UsuarioRepository usuarioRepository,
                     PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public AuthResponseDTO register(RegisterRequestDTO req) {
    Usuario u = new Usuario();
    u.setNombre(req.getNombre());
    u.setCorreo(req.getCorreo());
    u.setPassword(passwordEncoder.encode(req.getPassword()));
    u.setRol(Rol.valueOf(req.getRol()));

    usuarioRepository.save(u);

    return new AuthResponseDTO(u.getId(), u.getCorreo(), u.getRol());
  }

  public AuthResponseDTO login(LoginRequestDTO req) {
    Usuario u = usuarioRepository.findByCorreo(req.getCorreo())
        .orElseThrow();

    return new AuthResponseDTO(u.getId(), u.getCorreo(), u.getRol());
  }

  public void recover(String correo) {
    usuarioRepository.findByCorreo(correo)
        .orElseThrow();
    // recuperación simulada (requisito del enunciado)
  }
}
