package io.murilo.vendas.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.murilo.vendas.VendasApplication;
import io.murilo.vendas.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;
    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expiracaoString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expiracaoString);
        Date data = Date.from(dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    protected Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
         try {

             Claims claims = obterClaims(token);
             Date dataExpiracao = claims.getExpiration();
             LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
             return !LocalDateTime.now().isAfter(data);

         }catch (Exception e) {
             return false;
         }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }

//    mockando objeto
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(VendasApplication.class);
//        JwtService service =  context.getBean(JwtService.class);
//        Usuario usuario = new Usuario();
//        usuario.setLogin("murilo");
//        usuario.setAdmin(true);
//        String token = service.gerarToken(usuario);
//        System.out.println(token);
//        boolean tokenValido = service.tokenValido(token);
//        System.out.println("token valido?" + tokenValido);
//        System.out.println(service.obterLoginUsuario(token));
//    }
}
