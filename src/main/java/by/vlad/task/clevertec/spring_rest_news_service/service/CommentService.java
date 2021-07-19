package by.vlad.task.clevertec.spring_rest_news_service.service;

import by.vlad.task.clevertec.spring_rest_news_service.dao.CommentRepository;
import by.vlad.task.clevertec.spring_rest_news_service.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> getComment(Integer id) {
        return commentRepository.findById(id);
    }

    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Page<Comment> getCommentByUsernameContaining(String username, Pageable pageable) {
        return commentRepository.findByUsernameContaining(username, pageable);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Integer id) {
        commentRepository.deleteById(id);
    }
}
