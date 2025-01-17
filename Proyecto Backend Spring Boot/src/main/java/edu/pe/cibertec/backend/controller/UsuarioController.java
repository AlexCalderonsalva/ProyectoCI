package edu.pe.cibertec.backend.controller;

import edu.pe.cibertec.backend.model.Usuario;
import edu.pe.cibertec.backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listUsuarios(){
        return service.listarUsuarios();
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario", description = "Crea un nuevo usuario en el sistema con los datos proporcionados.")
    public ResponseEntity<Map<String, Object>> addUsuaro(@RequestBody Usuario usuario){
        return service.registrarUsuario(usuario);
    }
}
