package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String projectName;
    private String projectCode;
    /*
     //this will be saved as String in the html,
      we are trying to parse it to UserDTO. We will use converter.
     */
    private UserDTO assignedManager;

    /*LocalDate will be saved as String in the html,
      we are trying to parse it to LocalDate.
      We can use DateTimeFormatter for LocalDate.
      Pattern needs to match exact format with html,
      (It shows format in the error page)
     */
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;
    private int completeTaskCounts;//added later
    private int unfinishedTaskCounts;//added later

    //constructor without last two fields to not break the code in data generator
    public ProjectDTO(String projectName, String projectCode, UserDTO assignedManager, LocalDate startDate, LocalDate endDate, String projectDetail, Status projectStatus) {
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.assignedManager = assignedManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDetail = projectDetail;
        this.projectStatus = projectStatus;
    }
}
