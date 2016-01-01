package com.iii.twentywork.model.service.sharefile;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.daointerface.ShareFileDAO;
import com.iii.twentywork.model.daointerface.TeamUserDAO;

@Component(value = "shareFileService")
public class ShareFileService
{
    @Autowired
    private ShareFileDAO shareFileDAO ;
    public void setShareFileDAO(ShareFileDAO shareFileDAO)
    {
        this.shareFileDAO = shareFileDAO;
        System.out.println("ShareFileService setShareFileDAO");
    }
    
    public boolean checkPathInfo(String pathInfo, TeamUserBean teamUser) {
        int teamId = teamUser.getTeam().getTeamId();
        List<FileTreeBean> folderTree = getGroupFolderTree(teamId);
//        System.out.println(folderTree);
        String[] pathName = pathInfo.split("/");//取得各層folder名稱
        
        int folderTreeSize = folderTree.size();
        int folderTreeMaxLevel = folderTree.get(folderTreeSize-1).getFileLevel();
        
//        System.out.println("into routine");
        if(pathName.length==0 || folderTreeMaxLevel<(pathName.length-1)) {
//            System.out.println("不用比對path"
//                                +"  pathLevel= "+ (pathName.length-1)
//                                +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
            return false;
        } else
        {
            int folderTreeIndex = 1;// folderTreeIndex=0:folder根目錄
            for (int pathLevel = 1; pathLevel < pathName.length; pathLevel++)
            {
//                System.out.println("pathLevel= " + pathLevel);
                String pathElement = pathName[pathLevel];
                int comparedLevel;
                boolean isConformity=false;
                do
                {
                    FileTreeBean compared = folderTree.get(folderTreeIndex);
                 // 比對folderName
                    if(pathElement.equals(compared.getFileName())) {
                       isConformity = true;
                    }
                    folderTreeIndex++;
                    comparedLevel = compared.getFileLevel();
                } while (comparedLevel == pathLevel && folderTreeIndex < folderTreeSize-1);
                if(!isConformity) {
//                    System.out.println("找不到符合的Folder");
//                    break;
                    return false;
                }
            }
            return true;
        }
    }
    
    
    
    public List<FileTreeBean> getGroupFolderTree(int teamId)
    {//testing#1
        return shareFileDAO.getGroupFolderTree(teamId);
    }
    
    public ShareFileBean getGroupRootFolder(int teamId) {
        return shareFileDAO.getGroupRootBean(teamId);
    }
    
    
    
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        ShareFileService service = (ShareFileService) context.getBean("shareFileService");
        System.out.println(service.getGroupFolderTree(201));
        System.out.println(service.getGroupFolderTree(203));
        
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    /**
     * 新增多個檔案
     */
    public List<ShareFileBean> insertFile(TeamUserDAO teamUserDAO,int upperFolderId, String[] filePath)
    {//testing#2
        List<ShareFileBean> beans = new ArrayList<ShareFileBean>();
        
//        for(int i=0;i<filePath.length;i++) {
//            ShareFileBean bean = new ShareFileBean (userId, teamId,upperFolderId, filePath[i]);
//            beans.add(dao.insert(bean));
//        }
        return beans;
    }
    
    
    /**
     * 新增檔案夾
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param folderName
     * @return ShareFileBean裡所有屬性都要放
     */
    public ShareFileBean insertFolder(int userId, int teamId, int upperFolderId,String folderName)
    {//testing#3
        ShareFileBean bean =new ShareFileBean(userId,teamId,folderName,upperFolderId);
        return shareFileDAO.insert(bean);
    }
    
    /**
     * 建立新的Group User的檔案分享根目錄Folder
     * @param userId
     * @param teamId
     * @param upperFolderId
     * @param fileName
     * @return ShareFileBean裡fileSize,updateTime屬性為Null不設定
     */
    public  ShareFileBean insertFolder(int userId,int teamId) 
    {//testing#4
        ShareFileBean bean =new ShareFileBean(userId,teamId,"Group"+teamId+"根目錄",1);
        return shareFileDAO.insert(bean);
    }
    
    
    /**
     * 刪除單一檔案
     * @param fileId
     * @return 回傳刪除的檔案Id
     */
    public int deleteFile(ShareFileBean bean) 
    {//testing#8
        return shareFileDAO.deleteFile(bean.getFileId(),bean.getFileSize()==0);
    }
    
    
    /**
     * 刪除多個檔案
     * @param fileId
     * @return 回傳刪除的檔案Id陣列
     */
    public int[] deleteFile(List<ShareFileBean> bean)
    {//testing#12
        int[] resultset = new int[bean.size()];
        for(int i =0;i<bean.size();i++){
            int fileId = deleteFile(bean.get(i));
            resultset[i]=fileId;
        }
        return resultset;
    }
    
    
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

    
    

}
