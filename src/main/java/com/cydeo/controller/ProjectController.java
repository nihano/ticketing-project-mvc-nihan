package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    @GetMapping("/create")
    public String createProject(Model model) {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findAll());
        model.addAttribute("projects", projectService.findAll());

        return "/project/create";
    }

    @PostMapping("/create")
    public String insertProject(@ModelAttribute("project") ProjectDTO project) {

        /*
        //when creating new project we set the Status to OPEN,
        otherwise we will get nullPointerException.
        Because in the html it is looking for a value.

        BUT THIS IS NOT A GOOD SOLUTION BECAUSE WE ARE ADDING
        BUSINESS LOGIC INTO CONTROLLER. WE MOVED THIS TO
        ProjectServiceImpl

        project.setProjectStatus(Status.OPEN);

         */

        projectService.save(project);
        return "redirect:/project/create";//this what we see after save we will go project create again so we can use redirect

    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode) {

        projectService.deleteById(projectCode);

        return "redirect:/project/create";

    }
}
