package com.son.CapstoneProject.common.entity.pagination;

import com.son.CapstoneProject.common.entity.login.AppUser;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AppUserPagination extends Pagination {

    private List<AppUser> appUsersByPageIndex = new ArrayList<>();

}
