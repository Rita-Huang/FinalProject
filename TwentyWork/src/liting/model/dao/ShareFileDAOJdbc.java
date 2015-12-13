package liting.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import liting.model.ShareFileBean;

public class ShareFileDAOJdbc implements Serializable
{
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
    
    public ShareFileDAOJdbc() {
        try
        {
            getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private static final String insertStmt = "insert into ShareFile values(?,?,?,?,?,?,?,?)";
    public int insertFile(int userId,int teamId,String filePath) throws FileNotFoundException {
        ShareFileBean bean = new ShareFileBean(userId, teamId, filePath);
        return insertFile(bean,filePath);
    }
    
    public int insertFile(int userId,int teamId,String filePath,int upperFolderId) throws FileNotFoundException {
        ShareFileBean bean = new ShareFileBean(userId, teamId, filePath);
        bean.setUpperFolderId(upperFolderId);
        return insertFile(bean,filePath);
    }
    
    //return 1 : insert成功
    //return -1 : insert失敗
    public int insertFile(ShareFileBean bean,String filePath) throws FileNotFoundException {
        int result = -1;
        try {
            PreparedStatement stmt = conn.prepareStatement(insertStmt);
            
            File f = new File(filePath);
            FileInputStream fis = new FileInputStream(f);
            
            stmt.setNString(1,bean.getFileName());
            stmt.setString(2, bean.getFileType());
            stmt.setInt(3, bean.getFileSize());
            stmt.setObject(4, bean.getUpdateTime());
            stmt.setBinaryStream(5, fis, f.length());
            stmt.setInt(6, bean.getUserId());
            stmt.setInt(7, bean.getTeamId());
            stmt.setInt(8, bean.getUpperFolderId());
            result = stmt.executeUpdate();
            
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("catch the error: @insertFile()");
        }
        return result;
    }
    
    public int insertFolder(int userId,int teamId,String folderName,int upperFolderId) {
        int result = -1;
        try
        {
            ShareFileBean bean = new ShareFileBean(userId, teamId, folderName,upperFolderId);
            PreparedStatement stmt = conn.prepareStatement(insertStmt);
            stmt.setNString(1,bean.getFileName());
            stmt.setString(2, bean.getFileType());
            stmt.setNull(3, java.sql.Types.INTEGER);
            stmt.setNull(4, java.sql.Types.DATE);
            stmt.setNull(5, java.sql.Types.VARBINARY);
            stmt.setInt(6, bean.getUserId());
            stmt.setInt(7, bean.getTeamId());
            stmt.setInt(8, bean.getUpperFolderId());
            result = stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("catch the error: @insertFolder()");
        }
        return result;
    }
    
    private static final String updateStmt = "update ShareFile set fileName_ = ?, upperFolderId = ?  where fileId = ?";
    public int updateFile(String fileName,int upperFolderId,int fileId) {
        int result = -1;
        try
        {
            PreparedStatement stmt = conn.prepareStatement(updateStmt);
            stmt.setNString(1, fileName);
            stmt.setInt(2, upperFolderId);
            stmt.setInt(3, fileId);
            result = stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    private static final String deleteStmt = "delete from ShareFile where fileId = ?";
    public int deleteFile(int fileId) {
        int result = -1;
        try
        {
            PreparedStatement stmt = conn.prepareStatement(deleteStmt);
            stmt.setInt(1, fileId);
            result = stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) throws FileNotFoundException, SQLException
    {
        ShareFileDAOJdbc dao = new ShareFileDAOJdbc();
//        System.out.println(dao.insertFile(1, 1, "D:\\測試test\\貓cat4.jpg"));
//        System.out.println(dao.insertFile(1, 1, "D:\\測試test\\貓cat4.jpg",0));
//        System.out.println(dao.insertFolder(1, 1, "喵的勒", 1));
        System.out.println(dao.updateFile("喵喵喵", 3, 4));
        System.out.println(dao.deleteFile(6));
        dao.closeConnection();
    }

}
