package com.iii.twentywork.model.bean;

public class FileTreeBean
{
    //FileTree (fileId, fileName_,fileType,fileSize, teamId,upperFolderId , fileLevel)
    private int fileId; //PK
    private String fileName; 
    private String fileType; 
    private Integer fileSize;
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
    @Override
    public String toString()
    {
        return "FolderTreeBean [fileId=" + fileId + ", fileName=" + fileName
                + ", fileType=" + fileType + ", fileSize=" + fileSize
                + ", teamId=" + teamId + ", upperFolderId=" + upperFolderId
                + ", fileLevel=" + fileLevel + "]";
    }
    
    
    
//    public static void main(String[] args) {
//        FolderTreeBean bean = new FolderTreeBean();
//        bean.setFileId(1);
//        bean.setFileName("烏拉拉");
//        bean.setUpperFolderId(5);
//        bean.setFileLevel(3);
//        System.out.println(bean);
//    }
    
}
