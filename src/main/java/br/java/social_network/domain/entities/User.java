package br.java.social_network.domain.entities;

import br.java.social_network.domain.embedded.Profile;
import br.java.social_network.application.models.user.UserRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Document(collection = "users")
@Data
public class User implements UserDetails {
    @Id private UUID id;
    private String email;
    private String password;
    private Profile profile;

    private User(){};

    private User(String email, String password, Profile profile){
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    //using factory method
    public static User create(UserRequest userDTO){
        return new User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getProfile()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
