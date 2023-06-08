package br.java.social_network.application.comment.services;

public interface ICommentService<I, O> {
    O execute(I input);
}
