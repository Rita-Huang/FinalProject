package sharefile.model;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


public class ShareFileBean
{
//    CREATE TABLE ShareFile(
//            fileId          int             IDENTITY(1,1)  PRIMARY KEY,
//            fileName_       nvarchar(50)    not null,
//            fileType        varchar(10)     not null,
//            fileSize        int,
//            updateTime      datetime,
//            userId          int ,
//            teamId          int ,
//            upperFolderId   int             
//            foreign key (upperFolderId) references ShareFile (fileId)
//        );
    private int fileId; //PK
    private String fileName; //not null
    private String fileType; //not null
    private Integer fileSize;
    private Timestamp updateTime;
    private Integer userId; 
    private Integer teamId; 
    private int upperFolderId=1; 
    private ShareFileBean shareFile;
    private Set<ShareFileBean> shareFiles = new HashSet<ShareFileBean>(0);
    /**
     * 空的建構子，沒有自動設定任何屬性
     */
    public ShareFileBean() {
    }
    
    
    /**
     * 新增檔案時使用的建構子<br>
     * 此建構子會將fileName,fileType,fileSize,updateTime,userId,teamId,upperFolderId設定好<br>
     * 沒設定的屬性:fileId
     * @param userId
     * @param teamId
     * @param filePath
     */
    public ShareFileBean(int userId,int teamId,int upperFolderId,String filePath) {
        this.userId = userId;
        this.teamId = teamId;
        this.upperFolderId = upperFolderId;
        insertSetFileName(filePath);
        insertSetFileType(filePath);
        insertSetFileSize(filePath);
        insertSetUpdateTime();
    }
    
    /**
     * 新增folder時使用的建構子<br>
     * 此建構子會將fileName,fileType,userId,teamId,upperFolderId設定好<br>
     * fileSize,updateTime屬性為Null不設定<br>
     * fileId沒設定
     * @param userId
     * @param teamId
     * @param folderName
     * @param upperFolderId
     */
    public ShareFileBean(int userId,int teamId,String folderName,int upperFolderId) {
        this.userId = userId;
        this.teamId = teamId;
        this.fileName = folderName;
        this.fileType = "資料夾";
        this.upperFolderId = upperFolderId;
    }

    /**
     * 從filePath擷取檔案名稱(包含副檔名)，設定為fileName
     * @param filePath
     */
    private void insertSetFileName(String filePath)
    {
        int begin = new String(filePath).lastIndexOf("\\");
        this.fileName = filePath.substring(begin+1);
    }

    /**
     * 從filePath擷取副檔名，改成全大寫，設定為fileType
     * @param filePath
     */
    private void insertSetFileType(String filePath)
    {
        int end = new String(filePath).lastIndexOf(".");
        this.fileType =new String(filePath.substring(end+1)).toLowerCase();
    }

    
    /**
     * 從filePath讀取檔案，計算檔案size，單位:byte，設定為fileSize
     * @param filePath
     */
    private void insertSetFileSize(String filePath)
    {
        File file = new File(filePath);        
        this.fileSize = (int) file.length();
    }

    /**
     * 取得當下系統時間，設定為updateTime
     */
    private void insertSetUpdateTime()
    {
        java.util.Date date= new java.util.Date();
        this.updateTime =new Timestamp(date.getTime());
    }


     
    
    
    public int getFileId() { return fileId; }
    public void setFileId(int fileId) { this.fileId = fileId; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    
    public Integer getFileSize() { return fileSize; }
    public void setFileSize(Integer fileSize) { this.fileSize = fileSize; }
    
    public Timestamp getUpdateTime() { return updateTime; }
    public void setUpdateTime(Timestamp updateTime) { this.updateTime = updateTime; }
    
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
    
    public Integer getUpperFolderId() { return upperFolderId; }
    public void setUpperFolderId(Integer upperFolderId) { this.upperFolderId = upperFolderId; }
    
    public ShareFileBean getShareFile() { return this.shareFile; }
    public void setShareFile(ShareFileBean shareFile) { this.shareFile = shareFile; }
    
    public Set<ShareFileBean> getShareFiles() { return this.shareFiles; }
    public void setShareFiles(Set<ShareFileBean> shareFiles) { this.shareFiles = shareFiles; }
    
    
    @Override
    public String toString()
    {
        return "ShareFile [fileId=" + fileId + ", shareFile=" + shareFile
                + ", fileName=" + fileName + ", fileType=" + fileType
                + ", fileSize=" + fileSize + ", updateTime=" + updateTime
                + ", userId=" + userId + ", teamId=" + teamId + ", shareFiles="
                + shareFiles + "]";
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
        
//        String filePath = "D:\\測試test\\貓cat4.jpg";
//        ShareFileBean file = new ShareFileBean(1, 1, filePath);
//        boolean testing1 = "貓cat4".equals(file.getFileName());
//        System.out.println("testing1:  "+testing1);
//        boolean testing2 = "JPG".equals(file.getFileType());
//        System.out.println("testing2:  "+testing2);
//        System.out.println("testing3:  "+file.getFileSize());
//        System.out.println("testing4:  "+file.getUpdateTime());
//        System.out.println(file.getUpperFolderId());
        
        
        System.out.println("ShareFileBean main()");
    }

}
