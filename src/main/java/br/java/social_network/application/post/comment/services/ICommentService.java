package br.java.social_network.application.post.comment.services;

public interface ICommentService<I, O> {
    O execute(I input);
}
