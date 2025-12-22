package cl.duoc.microuser.service;

import cl.duoc.microuser.model.Usuario;
import cl.duoc.microuser.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<Usuario> findAll() {
    return usuarioRepository.findAll();
  }
}
