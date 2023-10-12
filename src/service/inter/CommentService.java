package service.inter;

import model.Comment;

import java.util.List;

public interface CommentService {
    void add(Comment comment);
    List<String> getByBookId(int id);
}
