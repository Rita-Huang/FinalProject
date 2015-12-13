package liting.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private int fileId; //PK
    private String fileName; //not null:後端產生
    private String fileType; //not null:後端產生
    private int fileSize;
    private Date updateTime;
    private byte[] file;
    private int userId; //not null:從前端取得
    private int teamId; //not null:從前端取得
    private int upperFolderId;
    
    
    public ShareFileBean() {
        System.out.println("ERROR PATH: ShareFile 空的建構子");
    }
    public ShareFileBean(int userId,int teamId,String filePath) {
        this.userId = userId;
        this.teamId = teamId;
        setFileName(filePath);
        setFileType(filePath);
        setFileSize(filePath);
        setUpdateTime();
    }

    public int getFileId()
    {
        return fileId;
    }

    //FileId SQL自動產生
    // public void setFileId(int fileId)
    // {
    // this.fileId = fileId;
    // }

    public String getFileName()
    {
        return fileName;
    }

    private void setFileName(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        int begin = new String(filePath).lastIndexOf("\\");
        
        this.fileName = filePath.substring(begin+1,end);
    }

    public String getFileType()
    {
        return fileType;
    }

    private void setFileType(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        
        this.fileType = filePath.substring(end+1);
        this.fileType =new String(this.fileType).toUpperCase();
    }

    public int getFileSize()
    {
        return fileSize;
    }

    private void setFileSize(String filePath)
    {
        File file = new File(filePath);        
        this.fileSize = (int) file.length();
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    private void setUpdateTime()
    {
        this.updateTime = new Date(System.currentTimeMillis());
    }

    public byte[] getFile()
    {
        return file;
    }

    public void setFile(String filePath)
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

    private void smallFile(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        in.read(buffer);
        
    }
    
    
    
    
    @Override
    public String toString()
    {
        return fileId+" , "+fileName+" , "+upperFolderId;
    }

    public static void main(String[] args)
    {
        ShareFileBean file = new ShareFileBean();
        String filePath = "D:\\測試test\\貓cat4.jpg";
        file.setFileName(filePath);
        boolean testing1 = "貓cat4".equals(file.getFileName());
        System.out.println("testing1:  "+testing1);
        
        file.setFileType(filePath);
        boolean testing2 = "JPG".equals(file.getFileType());
        System.out.println("testing2:  "+testing2);
        
        file.setFileSize(filePath);
        System.out.println("testing3:  "+file.getFileSize());
        
        file.setUpdateTime();
        System.out.println("testing4:  "+file.getUpdateTime());
        
//        boolean testing2 = "JPG".equals(file.getFileType());
//        System.out.println("testing2:  "+testing2);
    }

}
