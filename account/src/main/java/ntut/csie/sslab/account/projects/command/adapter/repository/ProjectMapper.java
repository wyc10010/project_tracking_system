package ntut.csie.sslab.account.projects.command.adapter.repository;

import ntut.csie.sslab.account.projects.command.entity.Project;
import ntut.csie.sslab.account.projects.command.entity.Repository;
import ntut.csie.sslab.account.projects.command.entity.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectMapper {
    public static List<Project> transformToDomain(List<ProjectData> projectDatas){
        List<Project> projects = new ArrayList<>();
        for(ProjectData data: projectDatas){
            projects.add(transformToDomain_(data));
        };
        return projects;
    }

    public static Project transformToDomain_(ProjectData data){
        Project project = new Project(data.getId(), data.getProjectName(), null);
        Map<String, Role> projectMember = new HashMap<>();
        data.getProjectMembers().forEach((k, v) -> projectMember.put(k, Role.valueOf(v)));
        project.setProjectMembers(projectMember);
        project.setRepositories(ProjectMapper.transformToDomain_(data.getRepositoryDataSet()));
        project.clearDomainEvents();
        return project;
    }

    private static List<Repository> transformToDomain_(List<RepositoryData> repositoryDatas){
        List<Repository> repositories = new ArrayList<>();
        for(RepositoryData repositoryData: repositoryDatas){
            Repository repository = new Repository(repositoryData.getOwnerName(), repositoryData.getRepositoryName());
            repositories.add(repository);
        }
        return repositories;
    }

    public static ProjectData transformToData(Project project){
        ProjectData projectData = new ProjectData();
        projectData.setId(project.getId());
        projectData.setProjectName(project.getName());
        Map<String, String> projectMember = new HashMap<>();
        project.getProjectMembers().forEach((k, v) -> projectMember.put(k, v.toString()));
        projectData.setProjectMembers(projectMember);
        projectData.setRepositoryDataSet(transformToData(project.getRepositories()));
        return projectData;
    }


    private static List<RepositoryData> transformToData(List<Repository> repositories){
        List<RepositoryData> repositoryDatas = new ArrayList<>();
        for(Repository repository: repositories){
            RepositoryData repositoryData = new RepositoryData(repository.getOwnerName(), repository.getRepositoryName());
            repositoryDatas.add(repositoryData);
        }
        return repositoryDatas;
    }
}
