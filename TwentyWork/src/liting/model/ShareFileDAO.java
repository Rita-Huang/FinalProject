package liting.model;

import java.util.List;

public interface ShareFileDAO
{
    /**
     * 頁面初始化要取得的檔案列表data<br>
     * @return ShareFileBean裡除了file_屬性不需要，其他都要放
     */
    public abstract List<ShareFileBean> getFileList(int teamId,int upperFolderId);

    /**
     * 新增單一檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡除了file,path屬性不需要，其他都要放
     */
    public abstract ShareFileBean insertFile(int userId, int teamId,int upperFolderId, String filePath);

    /**
     * 新增多個檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡除了file,path屬性不需要，其他都要放
     */
    public abstract List<ShareFileBean> insertFile(int userId, int teamId,int upperFolderId, String[] filePath);

    /**
     * 新增檔案夾
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param fileName
     * @return ShareFileBean裡除了file_屬性不需要，其他都要放
     */
    public abstract ShareFileBean insertFolder(int userId, int teamId,int upperFolderId, String fileName);

    
    /**
     * 取得該team的 Folder樹狀圖資料
     * @param teamId
     * @return ShareFileBean裡要有fileId,fileName,upperFolderId
     */
    public abstract ShareFileBean getGroupFolderTree(int teamId);

    
    /**
     * 更新檔案位置或是檔案名稱
     * @param fileId
     * @param newFolderId
     * @param fileName
     * @return ShareFileBean裡除了file_屬性不需要，其他都要放
     */
    public abstract ShareFileBean updateFile(int fileId, int newFolderId,String fileName);

    
    /**
     * 刪除單一檔案
     * @param fileId
     * @return 回傳刪除的檔案Id
     */
    public abstract int deleteFile(int fileId);

    
    /**
     * 刪除多個檔案
     * @param fileId
     * @return 回傳刪除的檔案Id陣列
     */
    public abstract int[] deleteFile(int[] fileId);

    
    /**
     * 下載檔案
     * @param fileId
     * @param savePath
     * @return
     */
    public abstract void downloadFile(int fileId,String savePath);

    /**
     * 複製檔案
     * @param fileId
     * @param newFolderId
     * @return ShareFileBean裡除了file_屬性不需要，其他都要放
     */
    public abstract ShareFileBean copyFile(int fileId,int newFolderId);

    
    /**
     * 搜尋檔案
     * @param teamId
     * @param queryString
     * @param upperFolderId
     * @return ShareFileBean裡除了file_屬性不需要，其他都要放
     */
    public abstract  List<ShareFileBean> findFile(int teamId,String queryString,int upperFolderId);

}
