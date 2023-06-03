package br.java.social_network.application.models.post;

public interface IPostService<I1, O> {
    O execute(I1 input);
}
