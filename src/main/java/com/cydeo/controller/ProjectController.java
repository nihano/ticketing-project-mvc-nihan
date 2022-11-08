package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("managers", userService.findManagers());
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

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){

        //complete -> status to complete - > do i have service?
        projectService.complete(projectService.findById(projectCode));
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
        //user obj  view is requesting ${user}
        model.addAttribute("project", projectService.findById(projectCode));//we are sending specific proj not an empty proj

        //roles ${roles}
        model.addAttribute("managers", userService.findManagers());

        //users ${users}
        model.addAttribute("projects", projectService.findAll());

        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute("project") ProjectDTO project){
        //update the user
        projectService.update(project);
        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String getProjectByManager(Model model){

        UserDTO manager=userService.findById("john@cydeo.com");
        List<ProjectDTO> projects = projectService.getCountedListOfProjectDTO(manager);
        model.addAttribute("projects", projects);
        return "/manager/project-status";
    }
}
