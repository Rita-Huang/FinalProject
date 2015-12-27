package register.model;
// Generated 2015/12/23 �U�� 08:33:15 by Hibernate Tools 4.3.1.Final

import java.util.Arrays;
import java.util.Date;

/**
 * Memember generated by hbm2java
 */
public class MememberBean implements java.io.Serializable
{

    private int userId;

    private String username;

    private String email;

    private String password;

    private Date birth;

    private byte[] userImage;

    private String cellphone;

    private String phone;

    public MememberBean()
    {
    }

    public MememberBean(int userId, String username, String email, String password)
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public MememberBean(int userId, String username, String email, String password,
            Date birth, byte[] userImage, String cellphone, String phone)
    {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.userImage = userImage;
        this.cellphone = cellphone;
        this.phone = phone;
    }

    public int getUserId()
    {
        return this.userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Date getBirth()
    {
        return this.birth;
    }

    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    public byte[] getUserImage()
    {
        return this.userImage;
    }

    public void setUserImage(byte[] userImage)
    {
        this.userImage = userImage;
    }

    public String getCellphone()
    {
        return this.cellphone;
    }

    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Memember [userId=" + userId + ", username=" + username
                + ", email=" + email + ", password=" + password + ", birth="
                + birth + ", userImage=" + Arrays.toString(userImage)
                + ", cellphone=" + cellphone + ", phone=" + phone + "]";
    }

    
}
