package br.java.social_network.application.models.post.comment;

public interface ICommentService<I, O> {
    O execute(I input);
}
