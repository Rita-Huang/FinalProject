package liting.model.dao;

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
    
    public void ShareFileDAOJdbc() {
        try
        {
            getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private static final String insertStmt = "insert into ShareFile values(?,?,?,?,?,?,?,?)";
    public int insertFile(ShareFileBean bean) {
        int result = -1;
        try {
            PreparedStatement stmt = conn.prepareStatement(insertStmt);
            stmt.setNString(1,bean.getFileName());
            stmt.setString(2, bean.getFileType());
            stmt.setInt(3, bean.getFileSize());
            stmt.setObject(4, bean.getUpdateTime());
            stmt.setBytes(5, bean.getFile());
            stmt.setInt(6, bean.getUserId());
            stmt.setInt(7, bean.getTeamId());
            stmt.setInt(8, bean.getUpperFolderId());
            result = stmt.executeUpdate();
            
        }catch(SQLException e) {
            e.printStackTrace();
            System.out.println("insertFile()");
        }
        return result;
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

    }

}
