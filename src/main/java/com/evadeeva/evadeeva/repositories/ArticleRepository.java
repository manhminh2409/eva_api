package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Article;
import com.evadeeva.evadeeva.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	Article findByIdAndStatus(long articleId, int status);
	Page<Article> findAllByStatus(Pageable pageable, int status);
	Page<Article> findAllByCategoryAndStatus(Pageable pageable, Category category, int status);
	Article findByTitle(String title);
}
