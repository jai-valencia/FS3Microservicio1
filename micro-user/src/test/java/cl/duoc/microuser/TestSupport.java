package cl.duoc.microuser;


import cl.duoc.microuser.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestSupport {

  public static String registerAndGetToken(MockMvc mvc, ObjectMapper om, String nombre, String correo, String password, String rol) throws Exception {
    RegisterRequestDTO reg = new RegisterRequestDTO();
    reg.setNombre(nombre);
    reg.setCorreo(correo);
    reg.setPassword(password);
    reg.setRol(rol);

    mvc.perform(post("/api/auth/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(reg)))
        .andExpect(status().isOk());

    LoginRequestDTO login = new LoginRequestDTO();
    login.setCorreo(correo);
    login.setPassword(password);

    String body = mvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(login)))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return om.readTree(body).get("token").asText();
  }
}

