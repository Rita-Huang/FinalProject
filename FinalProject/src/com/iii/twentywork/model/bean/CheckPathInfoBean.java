package com.iii.twentywork.model.bean;

import java.util.List;

public class CheckPathInfoBean
{
    private boolean isPass;
    private List<FileTreeBean> folders;
    
    
    public CheckPathInfoBean() {}
    public CheckPathInfoBean(boolean isPass)
    {
        this.isPass = isPass;
    }
    public CheckPathInfoBean(boolean isPass, List<FileTreeBean> folders)
    {
        this.isPass = isPass;
        this.folders = folders;
    }
    
    
    public boolean isPass() { return isPass; }
    public void setPass(boolean isPass) { this.isPass = isPass; }
    
    public List<FileTreeBean> getFolders() { return folders; }
    public void setFolders(List<FileTreeBean> folders) { this.folders = folders; }
    
}
