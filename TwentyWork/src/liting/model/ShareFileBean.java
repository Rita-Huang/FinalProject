package liting.model;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;

public class ShareFileBean
{
//    CREATE TABLE ShareFile(
//            fileId          int             IDENTITY(1,1)   PRIMARY KEY,
//            fileName_       nvarchar(50)    not null,
//            fileType        varchar(10)     not null,
//            fileSize        int,
//            updateTime      datetime,
//            file_           varbinary(max),
//            userId          int ,
//            teamId          int ,
//            upperFolderId   int             
//            foreign key (upperFolderId) references ShareFile (fileId)
//        );
    private int fileId; //PK
    private String fileName; //not null:後端產生
    private String fileType; //not null:後端產生
    private int fileSize;
    private Timestamp updateTime;
    private byte[] file;
    private int userId; //從前端取得
    private int teamId; //從前端取得
    private int upperFolderId=1; //從前端取得
    
    
    public ShareFileBean() {
        System.out.println("ERROR PATH: ShareFile 空的建構子");
    }
    public ShareFileBean(int userId,int teamId,String filePath) {
        this.userId = userId;
        this.teamId = teamId;
        insertSetFileName(filePath);
        insertSetFileType(filePath);
        insertSetFileSize(filePath);
        insertSetUpdateTime();
    }
    public ShareFileBean(int userId,int teamId,String folderName,int upperFolderId) {
        this.userId = userId;
        this.teamId = teamId;
        this.fileName = folderName;
        this.fileType = "資料夾";
        this.upperFolderId = upperFolderId;
    }

    
    private void insertSetFileName(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        int begin = new String(filePath).lastIndexOf("\\");
        
        this.fileName = filePath.substring(begin+1,end);
    }

    private void insertSetFileType(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        
        this.fileType = filePath.substring(end+1);
        this.fileType =new String(this.fileType).toUpperCase();
    }

    private void insertSetFileSize(String filePath)
    {
        File file = new File(filePath);        
        this.fileSize = (int) file.length();
    }

    private void insertSetUpdateTime()
    {
//        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
//        Date now = new Date();
//        this.updateTime = (Date)sdfDate.format(now);
        java.util.Date date= new java.util.Date();
        this.updateTime =new Timestamp(date.getTime());
    }


     
    
    
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
    public Timestamp getUpdateTime()
    {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime)
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
        /*ShareFileBean file = new ShareFileBean();
        String filePath = "D:\\測試test\\貓cat4.jpg";
        file.insertSetFileName(filePath);
        boolean testing1 = "貓cat4".equals(file.getFileName());
        System.out.println("testing1:  "+testing1);
        
        file.insertSetFileType(filePath);
        boolean testing2 = "JPG".equals(file.getFileType());
        System.out.println("testing2:  "+testing2);
        
        file.insertSetFileSize(filePath);
        System.out.println("testing3:  "+file.getFileSize());
        
        file.insertSetUpdateTime();
        System.out.println("testing4:  "+file.getUpdateTime());*/
        
        String filePath = "D:\\測試test\\貓cat4.jpg";
        ShareFileBean file = new ShareFileBean(1, 1, filePath);
        boolean testing1 = "貓cat4".equals(file.getFileName());
        System.out.println("testing1:  "+testing1);
        boolean testing2 = "JPG".equals(file.getFileType());
        System.out.println("testing2:  "+testing2);
        System.out.println("testing3:  "+file.getFileSize());
        System.out.println("testing4:  "+file.getUpdateTime());
        System.out.println(file.getUpperFolderId());
        
        
        System.out.println("ShareFileBean main()");
    }

}
