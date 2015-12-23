package register.model;

public class TeamUserBean
{
    private int userID;
    private int teamID;
    private String post;
    private String department;
    private String extension;
    private java.util.Date activeDate;
    private int rights; 
    
    
    @Override
    public String toString()
    {
        return "TeamUserBean [userID=" + userID + ", teamID=" + teamID
                + ", post=" + post + ", department=" + department
                + ", extension=" + extension + ", activeDate=" + activeDate
                + ", rights=" + rights + "]";
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public int getTeamID()
    {
        return teamID;
    }

    public void setTeamID(int teamID)
    {
        this.teamID = teamID;
    }

    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public java.util.Date getActiveDate() {
        return activeDate;
    }
    public void setActiveDate(java.util.Date activeDate) {
        this.activeDate = activeDate;
    }
    public int getRights() {
        return rights;
    }
    public void setRights(int rights) {
        this.rights = rights;
    }
}
