package sharefile.model;

import java.util.List;

public interface ShareFileDAO
{
    /**
     * 頁面初始化要取得的檔案列表data<br>
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract List<ShareFileBean> getFileList(int upperFolderId);

    /**
     * 
     * @param ShareFileBean
     * @return ShareFileBean 會把Sql server自動產生的PK值設定回bean裡
     */
    public abstract ShareFileBean insert(ShareFileBean bean); 

    
    /**
     * 取得該team的 Folder樹狀圖資料
     * @param teamId
     * @return FolderTreeBean
     */
    public abstract List<FolderTreeBean> getGroupFolderTree(int teamId);

    
    /**
     * 更新檔案位置或是檔案名稱
     * @param fileId
     * @param newFolderId
     * @param fileName
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract ShareFileBean updateFile(ShareFileBean bean, int newFolderId,String newFileName);

    
    
    /**
     * 搜尋檔案
     * @param teamId
     * @param queryString
     * @param upperFolderId
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract List<ShareFileBean> findFileByFileName(String queryString,int upperFolderId);

    
    public abstract int deleteFile(int fileId,boolean isFolder);
    
    public abstract ShareFileBean selectByFileId(int fileId);
}
