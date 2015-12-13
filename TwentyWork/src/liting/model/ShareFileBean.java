package liting.model;

import java.sql.Date;

import javax.naming.BinaryRefAddr;
import javax.websocket.Decoder.Binary;

public class ShareFileBean
{
//    CREATE TABLE ShareFile(
//            fileId          int             IDENTITY(1,1)   PRIMARY KEY,
//            fileName_       nvarchar(50)    not null,
//            fileType        varchar(10)     not null,
//            fileSize        int,
//            updateTime      datetime,
//            file_           varbinary(max),
//            userId          int             not null,
//            teamId          int             not null,
//            upperFolderId   int             
//            foreign key (upperFolderId) references ShareFile (fileId)
//        );
    private int fileId;
    private String fileName;
    private String fileType;
    private int fileSize;
    private Date updateTime;
    private byte[] file;
    private int userId;
    private int teamId;
    private int upperFolderId;
    
    
    

    public int getFileId()
    {
        return fileId;
    }

    public void setFileId(int fileId)
    {
        this.fileId = fileId;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileType()
    {
        return fileType;
    }

    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public int getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    public byte[] getFile()
    {
        return file;
    }

    public void setFile(byte[] file)
    {
        this.file = file;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getTeamId()
    {
        return teamId;
    }

    public void setTeamId(int teamId)
    {
        this.teamId = teamId;
    }

    public int getUpperFolderId()
    {
        return upperFolderId;
    }

    public void setUpperFolderId(int upperFolderId)
    {
        this.upperFolderId = upperFolderId;
    }

    
    
    @Override
    public String toString()
    {
        return fileId+" , "+fileName+" , "+upperFolderId;
    }

    public static void main(String[] args)
    {

    }

}
