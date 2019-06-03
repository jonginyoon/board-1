package com.example.board.api;

import com.example.board.model.Article;
import com.example.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public List<Article> getArticles(@RequestParam(value = "title", required = false) String title) {
        if (title != null) {
            return articleRepository.findByTitleContains(title, Sort.by(Sort.Order.desc("createdAt")));
        }
        return articleRepository.findAll(Sort.by(Sort.Order.desc("createdAt")));
    }

    @GetMapping("/api/articles/{id}")
    public Article getArticle(@PathVariable("id") Long id) {
        return articleRepository.findById(id).orElseGet(Article::new);
    }

    @PostMapping("/api/articles")
    public Article createArticle(Article article) {
        if (article.getId() != null) {
            article.setCreatedAt(articleRepository.findById(article.getId()).orElseThrow(IllegalArgumentException::new).getCreatedAt());
        }
        return articleRepository.save(article);
    }
}
