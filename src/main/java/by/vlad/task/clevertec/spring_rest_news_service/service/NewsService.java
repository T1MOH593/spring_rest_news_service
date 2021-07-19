package by.vlad.task.clevertec.spring_rest_news_service.service;

import by.vlad.task.clevertec.spring_rest_news_service.dao.NewsRepository;
import by.vlad.task.clevertec.spring_rest_news_service.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public Optional<News> getNewsById(Integer id) {
        return newsRepository.findById(id);
    }

    public Page<News> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public void deleteNewsById(Integer id) {
        newsRepository.deleteById(id);
    }

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public Page<News> getNewsByTitleContaining(String title, Pageable pageable) {
        return newsRepository.findByTitleContaining(title, pageable);
    }
}
