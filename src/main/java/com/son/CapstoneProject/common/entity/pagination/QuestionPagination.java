package com.son.CapstoneProject.common.entity.pagination;

import com.son.CapstoneProject.common.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class QuestionPagination extends Pagination {

    private List<Question> Qa = new ArrayList<>();

}
