package com.iii.twentywork.model.service.sharefile;

import java.util.ArrayList;
import java.util.List;

import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.dao.ShareFileDAOJdbc;
import com.iii.twentywork.model.daointerface.ShareFileDAO;

public class ShareFileService_old_version{}
//    ShareFileDAO dao = new ShareFileDAOJdbc();
    
    /**
     * 新增單一檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡所有屬性都要放
     */
//    public ShareFileBean insertFile(int userId, int teamId,int upperFolderId, String filePath) 
//    {// testing#1
//        ShareFileBean bean = new ShareFileBean(userId, teamId, upperFolderId,filePath);
//
//        return dao.insert(bean);
//    }
    
    
    
    /**
     * 新增多個檔案
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param filePath
     * @return ShareFileBean裡所有屬性都要放
     */
//    public List<ShareFileBean> insertFile(int userId, int teamId,int upperFolderId, String[] filePath)
//    {//testing#2
//        List<ShareFileBean> beans = new ArrayList<ShareFileBean>();
//        
//        for(int i=0;i<filePath.length;i++) {
//            ShareFileBean bean = new ShareFileBean (userId, teamId,upperFolderId, filePath[i]);
//            beans.add(dao.insert(bean));
//        }
//        return beans;
//    }
    
    
    /**
     * 建立新的Group User的檔案分享根目錄Folder
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param fileName
     * @return ShareFileBean裡fileSize,updateTime屬性為Null不設定
     */
//    public  ShareFileBean insertFolder(int userId,int teamId) 
//    {//testing#4
//        ShareFileBean bean =new ShareFileBean(userId,teamId,"Group"+teamId+"根目錄",1);
//        return dao.insert(bean);
//    }
    
    
    /**
     * 複製檔案
     * @param fileId
     * @param newFolderId
     * @return ShareFileBean裡所有屬性都要放
     */
//    public ShareFileBean copyFile(ShareFileBean bean, int newFolderId)
//    {//testing#10
//        bean.setUpperFolderId(newFolderId);
//        return dao.insert(bean);
//    }
    
    

