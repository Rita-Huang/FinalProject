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
     * 新增單一檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract ShareFileBean insertFile(int userId, int teamId,int upperFolderId, String filePath);

    /**
     * 新增多個檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract List<ShareFileBean> insertFile(int userId, int teamId,int upperFolderId, String[] filePath);

    /**
     * 新增檔案夾
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param folderName
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract ShareFileBean insertFolder(int userId, int teamId,int upperFolderId, String folderName);
    
    /**
     * 建立新的Group User的檔案分享根目錄Folder
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param fileName
     * @return ShareFileBean裡fileSize,updateTime屬性為Null不設定
     */
    public abstract ShareFileBean insertFolder(int userId,int teamId);

    
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
     * 刪除單一檔案
     * @param fileId
     * @return 回傳刪除的檔案Id
     */
    public abstract int deleteFile(ShareFileBean bean);

    
    /**
     * 刪除多個檔案
     * @param fileId
     * @return 回傳刪除的檔案Id陣列
     */
    public abstract int[] deleteFile(List<ShareFileBean> bean);

    

    /**
     * 複製檔案
     * @param fileId
     * @param newFolderId
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract ShareFileBean copyFile(ShareFileBean bean,int newFolderId);

    
    /**
     * 搜尋檔案
     * @param teamId
     * @param queryString
     * @param upperFolderId
     * @return ShareFileBean裡所有屬性都要放
     */
    public abstract  List<ShareFileBean> findFileByFileName(String queryString,int upperFolderId);

}
