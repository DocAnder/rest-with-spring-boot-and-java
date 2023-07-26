package com.example.restwithspringbootandjava.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.restwithspringbootandjava.data.vo.v1.security.TokenVO;
import com.example.restwithspringbootandjava.exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/*
  A maioria das importações nessa classe são do Spring Security, que contem as classes necessárias para
  o retorno dos metodos que definimos, como por exemplo, a classe Authentication


* Os atributos secretKey e validityInMilliseconds foram setados como padrão na clase e serão utilizados quando nada
* for posto no yml da api. Ou seja, caso o yml não tenha esses dados, o padrão será o valor dentro da classe.
*
* O userDetailsService é uma classe do Spring Security que possui um metodo p/ carregar os dados do usuário pelo nome.
* O algorithm do JWT será o metodo pelo qual a senha será encriptada.
*
* */

@Service()
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;


    Algorithm algorithm = null;

/*
* Anotação PostConstruc : Quando o spring inicia a aplicação (o spring context) ele cria instancias de  todos os beans anotados
* ou declarados na configuração, processas as anotations, injeta as dependencias, além de mais algumas coisas.
* Apos fazer tudo isso corretamente, ele chama os metodos que tenham a anotação postconstruct
* */
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    //Metodo responsável por criar o token.
    public TokenVO createAccessToken(String username, List<String> roles){
        Date now = new Date();
        //Validity soma 1 hora ao horario atual.
        Date vality = new Date(now.getTime() + validityInMilliseconds);
        var acessToken = getAccessToken(username, roles, now, vality);
        var refreshToken = getRefreshToken(username, roles, now);
        return new TokenVO(username, true, now, vality, acessToken, refreshToken);
    }

    public TokenVO refreshToken (String refreshToken){
        if(refreshToken.contains("Bearer ")) refreshToken = refreshToken.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return createAccessToken(username, roles);
    }



    private String getAccessToken(String username, List<String> roles, Date now, Date vality) {
        // A issuerUrl irá conter a URL do servidor.
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles) //Quais papei o usuátio tem dentro da api
                .withIssuedAt(now) //Quando o token foi criado
                .withExpiresAt(vality) //Quando o token vai expirar
                .withSubject(username) //O nome do usuario
                .withIssuer(issuerUrl) // a url
                .sign(algorithm)
                .strip();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        //Aqui atualizamos a validade do token, para 3 horas
        Date valityRefreshToken = new Date(now.getTime() + (validityInMilliseconds * 3));
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(now)
                .withExpiresAt(valityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .strip();
    }


    public Authentication getAuthentication(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /*Para decodificar o token, precisamos antes informar o algoritimo e a chave secreta usadas.
    Apos, criamos um verificador do JWT que recebe o tipo de algotimo utilizado p/ fazer a checagem.
    Depois de configurado, o verificador é capaz de dizer se o token é ou nao valido.
    * */
    private DecodedJWT decodedToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }


    public String resolveToken(HttpServletRequest req){
        //Toda request http tem no cabeçalho o authorization
        String bearerToken = req.getHeader("Authorization");

        //O autorization tem a seguinte formatação: Bearer 1AfJRkASrWDQM1FADadadr.gadfaFARahbJAxaqGAgg (Bearer + o token)
        //Por isso, retiramos o Bearer antes de devolver o token para verificação
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }

    public boolean validateToken(String token){
        DecodedJWT decodedJWT = decodedToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())){
                return false;
            }
            return true;
        }catch (Exception e){
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT Token");
        }

    }





}
