package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class thuc hien truy van thong tin bang article trong database
 * 
 * @author TUAN ANH
 * @version 1.00
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
	// Lấy danh sách tất cả article
	@Query("SELECT a FROM Article a ORDER BY a.article_id DESC")
	Page<Article> getListArticle(Pageable pageable);
	
	// Lấy tất cả article của một danh mục cụ thể
	@Query("SELECT a FROM Article a WHERE a.category.id = ?1 ORDER BY a.article_id DESC")
	Page<Article> getListArticleOfCategory(long category_id, Pageable pageable);
	
	// Lấy n article theo danh mục cụ thể
	@Query("SELECT TOP(?1) a FROM Article a WHERE a.category.id=?2 ORDER BY a.article_id DESC")
	List<Article> getListNArticleOfCategory(int n, long category_id);
}
