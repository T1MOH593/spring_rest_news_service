package by.vlad.task.clevertec.spring_rest_news_service.dao;

import by.vlad.task.clevertec.spring_rest_news_service.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    Page<News> findByTitleContaining(String partOfTitle, Pageable pageable);
}
