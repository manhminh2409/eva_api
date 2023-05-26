package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.ArticleImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class thuc hien truy van thong tin bang article_image trong database
 * 
 * @author TUAN ANH
 * @version 1.00
 */
@Repository
public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
	@Query("SELECT ai FROM ArticleImage ai ORDER BY ai.article_image_id DESC")
	Page<ArticleImage> getListArticleImage(Pageable pageable);
	
	@Query("SELECT ai FROM ArticleImage ai WHERE ai.article.article_id = ?1")
	List<ArticleImage> getListArticleImageOfArticle(Long article_id);
}

