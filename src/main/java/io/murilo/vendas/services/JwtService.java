package io.murilo.vendas.services;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private String expiracao;
    private String chaveAssinatura;
}
