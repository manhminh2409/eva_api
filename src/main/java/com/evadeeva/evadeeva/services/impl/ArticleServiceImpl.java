package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.ArticleMapper;
import com.evadeeva.evadeeva.models.Article;
import com.evadeeva.evadeeva.models.ArticleImage;
import com.evadeeva.evadeeva.models.Category;
import com.evadeeva.evadeeva.repositories.ArticleImageRepository;
import com.evadeeva.evadeeva.repositories.ArticleRepository;
import com.evadeeva.evadeeva.repositories.CategoryRepository;
import com.evadeeva.evadeeva.services.ArticleService;
import com.evadeeva.evadeeva.web.dto.request.ArticleImageRequest;
import com.evadeeva.evadeeva.web.dto.request.ArticleRequest;
import com.evadeeva.evadeeva.web.dto.response.ArticleResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.evadeeva.evadeeva.config.Constants.ACTIVE_STATUS;
import static com.evadeeva.evadeeva.config.Constants.TYPE_ARTICLE;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleImageRepository articleImageRepository;
    private final CategoryRepository categoryRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ArticleImageRepository articleImageRepository, CategoryRepository categoryRepository,
                              ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleImageRepository = articleImageRepository;
        this.categoryRepository = categoryRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public ArticleResponse getArticle(long articleId) {
        Article article = articleRepository.findByIdAndStatus(articleId, ACTIVE_STATUS);
        if (article != null) {
            return articleMapper.mapToResponse(article);
        }
        return null;
    }

    @Override
    public List<ArticleResponse> getArticles(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Article> articles = articleRepository.findAllByStatus(pageable, ACTIVE_STATUS);

        return articles.getContent().stream()
                .map(articleMapper::mapToResponse)
                .toList();
    }

    @Override
    public List<ArticleResponse> getArticleByCategory(int pageNo, int pageSize, String sortBy, long categoryId) {
        Category category = categoryRepository.findByStatusAndIdAndType(ACTIVE_STATUS, categoryId, TYPE_ARTICLE);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<Article> articles = articleRepository.findAllByCategoryAndStatus(pageable, category, ACTIVE_STATUS);

        if(!articles.isEmpty()){
            return articles.getContent().stream()
                    .map(articleMapper::mapToResponse)
                    .toList();
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public ArticleResponse createArticle(ArticleRequest articleRequest) {
        Article article = articleRepository.findByTitle(articleRequest.getTitle());
        if (article == null){
            Article articleNew = articleMapper.mapRequestToModel(articleRequest);
            Date date = new Date();
            articleNew.setCreatedDate(date);
            articleNew.setModifiedDate(date);
            articleNew.setStatus(1);
            Article articleResp = articleRepository.save(articleNew);

            for (ArticleImageRequest a: articleRequest.getImages()) {
                ArticleImage image = new ArticleImage();
                image.setArticle(articleResp);
                image.setUrl(a.getUrl());
                articleImageRepository.save(image);
            }
            return articleMapper.mapToResponse(articleResp);
        }else {
            return null;
        }

    }
}
