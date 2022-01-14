package io.murilo.vendas.controller;

import io.murilo.vendas.domain.entity.Usuario;
import io.murilo.vendas.services.Impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        String senhaCrifada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCrifada);
        return usuarioService.salvar(usuario);
    }
}
