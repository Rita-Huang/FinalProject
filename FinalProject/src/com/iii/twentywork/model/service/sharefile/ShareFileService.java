package com.iii.twentywork.model.service.sharefile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.CheckPathInfoBean;
import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamBean;
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
    
  //testing#2
    public CheckPathInfoBean checkPathInfo(String pathInfo, TeamUserBean teamUser)  {
        int teamId = teamUser.getTeam().getTeamId();
        List<FileTreeBean> folderTree = getGroupFolderTree(teamId);
        List<FileTreeBean> folders = new ArrayList<FileTreeBean>();
        folders.add(folderTree.get(0));//group root folder
//        System.out.println("ShareFileService--CheckPathInfoBean--"+folderTree);
        if(pathInfo==null) { //測過web
//            System.out.println("pathInfo==null");
            return new CheckPathInfoBean(true,folders);               
        }
        String[] pathName = pathInfo.split("/");//取得各層folder名稱
        int folderTreeSize = folderTree.size();
        int folderTreeMaxLevel = folderTree.get(folderTreeSize-1).getFileLevel();
//        System.out.println("folderTree:");
//        for(Object i:folderTree){
//        	System.out.println(i);
//        }
        if(pathName.length==0) 
        {//pathInfo="/"  pathName=[]
//            System.out.println("不用比對path1:pathName.length==0"
//                                +"  pathLevel= "+ (pathName.length-1)
//                                +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
            return new CheckPathInfoBean(true,folders);
        }else if(folderTreeMaxLevel<(pathName.length-1)) 
        {//pathInfo階層高於 folderTree最高階層，path錯誤
//            System.out.println("不用比對path2:folderTreeMaxLevel<pathName.length"
//                    +"  pathLevel= "+ (pathName.length-1)
//                    +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
            return new CheckPathInfoBean(false,folders);
        }else
        {//比對各階層目錄名稱
            int folderTreeIndex = 1;// folderTreeIndex=0:folder根目錄
            FileTreeBean compared = folderTree.get(folderTreeIndex);
            for (int pathLevel = 1; pathLevel < pathName.length; pathLevel++)
            {
//                System.out.println("pathLevel= " + pathLevel);
                String pathElement = pathName[pathLevel];
//                System.out.println("+++pathElement="+pathElement);
                int comparedLevel;
                boolean isConformity=false;
                do
                {// 比對folderName
                    if(pathElement.equals(compared.getFileName())) {
                       isConformity = true;
                       folders.add(compared);
                    }
                    folderTreeIndex++;
                    if(folderTree.size()!=folderTreeIndex){
                    compared = folderTree.get(folderTreeIndex);
                    comparedLevel = compared.getFileLevel();
                    }else{
                    	break;
                    }
                } while (comparedLevel == pathLevel && folderTreeIndex < folderTreeSize-1);
                if(!isConformity) {
//                    System.out.println("找不到符合的Folder");
                    return new CheckPathInfoBean(false,folders);
                }
            }
//            System.out.println("final return all folders");
            return new CheckPathInfoBean(true,folders);
        }
    }
  //testing#1
    public List<FileTreeBean> getGroupFolderTree(int teamId)
    {
        return shareFileDAO.getGroupFolderTree(teamId);
    }
  //testing#3 
    public List<ShareFileBean> getSortedFileList(int upperFolderId)
    {
        Set<ShareFileBean> set = shareFileDAO.getFileList(upperFolderId);
        List<ShareFileBean> list = new ArrayList<ShareFileBean>(set);
        Collections.sort (list , new Comparator< ShareFileBean >(){
            public int compare( ShareFileBean o1, ShareFileBean o2 ) {
                int compareByFileType =- o1.getFileType().compareTo(o2.getFileType());
                if(compareByFileType==0) {
                    int compareByFileName = o1.getFileName().compareTo(o2.getFileName());
                    return compareByFileName;
                }else {
                    return compareByFileType;
                }
          }
        });
        return list;
    }
  //testing#4
    public ShareFileBean insertFile(TeamUserBean teamUser,String fileName,int upperFolderId){
    	ShareFileBean upperFolder = shareFileDAO.selectByFileId(upperFolderId);
		ShareFileBean bean = new ShareFileBean(teamUser,fileName,upperFolder);
		return shareFileDAO.insert(bean);
	}
    
    public int deleteFile(int fileId, boolean isFolder){
    	return shareFileDAO.deleteFile(fileId,isFolder);
    }
    
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        ShareFileService service = (ShareFileService) context.getBean("shareFileService");
        
        
        		
      //testing#1
//        System.out.println(service.getGroupFolderTree(201));
//        System.out.println(service.getGroupFolderTree(203));
      //test#2  
//        String pathInfo = "/";
//        TeamUserBean teamUser = new TeamUserBean();
//        TeamBean beanChild = new TeamBean();
//        beanChild.setTeamId(200);
//        teamUser.setTeam(beanChild);
//        CheckPathInfoBean bean = service. checkPathInfo(pathInfo, teamUser); 
//        System.out.println(pathInfo);
//        System.out.println(bean.isPass());
//        for( int i = 0; i < bean.getFolders() .size (); i++ ) {
//            Object o = bean.getFolders() .get (i );
//            System .out . println( "Object = " + o) ;
//        }
      //test#3  
//        List<ShareFileBean> list = service.getSortedFileList(901);
//        for(int i = 0; i<list.size(); i++) {System.out.println(list.get(i));}

        
        
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
    
    
    public ShareFileBean getGroupRootFolder(int teamId) {
        return shareFileDAO.getGroupRootBean(teamId);
    }
    
    

}
