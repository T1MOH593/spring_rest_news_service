package by.vlad.task.clevertec.spring_rest_news_service.service;

import by.vlad.task.clevertec.spring_rest_news_service.entity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    private Comment comment = Comment.builder()
            .username("test username")
            .text("test text")
            .date(LocalDate.now())
            .build();

    @Test
    void getComment() {
        var idOfSavedComment = commentService.saveComment(comment).getId();
        assertThat(idOfSavedComment).isNotNull();
        assertThat(commentService.getComment(idOfSavedComment).isPresent()).isTrue();
    }

    @Test
    void getAllComments() {
        var allComments = commentService.getAllComments(PageRequest.ofSize(2)).getContent();
        assertThat(allComments.size()).isEqualTo(2);
    }

    @Test
    void getCommentByUsernameContaining() {
        String username = "username2";
        var commentList = commentService.getCommentByUsernameContaining(username, PageRequest.ofSize(21)).getContent();
        assertThat(commentList.size()).isEqualTo(20);
    }

    @Test
    void saveComment() {
        var idOfSavedComment = commentService.saveComment(comment).getId();
        assertThat(idOfSavedComment).isNotNull();
    }

    @Test
    void deleteCommentById() {
        var idOfSavedComment = commentService.saveComment(comment).getId();
        commentService.deleteCommentById(idOfSavedComment);
        assertThat(commentService.getComment(idOfSavedComment)).isEqualTo(Optional.empty());
    }
}