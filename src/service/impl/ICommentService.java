package service.impl;

import model.Comment;
import repository.impl.ICommentRepository;
import repository.inter.CommentRepository;
import service.inter.CommentService;

import java.util.List;

public class ICommentService implements CommentService {
    CommentRepository commentRepository=new ICommentRepository();

    @Override
    public void add(Comment comment) {
        commentRepository.add(comment);
    }

    @Override
    public List<String> getByBookId(int id) {
        return commentRepository.getByBookId(id);
    }
}
