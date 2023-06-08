package br.java.social_network.application.user.services;

import br.java.social_network.infrastructure.exception.HandleNotFoundException;

public interface IUserService<I, O> {
    O execute(I input) throws HandleNotFoundException;
}