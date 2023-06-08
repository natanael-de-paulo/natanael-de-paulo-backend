package br.java.social_network.application.post.services;

public interface IPostService<I1, O> {
    O execute(I1 input);
}
