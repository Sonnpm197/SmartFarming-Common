package com.son.CapstoneProject.common.entity.pagination;

import com.son.CapstoneProject.common.entity.Notification;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NotificationPagination extends Pagination {

    private List<Notification> notificationsByPageIndex = new ArrayList<>();
    private int currentPage;

}
