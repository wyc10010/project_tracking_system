package ntut.csie.sslab.account.projects.query.adapter.presenter;

public class MemberDto {
    private String username;
    private String role;

    public MemberDto(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
