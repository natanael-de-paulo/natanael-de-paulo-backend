package br.java.social_network.domain.user;

import br.java.social_network.domain.user.embedded.Profile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Getter
public class User implements UserDetails {
    @Id private UUID id;
    @NotNull
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    private Profile profile;

    private User(){
        this.id = UUID.randomUUID();
    }

    public static User builder(){
        return new User();
    }

    public User email(String email){
        this.email = email;
        return this;
    }

    public User password(String password){
        this.password = password;
        return this;
    }

    public User profile(Profile profile){
        this.profile = profile;
        return this;
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
