package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Article;
import com.evadeeva.evadeeva.models.ArticleImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
	List<ArticleImage> findAllByArticle(Article article);
}

