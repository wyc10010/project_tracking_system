package ntut.csie.sslab.account.users.command.adapter.repository;


import ntut.csie.sslab.account.users.command.entity.CommittedProject;
import ntut.csie.sslab.account.users.command.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

	public static List<User> transformToDomain(List<UserData> datas) {
		List<User> users = new ArrayList<>();
		datas.forEach(x -> users.add(transformToDomain(x)));
		return users;
	}

	public static User transformToDomain(UserData data) {
		User user = new User(data.getUsername());
//		user.setCommittedProjects(transformCommittedProjectDataToDomain(data.getCommittedWorkflowDatas()));
		user.clearDomainEvents();
		return user;
	}

//	private static List<CommittedProject> transformCommittedProjectDataToDomain(List<CommittedProjectData> committedProjectDatas){
//		List<CommittedProject> committedProjects = new ArrayList<>();
//		for(CommittedProjectData committedProjectData: committedProjectDatas){
//			CommittedProject committedProject = new CommittedProject(committedProjectData.getUserId(),
//																	committedProjectData.getProjectId(),
//																	committedProjectData.getOrder());
//			committedProjects.add(committedProject);
//		}
//		return committedProjects;
//	}

	public static UserData transformToData(User user) {
		UserData data = new UserData();
		data.setUsername(user.getName());
		return data;
	}

//	private static List<CommittedProjectData> transformToData(List<CommittedProject> committedProjects){
//		List<CommittedProjectData> committedProjectDatas = new ArrayList<>();
//		for(CommittedProject committedProject: committedProjects){
//			CommittedProjectData committedProjectData = new CommittedProjectData(committedProject.getUserId(), committedProject.getProjectId(), committedProject.getOrder());
//			committedProjectDatas.add(committedProjectData);
//		}
//		return committedProjectDatas;
//	}
}
