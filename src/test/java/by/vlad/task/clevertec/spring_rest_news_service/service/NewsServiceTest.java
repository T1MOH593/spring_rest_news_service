package by.vlad.task.clevertec.spring_rest_news_service.service;

import by.vlad.task.clevertec.spring_rest_news_service.entity.News;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class NewsServiceTest {

    @Autowired
    private NewsService newsService;
    private News news = News.builder()
            .date(LocalDate.now())
            .title("test title")
            .text("test text")
            .build();

    @Test
    void getNewsById() {
        var savedNews = newsService.saveNews(news);
        var savednews = newsService.getNewsById(savedNews.getId());
        assertThat(savednews.isPresent()).isTrue();
        savednews.ifPresent(news -> assertThat(savedNews.getTitle()).isEqualTo("test title"));
    }

    @Test
    void getAllNews() {
        var allNews = newsService.getAllNews(PageRequest.ofSize(2)).getContent();
        assertThat(allNews.size()).isEqualTo(2);
    }

    @Test
    void deleteNewsById() {
        var idOfSavedNews = newsService.saveNews(news).getId();
        newsService.deleteNewsById(idOfSavedNews);
        assertThat(newsService.getNewsById(idOfSavedNews)).isEqualTo(Optional.empty());
    }

    @Test
    void saveNews() {
        var savedNews = newsService.saveNews(news);
        System.out.println(savedNews.getId());
        assertThat(savedNews.getId()).isNotNull();
    }

    @Test
    void getNewsByTitleContaining() {
        String title = "test title news12";
        var newsByTitleContaining = newsService.getNewsByTitleContaining(title, PageRequest.ofSize(1)).getContent();
        assertThat(newsByTitleContaining.size()).isEqualTo(1);
    }
}
