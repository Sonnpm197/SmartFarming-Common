package com.son.CapstoneProject.common.entity.search;

import com.son.CapstoneProject.common.entity.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleSearchResult {

    private List<Article> articles;
    private int numberOfPages;

}
