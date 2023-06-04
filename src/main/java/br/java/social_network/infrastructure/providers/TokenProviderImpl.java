package br.java.social_network.infrastructure.providers;

import br.java.social_network.application.models.infra_interfaces.ITokenProvider;
import br.java.social_network.application.models.user.UserResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenProviderImpl implements ITokenProvider {
    private Key genSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("4D6250655368566D597133743677397A24432646294A404E635266546A576E5A"));
    }

    @Override
    public String generateToken(UserResponseDTO user){
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .claim("userId", ConvertFormatId.toString(user.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7200000))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public Claims getClaimsToToken(String token){
        var claims = Jwts.parserBuilder()
                .setSigningKey(genSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
