package by.vlad.task.clevertec.spring_rest_news_service.controller;

import by.vlad.task.clevertec.spring_rest_news_service.dto.NewsDto;
import by.vlad.task.clevertec.spring_rest_news_service.entity.News;
import by.vlad.task.clevertec.spring_rest_news_service.exception_handling.NoSuchNewsException;
import by.vlad.task.clevertec.spring_rest_news_service.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class NewsRestController {

    private final NewsService newsService;
    private final ModelMapper modelMapper;

    public NewsRestController(NewsService newsService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.modelMapper = modelMapper;
    }

    /**
     * @param partOfTitle is the field in entity News
     * @param page is number of page which want to see
     * @param size is number of items on page
     * @param sort it is fields of entity which you want to sort (id, title etc) and direction (desc, asc)
     * @return current page, number of pages, number of items on page, newsDtos
     */
    /*
    Данный метод реализовывает постраничность и сортировку возвращаемых данных для потенциально больших запросов
    Этот url-адрес принимает параметры page, size, sort (поля по которым идёт сортировка + направление сортировки)
    Возвращает список NewsDto, нынешнюю страницу, количество элементов на странице, общее кол-во страниц
     */
    @GetMapping("/news")
    public ResponseEntity<Map<String, Object>> getAllNewsPage(
            @RequestParam(required = false) String partOfTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<>();


                // sort=[field, direction]
                orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));

            List<News> news;
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<News> pageNews;
            if (partOfTitle == null)
                pageNews = newsService.getAllNews(pagingSort);
            else
                pageNews = newsService.getNewsByTitleContaining(partOfTitle, pagingSort);

            news = pageNews.getContent();
            List<NewsDto> newsDtos = news.stream()
                    .map(news1 -> modelMapper.map(news1, NewsDto.class))
                    .collect(Collectors.toList());
            if (newsDtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("news", newsDtos);
            response.put("currentPage", pageNews.getNumber());
            response.put("totalItems", pageNews.getTotalElements());
            response.put("totalPages", pageNews.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param news entity News with id = 0
     * @return saved entity news in dto format
     */
    @PostMapping("/news")
    public NewsDto addNews(@RequestBody News news) {
        return modelMapper.map(newsService.saveNews(news), NewsDto.class);
    }

    /**
     * @param news news entity News with id != 0
     * @return updated News
     */
    @PutMapping("/news")
    public NewsDto updateNews(@RequestBody News news) {
        return modelMapper.map(newsService.saveNews(news), NewsDto.class);
    }

    /**
     * @param newsId is id of wanted News
     * @return news entity in dto format
     */
    @GetMapping("/news/{newsId}")
    public NewsDto showNews(@PathVariable Integer newsId) {
        var maybeNews = newsService.getNewsById(newsId);
        if (maybeNews.isPresent()) {
            return modelMapper.map(maybeNews.get(), NewsDto.class);
        } else {
            throw new NoSuchNewsException("There is no news with ID = " + newsId);
        }
    }

    /**
     * @param newsId id of deleting news
     * @return String string whether it was deleted
     */
    @DeleteMapping("/news/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable Integer newsId) {
        if (newsService.getNewsById(newsId).isEmpty()) {
            throw new NoSuchNewsException("There is no news with ID = " + newsId);
        } else {
            newsService.deleteNewsById(newsId);
            return new ResponseEntity<>("News with ID " + newsId + " was successfully deleted",
                    HttpStatus.NOT_FOUND) ;
        }
    }
}
