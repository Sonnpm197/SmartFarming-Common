package com.son.CapstoneProject.common.entity.pagination;

import com.son.CapstoneProject.common.entity.Report;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReportPagination extends Pagination {

    private List<Report> reportsByPageIndex;

}
