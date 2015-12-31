package com.iii.twentywork.model.bean;

import java.sql.Timestamp;

public class FileTreeBean
{
    //findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)
    private int fileId; //PK
    private String fileName; 
    private String fileType; 
    private Integer fileSize;
    private Timestamp updateTime;
    private Integer userId; 
    private Integer teamId; 
    private Integer upperFolderId; 
    private Integer fileLevel;
    
    public int getFileId()
    { return fileId; }
    public void setFileId(int fileId)
    { this.fileId = fileId; }
    
    public String getFileName()
    { return fileName; }
    public void setFileName(String fileName)
    { this.fileName = fileName; }
    
    public String getFileType()
    { return fileType; }
    public void setFileType(String fileType)
    { this.fileType = fileType; }
    
    public Integer getFileSize()
    { return fileSize; }
    public void setFileSize(Integer fileSize)
    { this.fileSize = fileSize; }
    
    public Integer getTeamId()
    { return teamId; }
    public void setTeamId(Integer teamId)
    { this.teamId = teamId; }
    
    public Integer getUpperFolderId()
    { return upperFolderId; }
    public void setUpperFolderId(Integer upperFolderId)
    { this.upperFolderId = upperFolderId; }
    
    public int getFileLevel()
    { return fileLevel; }
    public void setFileLevel(Integer fileLevel)
    { this.fileLevel = fileLevel; }
    
    public Timestamp getUpdateTime() { return updateTime; }
    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
	@Override
	public String toString() {
		return "FileTreeBean [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", fileSize="
				+ fileSize + ", updateTime=" + updateTime + ", userId=" + userId + ", teamId=" + teamId
				+ ", upperFolderId=" + upperFolderId + ", fileLevel=" + fileLevel + "]";
	}
    
    
}
