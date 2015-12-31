package com.iii.twentywork.model.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.daointerface.ShareFileDAO;

@Component(value = "shareFileDAO")
public class ShareFileDAOHibernate implements ShareFileDAO
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
//        System.out.println("ShareFileDAOHibernate setSessionFactory");
    }
    
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
//        System.out.println("ShareFileDAOHibernate getSession");
        return session;
    }
    public ShareFileDAOHibernate() {}
    
    
    //selectByFileId = "select * from ShareFile where fileId=?";
    @Override
    public ShareFileBean selectByFileId(int fileId)
    {//testing#1
        ShareFileBean bean = (ShareFileBean) getSession().get(ShareFileBean.class, fileId);
//        System.out.println("ShareFileDAOHibernate  select結束");
        return bean;
    }
    
    // INSERT = "insert into ShareFile values(?,?,?,?,?,?,?)";
    @Override
    public  ShareFileBean insert(ShareFileBean bean)
    {//testing#2
        int pk = (int) getSession().save(bean);
//        System.out.println("ShareFileDAOHibernate--insert--pk="+pk);
        return selectByFileId(pk);//;
    }
   
  //select file list :"select * from ShareFile where upperFolderId=?"
    @Override
    public List<ShareFileBean> getFileList(int upperFolderId)
    {//testing#3
        ShareFileBean bean = selectByFileId(upperFolderId);
        if(bean!=null) {
            Set<ShareFileBean> beans = bean.getInnerFiles();
            List<ShareFileBean> list = new ArrayList<ShareFileBean>();
            for (Iterator<ShareFileBean> item = beans.iterator(); item.hasNext();)
            {
                list.add((ShareFileBean) item.next());
            }
            return list;
        }else {
            return null;
        }
    }
    

    private static final String FOLDER_TREE = "{call gen_folder_tree (:teamId)}";
    @Override
    public List<FileTreeBean> getGroupFolderTree(int teamId)
    {
        // TODO Auto-generated method stub
        
//        SQLQuery query = getSession().createSQLQuery(FOLDER_TREE);
//        query.setParameter("teamId", teamId);
//        query.addEntity(FolderTreeBean.class);
//        List<FolderTreeBean> result =  query.list();
        return null;
    }

    

    
    private static final String FIND = "{call find_file_by_fileName (?,?)}";
    @Override
    public List<ShareFileBean> findFileByFileName(String queryString,
            int upperFolderId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    
    private static final String DELETE = "delete from ShareFile where fileId = ?";
    private static final String DELETE_FOLDER = "{call find_delete_files(?)}";
    @Override
    public int deleteFile(int fileId, boolean isFolder)
    {
     // TODO Auto-generated method stub
      //testing#1
      //刪除(Delete):delete(bean)
        int result ;
        if (!isFolder)
        {
            ShareFileBean bean = (ShareFileBean) getSession().get(ShareFileBean.class, fileId);
            getSession().delete(bean);
            return fileId;
        } else
        {
            // create procedure find_delete_files ( @v_fileId int)
            // CallableStatement stmt = conn.prepareCall(DELETE_FOLDER);
            // stmt.setInt(1, fileId);
            // stmt.execute();
            // int couunt = stmt.getUpdateCount();
            // if(couunt>0) {//執行刪除成功
            // return fileId;
            // }
//            ProcedureCall stmt = getSession().createStoredProcedureCall(DELETE_FOLDER);
            return -1;
        }
        
        
        
        
        
    }

    
    private static final String UPDATE = "update ShareFile set fileName_ = ?, upperFolderId = ?  where fileId = ?";
    @Override
    public ShareFileBean updateFile(ShareFileBean bean, int newFolderId,
            String newFileName)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
      //testing#3
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      int upperFolderId=901;
//      List<ShareFileBean> beans = dao.getFileList(upperFolderId);
//      for(Iterator<ShareFileBean> item = beans.iterator();item.hasNext(); ) {
//          System.out.println(item.next());
//      }
        
        
      //testing#1
//      ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//      for(int i=910;i<=930;i++) {
//      ShareFileBean bean = dao.selectByFileId(i);
//      System.out.println(bean);
//      }
      
      
     
      //testing#2
//        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
//        int upperFolderId = 905;
//        ShareFileBean upperFolder = dao.selectByFileId(upperFolderId);
//        
//        String path = "C:\\測試test\\貓cat6.jpg";
//        ShareFileBean newbean = new ShareFileBean(105, 200, path);
//        newbean.setUpperFolder(upperFolder);
//        System.out.println(newbean);
//        System.out.println(dao.insert(newbean));

        
        
        sessionFactory.getCurrentSession().getTransaction().commit();
//       
    }

}
