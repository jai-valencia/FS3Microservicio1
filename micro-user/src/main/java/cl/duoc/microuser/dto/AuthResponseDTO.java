package cl.duoc.microuser.dto;

import cl.duoc.microuser.model.Rol;

public class AuthResponseDTO {

  private Long id;
  private String correo;
  private Rol rol;

  public AuthResponseDTO(Long id, String correo, Rol rol) {
    this.id = id;
    this.correo = correo;
    this.rol = rol;
  }

  public Long getId() {
    return id;
  }

  public String getCorreo() {
    return correo;
  }

  public Rol getRol() {
    return rol;
  }
}
