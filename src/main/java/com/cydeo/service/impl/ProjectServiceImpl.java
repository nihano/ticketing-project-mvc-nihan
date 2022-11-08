package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.CrudService;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO, String> implements ProjectService {

    @Override
    public ProjectDTO save(ProjectDTO project) {

        if (project.getProjectStatus()==null){ //we use save method in DataGenrator. If we do not add condition all the projects will be created as project status OPEN.
            project.setProjectStatus(Status.OPEN);
        }
        return super.save(project.getProjectCode(), project);
    }
    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }
    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }
    @Override
    public void update(ProjectDTO object) {

        if (object.getProjectStatus()==null){ //in the form there is no project status
            object.setProjectStatus(findById(object.getProjectCode()).getProjectStatus());//status is already in the database
        }
        super.update(object.getProjectCode(), object);
    }
    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {

        project.setProjectStatus(Status.COMPLETE);
    }
}
