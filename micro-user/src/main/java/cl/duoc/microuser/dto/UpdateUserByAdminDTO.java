package cl.duoc.microuser.dto;

import cl.duoc.microuser.model.Rol;

public class UpdateUserByAdminDTO {

  private String correo;
  private Rol rol;

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public Rol getRol() {
    return rol;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }
}
