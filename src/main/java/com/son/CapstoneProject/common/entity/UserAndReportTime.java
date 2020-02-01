package com.son.CapstoneProject.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAndReportTime {

    private String rowIndex;
    private String userId;
    private String role;
    private String fullName;
    private String numberOfReports;

}
