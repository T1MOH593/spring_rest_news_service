package by.vlad.task.clevertec.spring_rest_news_service.controller;

import by.vlad.task.clevertec.spring_rest_news_service.dto.CommentDto;
import by.vlad.task.clevertec.spring_rest_news_service.entity.Comment;
import by.vlad.task.clevertec.spring_rest_news_service.exception_handling.NoSuchCommentException;
import by.vlad.task.clevertec.spring_rest_news_service.exception_handling.NoSuchNewsException;
import by.vlad.task.clevertec.spring_rest_news_service.service.CommentService;
import by.vlad.task.clevertec.spring_rest_news_service.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/news/{newsId}")
public class CommentRestController {

    private final NewsService newsService;
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(NewsService newsService, CommentService commentService, ModelMapper modelMapper) {
        this.newsService = newsService;
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    /**
     * @param newsId is id of news of which we want to see comments
     * @param username is field of comment (for sorting)
     * @param page is wanted page
     * @param size is size of wanted page
     * @param sort is {field of Comment},{direction (desc or asc)}
     * @return current page, number of pages, size of pages, collection of comments
     */
    @GetMapping("/comments")
    public ResponseEntity<Map<String, Object>> getAllCommentsPage(
            @PathVariable Integer newsId,
            @RequestParam(required = false) String username,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            List<Order> orders = new ArrayList<>();


            // sort=[field, direction]
            orders.add(new Order(Sort.Direction.fromString(sort[1]), sort[0]));

            List<Comment> comments;
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Comment> pageComments;
            if (username == null)
                pageComments = commentService.getAllComments(pagingSort);
            else
                pageComments = commentService.getCommentByUsernameContaining(username, pagingSort);

            comments = pageComments.getContent();
            List<CommentDto> commentDtos = comments.stream()
                    .map(comment -> modelMapper.map(comment, CommentDto.class))
                    .collect(Collectors.toList());

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("comments", commentDtos);
            response.put("currentPage", pageComments.getNumber());
            response.put("totalItems", pageComments.getTotalElements());
            response.put("totalPages", pageComments.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @param newsId is id of wanted news
     * @param comment is Comment which we want to add
     * @return saved Comment in dto format
     */
    @PostMapping("/comments")
    public CommentDto addComment(@PathVariable (value = "newsId") Integer newsId,
                                 @RequestBody Comment comment) {
        comment.setDate(LocalDate.now());
        return newsService.getNewsById(newsId)
                .map(news -> {
                    comment.setNews(news);
                    return modelMapper.map(commentService.saveComment(comment), CommentDto.class);
                }).orElseThrow(() -> new NoSuchNewsException("NewsId " + newsId + " not found"));
    }

    /**
     * @param newsId is id of wanted news
     * @param commentId is id of wanted comment
     * @return wanted comment in dto format
     */
    @GetMapping("/comments/{commentId}")
    public CommentDto showComment(@PathVariable(name = "newsId") Integer newsId,
                               @PathVariable(name = "commentId") Integer commentId) {
        if (commentService.getComment(commentId).isEmpty()) {
            throw new NoSuchCommentException("There is no comments with ID = " + commentId);
        } else {
            return modelMapper.map(commentService.getComment(commentId).get(), CommentDto.class);
        }
    }

    /**
     * @param newsId is id of wanted news
     * @param commentId is id of comment which we want to delete
     * @return the String result of deleting
     */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "newsId") Integer newsId,
                                @PathVariable(name = "commentId") Integer commentId) {
        if (commentService.getComment(commentId).isEmpty()) {
            throw new NoSuchCommentException("There is no comments with ID = " + commentId);
        } else {
            commentService.deleteCommentById(commentId);
            return new ResponseEntity<>("Comment with ID " + commentId + " was successfully deleted",
                    HttpStatus.NOT_FOUND) ;
        }
    }

    /**
     * @param newsId id of wanted news
     * @param comment is comment with id of updating comment
     * @return updated comment in dto format
     */
    @PutMapping("/comments")
    public CommentDto updateComment(@PathVariable(name = "newsId") Integer newsId,
                                    Comment comment) {
        comment.setDate(LocalDate.now());
        return modelMapper.map(commentService.saveComment(comment), CommentDto.class);
    }
}
