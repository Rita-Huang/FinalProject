package sharefile.model.dao;

import java.io.*;
import java.sql.*;
import java.util.*;

import sharefile.model.*;

public class ShareFileDAOJdbc implements Serializable, ShareFileDAO
{
    private static final long serialVersionUID = 1L;
    
    Connection conn;
    private void getConnection() throws SQLException
    {
        String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=TwentyWork";
        conn = DriverManager.getConnection(connUrl, "sa", "passw0rd");
    }
    private void closeConnection() throws SQLException
    {
        if (conn != null)
            conn.close();
    }
    
    
    private static final String SELETE_FILE_LIST = "select * from ShareFile where upperFolderId=?";
    @Override
    public List<ShareFileBean> getFileList(int upperFolderId)
    {//testing#5
        List<ShareFileBean> fileList = new ArrayList<ShareFileBean>();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(SELETE_FILE_LIST);
            stmt.setInt(1, upperFolderId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                ShareFileBean bean = new ShareFileBean();
                bean.setFileId(rs.getInt(1));
                bean.setFileName(rs.getString(2));
                bean.setFileType(rs.getString(3));
                bean.setFileSize(rs.getInt(4));
                bean.setUpdateTime(rs.getTimestamp(5));
                bean.setUserId(rs.getInt(6));
                bean.setTeamId(rs.getInt(7));
                bean.setUpperFolderId(rs.getInt(8));
                fileList.add(bean);
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return fileList;
    }
    
    
    
    private static final String INSERT = "insert into ShareFile values(?,?,?,?,?,?,?)";
    @Override
    public ShareFileBean insert(ShareFileBean bean) 
    {//testing#1
        try {
            PreparedStatement stmt = conn.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
            
            stmt.setNString(1,bean.getFileName());
            stmt.setString(2, bean.getFileType());
            
            if(bean.getFileSize()==0) {
                stmt.setNull(3, java.sql.Types.INTEGER);
                stmt.setNull(4, java.sql.Types.DATE);
            }else {
                stmt.setInt(3, bean.getFileSize());
                stmt.setObject(4, bean.getUpdateTime());
            }
            
            stmt.setInt(5, bean.getUserId());
            stmt.setInt(6, bean.getTeamId());
            stmt.setInt(7, bean.getUpperFolderId());
            int result = stmt.executeUpdate();
            if(result ==0) {
                System.out.println("Creating user failed, no rows affected.");
                return bean=null;
            }else {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if(generatedKeys.next()) {
                    bean.setFileId(generatedKeys.getInt(1));
                }else {
                    System.out.println("Creating user failed, no ID obtained.");
                    return bean = null;
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("catch the error: @insertFile()");
        }
        return bean;
    }
    
    
    
    
    
    
    private static final String FOLDER_TREE = "{call gen_folder_tree (?)}";
    @Override
    public List<FolderTreeBean> getGroupFolderTree(int teamId)
    {//testing#11
        List<FolderTreeBean> result = new ArrayList<FolderTreeBean>();
        try
        {
            CallableStatement stmt=conn.prepareCall(FOLDER_TREE);
            stmt.setInt(1, teamId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                FolderTreeBean bean = new FolderTreeBean();
              //FileTree (fileId, fileName_,fileType,fileSize, teamId,upperFolderId , fileLevel)
                bean.setFileId(rs.getInt(1));
                bean.setFileName(rs.getString(2));
                bean.setUpperFolderId(rs.getInt(6));
                bean.setFileLevel(rs.getInt(7));
                result.add(bean);
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    private static final String UPDATE = "update ShareFile set fileName_ = ?, upperFolderId = ?  where fileId = ?";
    @Override
    public ShareFileBean updateFile(ShareFileBean bean, int newFolderId,String newFileName)
    {//testing#7
        try
        {
            PreparedStatement stmt = conn.prepareStatement(UPDATE);
            
            if(bean.getFileSize()==0) {
                stmt.setNString(1, newFileName);
            }else {
                stmt.setNString(1, newFileName+"."+bean.getFileType());
            }
            
            stmt.setInt(2, newFolderId);
            stmt.setInt(3, bean.getFileId());
            int rs = stmt.executeUpdate();
            if(rs==0) {
                System.out.println("Update Fail");
                return null;
            }else {
                bean.setFileName(newFileName);
                bean.setUpperFolderId(newFolderId);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return bean;
    }
    
    private static final String DELETE = "delete from ShareFile where fileId = ?";
    private static final String DELETE_FOLDER = "{call find_delete_files(?)}";
    @Override
    public int deleteFile(int fileId,boolean isFolder) 
    {//testing#8
        int result ;
        try
        {
            if (!isFolder)
            {
                PreparedStatement stmt = conn.prepareStatement(DELETE);
                stmt.setInt(1, fileId);
                result = stmt.executeUpdate();
                if (result == 1)//delete 成功
                {
                    return fileId;
                }

            } else
            {
             // create procedure find_delete_files ( @v_fileId int)
                CallableStatement stmt = conn.prepareCall(DELETE_FOLDER);
                stmt.setInt(1, fileId);
                stmt.execute();
                int couunt = stmt.getUpdateCount();
                if(couunt>0) {//執行刪除成功
                    return fileId;
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
    
    private static final String FIND = "{call find_file_by_fileName (?,?)}";
    @Override
    public List<ShareFileBean> findFileByFileName(String queryString,int upperFolderId)
    {//testing#12
        List<ShareFileBean> result = new ArrayList<ShareFileBean>();
        try
        {
            CallableStatement stmt=conn.prepareCall(FIND);
            stmt.setInt(1, upperFolderId);
            stmt.setString(2, queryString);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                ShareFileBean bean = new ShareFileBean();
                //findFile (fileId, fileName_,fileType,fileSize,updateTime,userId, teamId,upperFolderId , fileLevel)
                bean.setFileId(rs.getInt(1));
                bean.setFileName(rs.getString(2));
                bean.setFileType(rs.getString(3));
                bean.setFileSize(rs.getInt(4));
                bean.setUpdateTime(rs.getTimestamp(5));
                bean.setUserId(rs.getInt(6));
                bean.setTeamId(rs.getInt(7));
                bean.setUpperFolderId(rs.getInt(8));
                result.add(bean);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return result;
    }
    
    public ShareFileDAOJdbc() {
        try
        {
            getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    private static final String SELECT_BY_FILEID = "select * from ShareFile where fileId=?";
    private ShareFileBean selectByFileId(int fileId) 
    {//testing#6
        ShareFileBean bean = new ShareFileBean();
        try
        {
            PreparedStatement stmt = conn.prepareStatement(SELECT_BY_FILEID);
            stmt.setInt(1, fileId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                bean.setFileId(rs.getInt(1));
                bean.setFileName(rs.getString(2));
                bean.setFileType(rs.getString(3));
                bean.setFileSize(rs.getInt(4));
                bean.setUpdateTime(rs.getTimestamp(5));
                bean.setUserId(rs.getInt(6));
                bean.setTeamId(rs.getInt(7));
                bean.setUpperFolderId(rs.getInt(8));
            }else {
                System.out.println("Creating user failed, no rows affected.");
                return bean=null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return bean;
    }
    
    public static void main(String[] args) throws FileNotFoundException, SQLException
    {
        ShareFileDAOJdbc dao = new ShareFileDAOJdbc();
        String  path= "C:\\測試test\\貓cat1.jpg";
        String[] paths = {"C:\\測試test\\貓cat1.jpg","C:\\測試test\\貓cat2.jpg","C:\\測試test\\貓cat3.jpg","C:\\測試test\\貓cat4.jpg","C:\\測試test\\貓cat5.jpg","C:\\測試test\\貓cat6.jpg"};
        
        
//        System.out.println(dao.insertFile(1, 1, 2, path));  //testing#1
//        System.out.println(dao.insertFile(1, 1, 2, paths)); //testing#2
//        System.out.println(dao.insertFolder(1, 1, 2, "喵的勒")); //testing#3
//        System.out.println(dao.insertFolder(1,1));//testing#4
        
        List<ShareFileBean> rs = dao.getFileList(4);
        for(Iterator<ShareFileBean> item=rs.iterator();item.hasNext(); ) {
            System.out.println(item.next());
        }//testing#5
        
        System.out.println(dao.selectByFileId(11));//testing#6
        System.out.println(dao.updateFile(dao.selectByFileId(11), 3, "catcat"));//testing#7
        System.out.println(dao.updateFile(dao.selectByFileId(18), 2, "喵喵"));//testing#7
        
//        System.out.println(dao.deleteFile(dao.selectByFileId(5)));//testing#8
//        System.out.println(dao.deleteFile(dao.selectByFileId(13)));//testing#8

//        System.out.println(dao.copyFile(dao.selectByFileId(20), 9));//testing#10
        
        List<FolderTreeBean> rs2 = dao.getGroupFolderTree(3);
        for(Iterator<FolderTreeBean> item=rs2.iterator();item.hasNext(); ) {
            System.out.println(item.next());
        }//testing#11
        
        List<ShareFileBean> rs4 = new ArrayList<ShareFileBean>();
        rs4.add(dao.selectByFileId(6));
        rs4.add(dao.selectByFileId(7));
        rs4.add(dao.selectByFileId(11));
        rs4.add(dao.selectByFileId(12));
//        int[] rs4rs = dao.deleteFile(rs4);
//        for(int e: rs4rs) {
//            System.out.println(e);
//        }//testing#12
        

        
        
        dao.closeConnection();
    }

}
