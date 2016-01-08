package com.iii.twentywork.controller.sharefile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.iii.twentywork.model.bean.CheckPathInfoBean;
import com.iii.twentywork.model.bean.FileTreeBean;
import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;
import com.iii.twentywork.model.daointerface.TeamUserDAO;
import com.iii.twentywork.model.service.sharefile.ShareFileService;
import com.iii.twentywork.model.service.user.LoginService;

@WebServlet({"/ShareFile/*","/shareFile/fileUpload","/shareFile/deletefile"})
public class ShareFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShareFileServlet() {
        super();
    }
    private ShareFileService shareFileService;
    @Override
    public void init() throws ServletException {
        ServletContext application = this.getServletContext();
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
        this.shareFileService = (ShareFileService) context.getBean("shareFileService");
//        System.out.println("ShareFileServlet--1.init-shareFileService");
    }
    private static final String UPLOAD_DIRECTORY="C:\\userFileUpload";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
	    HttpSession session = request.getSession();
	    String pathInfo = request.getPathInfo();
	    String servletPath = request.getServletPath();
	    TeamUserBean teamUser =(TeamUserBean) session.getAttribute("teamUserBean");
        
	    System.out.println("ShareFileServlet--Line64++++++++++++++pathInfo: "+pathInfo+"++++++++++++++servletPath: "+servletPath);
//	    System.out.println(teamUser);
	    if(servletPath.equals("/ShareFile"))
        {//取得頁面列表功能
              //驗證資料
                Map<String, String> errors = new HashMap<String, String>();
                request.setAttribute("errors", errors);
                CheckPathInfoBean check = shareFileService.checkPathInfo(pathInfo,teamUser);
                boolean isPass = check.isPass();
                if(!isPass) {
                    System.out.println("ShareFileServlet -- Line74--找不到此資料夾");
                    errors.put("pathError", "找不到此資料夾");}
                
                
               //呼叫Model
                int lastFolder = (check.getFolders().size()-1);
                int upperFolderId = check.getFolders().get(lastFolder).getFileId();
                List<ShareFileBean> fileList = shareFileService.getSortedFileList(upperFolderId);
                session.setAttribute("upperFolderId", upperFolderId); //int
                session.setAttribute("fileList", fileList);  //List<ShareFileBean>
                session.setAttribute("folders", check.getFolders());//List<FileTreeBean>
                
              //呼叫View
                if(errors!=null && !errors.isEmpty()) {
                    String path = request.getContextPath();
                    response.sendRedirect(path+"/ShareFile");
                    return;
                }else {
                    String uri = request.getRequestURI();
//                    System.out.println("ShareFileServlet--Line91--session.setAttribute(\"requestURI\", uri)="+uri);
                    session.setAttribute("requestURI", uri);
                    request.getRequestDispatcher("/shareFile/main.jsp").forward(request, response);
                    return;
                }
            }
	    else if(servletPath.equals("/shareFile/fileUpload"))
	    {//檔案上傳功能
	    	System.out.println("ShareFileServlet--Line99--servletPath.equals(\"/shareFile/fileUpload\")");
	    	String requestURI =(String) session.getAttribute("requestURI");
//	        List<FileTreeBean> folders = (List<FileTreeBean>) session.getAttribute("folders");  
	    	int upperFolderId = (int) session.getAttribute("upperFolderId");
//
			if (ServletFileUpload.isMultipartContent(request)) {
					try {
						List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
						for (FileItem item : multiparts)
						{
						    if (!item.isFormField())
						    {
						        String fileName = new File(item.getName()).getName();
						        ShareFileBean bean = shareFileService.insertFile(teamUser, fileName,upperFolderId);
						        item.write(new File(UPLOAD_DIRECTORY+File.separator+bean.getFileId()+"."+bean.getFileType()));
						    }
						}
						session.setAttribute("message", "File Uploaded Successfully");
					} catch ( Exception e) {
						session.setAttribute("message", "File Upload Failed due to " + e);
						e.printStackTrace();
					}
			} else {
				session.setAttribute("message", "Sorry this Servlet only handles file upload request");
			}
		    response.sendRedirect(requestURI);
	    }
	    
	    else if(servletPath.equals("/shareFile/deletefile"))
	    {//刪除檔案功能
	    	
	    }
	    
	    else{
	    	System.out.println("What the Hall");
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        TeamUserDAO dao = (TeamUserDAO) context.getBean("teamUserDAO");
        TeamUserBean teamUser= dao.select(new TeamUserIdBean(100,200));
        System.out.println(teamUser);
        ShareFileService shareFileService = (ShareFileService) context.getBean("shareFileService");
	    String pathInfo = "/Group1-1/Group1-1-1/sdsd";//  /0   /p1:[,p1]   /p1/p2/p3:[,p1,p2,p3]
        
        int teamId = teamUser.getTeam().getTeamId();
        List<FileTreeBean> folderTree = shareFileService.getGroupFolderTree(teamId);
        System.out.println(folderTree);
        String[] pathName = pathInfo.split("/");//取得各層folder名稱
        
        int folderTreeSize = folderTree.size();
        int folderTreeMaxLevel = folderTree.get(folderTreeSize-1).getFileLevel();
        
	}

}
