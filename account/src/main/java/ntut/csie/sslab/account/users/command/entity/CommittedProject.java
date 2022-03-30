package ntut.csie.sslab.account.users.command.entity;

public class CommittedProject {
    private String userId;
    private String projectId;
    private int order;

    public CommittedProject(String userId, String projectId, int order) {
        this.userId = userId;
        this.projectId = projectId;
        this.order = order;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
