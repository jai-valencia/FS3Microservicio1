package cl.duoc.microuser.controller;

import cl.duoc.microuser.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<?> listarUsuarios() {
    return ResponseEntity.ok(usuarioService.findAll());
  }
}
