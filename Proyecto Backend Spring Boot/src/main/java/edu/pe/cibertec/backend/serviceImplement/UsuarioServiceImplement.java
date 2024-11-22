package edu.pe.cibertec.backend.serviceImplement;

import edu.pe.cibertec.backend.model.Usuario;
import edu.pe.cibertec.backend.repository.UsuarioRepository;
import edu.pe.cibertec.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServiceImplement implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<Map<String, Object>> listarUsuarios() {
        Map<String, Object> respuesta = new HashMap<>();
        List<Usuario> usuario = repository.findAll();

        if(!usuario.isEmpty()){
            respuesta.put("usuarios", usuario);
            respuesta.put("status", HttpStatus.OK);
            respuesta.put("fecha", new Date());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }else{
         respuesta.put("mensaje", "Sin registros");
         respuesta.put("status", HttpStatus.NOT_FOUND);
         respuesta.put("fecha", new Date());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> registrarUsuario(Usuario usuario) {
        Map<String, Object> respuesta = new HashMap<>();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
        respuesta.put("usuario", usuario);
        respuesta.put("mensaje", "Se añadio correctamente al alumno");
        respuesta.put("status", HttpStatus.CREATED);
        respuesta.put("fecha", new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }
}
