package io.murilo.vendas.controller;

import io.murilo.vendas.Dto.CredenciaisDTO;
import io.murilo.vendas.Dto.TokenDTO;
import io.murilo.vendas.domain.entity.Usuario;
import io.murilo.vendas.exceptions.SenhaInvalidaException;
import io.murilo.vendas.services.Impl.UsuarioServiceImpl;
import io.murilo.vendas.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    public UsuarioController(UsuarioServiceImpl usuarioService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    private UsuarioServiceImpl usuarioService;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        String senhaCrifada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCrifada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
        try {
            Usuario usuario = new Usuario();
            usuario.setLogin(credenciaisDTO.getLogin());
            usuario.setSenha(credenciaisDTO.getSenha());

            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
