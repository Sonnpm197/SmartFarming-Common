package com.son.CapstoneProject.common.entity.pagination;

import com.son.CapstoneProject.common.entity.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ArticlePagination extends Pagination {

    private List<Article> articlesByPageIndex = new ArrayList<>();

}
