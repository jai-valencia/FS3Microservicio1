package cl.duoc.microuser.repository;

import cl.duoc.microuser.model.Usuario;
import cl.duoc.microuser.model.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Test
void saveAndFindByCorreo() {
    Usuario u = new Usuario();
    u.setNombre("Juan Perez");   // 👈 FALTABA
    u.setCorreo("juan@test.cl");
    u.setPassword("1234");
    u.setRol(Rol.CLIENTE);;

    usuarioRepository.save(u);

    Optional<Usuario> found = usuarioRepository.findByCorreo("juan@test.cl");
    assertThat(found).isPresent();
}
}
