package by.vlad.task.clevertec.spring_rest_news_service.dao;

import by.vlad.task.clevertec.spring_rest_news_service.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByUsernameContaining(String username, Pageable pageable);
}
