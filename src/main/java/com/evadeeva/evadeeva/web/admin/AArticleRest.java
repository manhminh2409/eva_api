package com.evadeeva.evadeeva.web.admin;

import com.evadeeva.evadeeva.services.ArticleService;
import com.evadeeva.evadeeva.web.dto.request.ArticleRequest;
import com.evadeeva.evadeeva.web.dto.response.ArticleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/article")
public class AArticleRest {
    private final ArticleService articleService;

    public AArticleRest(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody ArticleRequest articleRequest){
        try {
            ArticleResponse articleResponse = articleService.createArticle(articleRequest);
            return ResponseEntity.ok(articleResponse);
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
}
