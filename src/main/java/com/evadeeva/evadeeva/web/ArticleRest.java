package com.evadeeva.evadeeva.web;

import com.evadeeva.evadeeva.services.ArticleService;
import com.evadeeva.evadeeva.web.dto.response.ArticleResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/article")
public class ArticleRest {
    private final ArticleService articleService;

    public ArticleRest(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public ResponseEntity<?> getArticles(@RequestParam(value = "pageNo", defaultValue = "0")int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "20")int pageSize,
                                         @RequestParam(value = "sortBy",defaultValue = "id")String sortBy){
        try{
            List<ArticleResponse> articleResponses = articleService.getArticles(pageNo, pageSize, sortBy);
            return ResponseEntity.ok(Objects.requireNonNullElse(articleResponses, "Bài viết đang được cập nhật!"));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getArticlesByCategory(@PathVariable("id")long categoryId,
                                                   @RequestParam(value = "pageNo", defaultValue = "0")int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "20")int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = "id")String sortBy){
        try{
            List<ArticleResponse> articleResponses = articleService.getArticleByCategory(pageNo, pageSize, sortBy, categoryId);
            return ResponseEntity.ok(Objects.requireNonNullElse(articleResponses, "Bài viết đang được cập nhật!"));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") long articleId){
        try{
            ArticleResponse articleResponse = articleService.getArticle(articleId);
            return ResponseEntity.ok(Objects.requireNonNullElse(articleResponse, "Bài viết đang được cập nhật!"));
        }catch (Exception e) {
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
}
