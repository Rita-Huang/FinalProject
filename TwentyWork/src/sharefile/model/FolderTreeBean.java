package sharefile.model;

public class FolderTreeBean
{
    //FileTree (fileId, fileName_,fileType,fileSize, teamId,upperFolderId , fileLevel)
    private int fileId; //PK
    private String fileName; 
    private String fileType; 
    private int fileSize;
    private int teamId; 
    private int upperFolderId; 
    private int fileLevel;
    
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
    
    public int getFileSize()
    { return fileSize; }
    public void setFileSize(int fileSize)
    { this.fileSize = fileSize; }
    
    public int getTeamId()
    { return teamId; }
    public void setTeamId(int teamId)
    { this.teamId = teamId; }
    
    public int getUpperFolderId()
    { return upperFolderId; }
    public void setUpperFolderId(int upperFolderId)
    { this.upperFolderId = upperFolderId; }
    public int getFileLevel()
    {
        return fileLevel;
    }
    public void setFileLevel(int fileLevel)
    {
        this.fileLevel = fileLevel;
    }
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
//        bean.setFileId(rs.getInt(1));
//        bean.setFileName(rs.getString(2));
//        bean.setUpperFolderId(rs.getInt(6));
//        bean.setFileLevel(rs.getInt(7));
        return "[ "+fileId+" , "+fileName+" , "+upperFolderId+" , "+fileLevel+" ]";
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
