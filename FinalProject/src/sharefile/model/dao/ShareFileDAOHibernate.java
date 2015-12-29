package sharefile.model.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import model.hibernate.HibernateUtil;
import sharefile.model.FolderTreeBean;
import sharefile.model.ShareFileBean;
import sharefile.model.ShareFileDAO;

public class ShareFileDAOHibernate implements ShareFileDAO
{
    private Session session;
    public void setSession(Session session)
    {
        this.session = session;
    }
    public Session getSession()
    {
        return session;
    }
    public ShareFileDAOHibernate(Session session) {
        this.session = session;
    }
    
    private static final String SELETE_FILE_LIST = "select * from ShareFile where upperFolderId=?";
    @Override
    public List<ShareFileBean> getFileList(int upperFolderId)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private static final String INSERT = "insert into ShareFile values(?,?,?,?,?,?,?)";
    @Override
    public ShareFileBean insert(ShareFileBean bean)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private static final String FOLDER_TREE = "{call gen_folder_tree (:teamId)}";
    @Override
    public List<FolderTreeBean> getGroupFolderTree(int teamId)
    {
        // TODO Auto-generated method stub
        
//        SQLQuery query = getSession().createSQLQuery(FOLDER_TREE);
//        query.setParameter("teamId", teamId);
//        query.addEntity(FolderTreeBean.class);
//        List<FolderTreeBean> result =  query.list();
        return null;
    }

    private static final String UPDATE = "update ShareFile set fileName_ = ?, upperFolderId = ?  where fileId = ?";
    @Override
    public ShareFileBean updateFile(ShareFileBean bean, int newFolderId,
            String newFileName)
    {
        // TODO Auto-generated method stub
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

    private static final String SELECT_BY_FILEID = "select * from ShareFile where fileId=?";
    @Override
    public ShareFileBean selectByFileId(int fileId)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            ShareFileDAO dao = new ShareFileDAOHibernate(session);
            
            
            List<FolderTreeBean> rs2 = dao.getGroupFolderTree(3);
            System.out.println(rs2.isEmpty());
//            for(Iterator<FolderTreeBean> item=rs2.iterator();item.hasNext(); ) {
//                System.out.println(item.next());
//            }//testing#11
            
            
          //delete(int id)
            //testing#1
//            System.out.println(dao.deleteFile(22,false));
            
            session.getTransaction().commit();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
    }

}
