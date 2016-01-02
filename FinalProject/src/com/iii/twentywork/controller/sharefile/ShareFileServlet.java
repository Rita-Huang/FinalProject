package com.iii.twentywork.controller.sharefile;

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

/**
 * Servlet implementation class ShareFileMainPageServlet
 */
@WebServlet("/ShareFile/*")
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
        System.out.println("ShareFileServlet--1.init-shareFileService");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //接收資料
	    HttpSession session = request.getSession();
	    String pathInfo = request.getPathInfo();
	    TeamUserBean teamUser =(TeamUserBean) session.getAttribute("teamUserBean");
	  
	  //驗證資料
        Map<String, String> errors = new HashMap<String, String>();
        request.setAttribute("errors", errors);
        CheckPathInfoBean check = shareFileService.checkPathInfo(pathInfo,teamUser);
        boolean isPass = check.isPass();
        if(!isPass) {errors.put("pathError", "找不到此資料夾");}
        request.setAttribute("folders", check.getFolders());//List<FileTreeBean>
        
	  //呼叫Model
        int lastFolder = (check.getFolders().size()-1);
        int upperFolderId = check.getFolders().get(lastFolder).getFileId();
        List<ShareFileBean> list = shareFileService.getSortedFileList(upperFolderId);
        request.setAttribute("list", list);
        
      //呼叫View
        if(errors!=null && !errors.isEmpty()) {
            String path = request.getContextPath();
            response.sendRedirect(path+"/ShareFile");
        }else {
            request.getRequestDispatcher("/shareFile/test.jsp").forward(request, response);
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
        
        
        System.out.println("into routine");
        if(pathName.length==0 || folderTreeMaxLevel<(pathName.length-1)) {
            System.out.println("不用比對path"
                                +"  pathLevel= "+ (pathName.length-1)
                                +"  folderTreeMaxLevel= "+folderTreeMaxLevel);
        } else
        {
            int folderTreeIndex = 1;// folderTreeIndex=0:folder根目錄
            for (int pathLevel = 1; pathLevel < pathName.length; pathLevel++)
            {
                System.out.println("pathLevel= " + pathLevel);
                String pathElement = pathName[pathLevel];
                int comparedLevel;
                boolean isConformity=false;
                do
                {
                    FileTreeBean compared = folderTree.get(folderTreeIndex);
                 // 比對folderName
                    if(pathElement.equals(compared.getFileName())) {
                       System.out.println("hello world");
                       isConformity = true;
                    }
                    folderTreeIndex++;
                    comparedLevel = compared.getFileLevel();
                } while (comparedLevel == pathLevel && folderTreeIndex < folderTreeSize-1);
                if(!isConformity) {
                    System.out.println("找不到符合的Folder");
                    break;
                }
            }
        }
	}

}
