package br.java.social_network.application.models.user;

public interface IUserService<I, O> {
    O execute(I input);
}