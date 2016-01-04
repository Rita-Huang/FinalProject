package com.iii.twentywork.model.bean;
// Generated 2015/12/28 �U�� 11:25:11 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Team generated by hbm2java
 */
@Entity
@Table(name="TEAM")
@Component(value="teamBean")
public class TeamBean implements java.io.Serializable
{

    @Override
    public String toString()
    {
        return "Team [teamId=" + teamId + ", teamName=" + teamName + "]";
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="identifier")
    @SequenceGenerator(name="identifier", allocationSize=1)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teamId;

    private String teamName;

    private byte[] teamImage;
    
    private String teamAbout;

    private Set<TeamUserBean> teamUsers = new HashSet<TeamUserBean>(0);

    public TeamBean()
    {
    }

    public TeamBean(int teamId, String teamName, String teamAbout)
    {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamAbout = teamAbout;
    }

    public TeamBean(int teamId, String teamName, byte[] teamImage, String teamAbout,
            Set<TeamUserBean> teamUsers)
    {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamImage = teamImage;
        this.teamAbout = teamAbout;
        this.teamUsers = teamUsers;
    }

    public int getTeamId()
    {
        return this.teamId;
    }

    public void setTeamId(int teamId)
    {
        this.teamId = teamId;
    }

    public String getTeamName()
    {
        return this.teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public byte[] getTeamImage()
    {
        return this.teamImage;
    }

    public void setTeamImage(byte[] teamImage)
    {
        this.teamImage = teamImage;
    }
    
    public String getteamAbout()
    {
        return this.teamAbout;
    }

    public void setteamAbout(String teamAbout)
    {
        this.teamAbout = teamAbout;
    }
    
    public Set<TeamUserBean> getTeamUsers()
    {
        return this.teamUsers;
    }

    public void setTeamUsers(Set<TeamUserBean> teamUsers)
    {
        this.teamUsers = teamUsers;
    }

}
