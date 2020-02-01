package com.son.CapstoneProject.common.entity.search;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleSearch implements Serializable {

    private String category;
    private String textSearch;

}
