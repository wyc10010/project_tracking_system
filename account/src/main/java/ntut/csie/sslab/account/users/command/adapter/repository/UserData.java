package ntut.csie.sslab.account.users.command.adapter.repository;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
//public class UserData implements UserDetails {
public class UserData{

//	@Column(name="user_id")
//	private String id;

	@Id
	@Column(name="user_name")
	private String username;

//	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//	@JoinColumn(name="user_id_fk")
//	@OrderBy("order")
//	private Set<CommittedProjectData> committedProjectDataSet;

	public UserData() {
//		committedProjectDataSet = new HashSet<>();
	}

	public UserData(String username){

		this.username = username;
//		this.committedProjectDataSet = new HashSet<>(committedProjectData);
	}


//	@Override
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) { this.username = username; }


//	public List<CommittedProjectData> getCommittedWorkflowDatas() {
//		return new ArrayList<>(committedProjectDataSet);
//	}
//	public void setCommittedProjectDatas(List<CommittedProjectData> committedProjectData) {
//		this.committedProjectDataSet = new HashSet<>(committedProjectData);
//	}
}
