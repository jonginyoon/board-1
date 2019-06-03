package com.example.board.repository;

import com.example.board.model.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitleContains(String title, Sort sort);
}
