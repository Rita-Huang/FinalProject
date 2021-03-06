package register.model;
// Generated 2015/12/23 �U�� 06:48:20 by Hibernate Tools 4.3.1.Final

import java.util.Date;

/**
 * TeamUser generated by hbm2java
 */
public class TeamUserBean implements java.io.Serializable
{

    private TeamUserId id;

    private MememberBean memember;

    private TeamBean team;

    private String post;

    private String depart;

    private String extension;

    private Date activeDate;

    private int rights;

    public TeamUserBean()
    {
    }

    public TeamUserBean(TeamUserId id, MememberBean memember, TeamBean team,
            Date activeDate, int rights)
    {
        this.id = id;
        this.memember = memember;
        this.team = team;
        this.activeDate = activeDate;
        this.rights = rights;
    }

    public TeamUserBean(TeamUserId id, MememberBean memember, TeamBean team, String post,
            String depart, String extension, Date activeDate, int rights)
    {
        this.id = id;
        this.memember = memember;
        this.team = team;
        this.post = post;
        this.depart = depart;
        this.extension = extension;
        this.activeDate = activeDate;
        this.rights = rights;
    }

    public TeamUserId getId()
    {
        return this.id;
    }

    public void setId(TeamUserId id)
    {
        this.id = id;
    }

    public MememberBean getMemember()
    {
        return this.memember;
    }

    public void setMemember(MememberBean memember)
    {
        this.memember = memember;
    }

    public TeamBean getTeam()
    {
        return this.team;
    }

    public void setTeam(TeamBean team)
    {
        this.team = team;
    }

    public String getPost()
    {
        return this.post;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

    public String getDepart()
    {
        return this.depart;
    }

    public void setDepart(String depart)
    {
        this.depart = depart;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    public Date getActiveDate()
    {
        return this.activeDate;
    }

    public void setActiveDate(Date activeDate)
    {
        this.activeDate = activeDate;
    }

    public int getRights()
    {
        return this.rights;
    }

    public void setRights(int rights)
    {
        this.rights = rights;
    }

    @Override
    public String toString()
    {
        return "TeamUser [id=" + id + ", memember=" + memember + ", team="
                + team + ", post=" + post + ", depart=" + depart
                + ", extension=" + extension + ", activeDate=" + activeDate
                + ", rights=" + rights + "]";
    }

    
}
