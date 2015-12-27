package hibernate_auto_coding;
// Generated 2015/12/27 �U�� 02:32:07 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ShareFile generated by hbm2java
 */
public class ShareFile implements java.io.Serializable
{
    private int fileId;

    private String fileName;

    private String fileType;

    private Integer fileSize;

    private Date updateTime;

    private Integer userId;

    private Integer teamId;

    private ShareFile shareFile;//parent
    
    private Set<ShareFile> shareFiles = new HashSet<ShareFile>(0);//child

    public ShareFile()
    {
    }

    public ShareFile(int fileId, String fileName, String fileType)
    {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public ShareFile(int fileId, ShareFile shareFile, String fileName,
            String fileType, Integer fileSize, Date updateTime, Integer userId,
            Integer teamId, Set<ShareFile> shareFiles)
    {
        this.fileId = fileId;
        this.shareFile = shareFile;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.updateTime = updateTime;
        this.userId = userId;
        this.teamId = teamId;
        this.shareFiles = shareFiles;
    }

    public int getFileId()
    {
        return this.fileId;
    }

    public void setFileId(int fileId)
    {
        this.fileId = fileId;
    }

    public ShareFile getShareFile()
    {
        return this.shareFile;
    }

    public void setShareFile(ShareFile shareFile)
    {
        this.shareFile = shareFile;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileType()
    {
        return this.fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public Integer getFileSize()
    {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize)
    {
        this.fileSize = fileSize;
    }

    public Date getUpdateTime()
    {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public Integer getUserId()
    {
        return this.userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public Integer getTeamId()
    {
        return this.teamId;
    }

    public void setTeamId(Integer teamId)
    {
        this.teamId = teamId;
    }

    public Set<ShareFile> getShareFiles()
    {
        return this.shareFiles;
    }

    public void setShareFiles(Set<ShareFile> shareFiles)
    {
        this.shareFiles = shareFiles;
    }

}
