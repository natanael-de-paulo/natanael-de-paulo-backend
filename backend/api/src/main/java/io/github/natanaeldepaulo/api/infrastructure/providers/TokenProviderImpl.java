package io.github.natanaeldepaulo.api.infrastructure.providers;

import io.github.natanaeldepaulo.api.application.models.auth.ITokenProvider;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenProviderImpl implements ITokenProvider {
    @Override
    public String generateToken(UserDTO user){
        return Jwts
                .builder()
                .setSubject(ConvertFormatId.toString(user.getId()))
                .claim("profileId", user.getProfile().getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7200000))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key genSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode("4D6250655368566D597133743677397A24432646294A404E635266546A576E5A"));
    }
}
