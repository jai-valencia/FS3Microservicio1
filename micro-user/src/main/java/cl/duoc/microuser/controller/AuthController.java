package cl.duoc.microuser.controller;

import cl.duoc.microuser.dto.AuthResponseDTO;
import cl.duoc.microuser.dto.LoginRequestDTO;
import cl.duoc.microuser.dto.RegisterRequestDTO;
import cl.duoc.microuser.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(
      @Valid @RequestBody LoginRequestDTO request
  ) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponseDTO> register(
      @Valid @RequestBody RegisterRequestDTO request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/recover")
  public ResponseEntity<Void> recover(@RequestParam String correo) {
    authService.recover(correo);
    return ResponseEntity.ok().build();
  }
}
