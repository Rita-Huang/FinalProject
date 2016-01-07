package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;
import com.iii.twentywork.model.daointerface.ShareFileDAO;
import com.iii.twentywork.model.daointerface.TeamUserDAO;

@Component(value = "teamUserDAO")
public class TeamUserDAOHibernate implements TeamUserDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("TeamUserDAOHibernate setSessionFactory");
		
	}
	
	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("TeamUserDAOHibernate getSession");
		return session;
	}
	


	@Override
    public TeamUserBean insert(TeamUserBean userBean) {
//		TeamUserIdBean pk = (TeamUserIdBean) 
				getSession().save(userBean);
//       System.out.println("ShareFileDAOHibernate--insert--pk="+pk);
       return null;//select(pk);//;
	}

	@Override
    public TeamUserBean select(TeamUserIdBean id) 
	{//testing#1
	    TeamUserBean bean = (TeamUserBean) getSession().get(TeamUserBean.class, id);
		System.out.println("TeamUserDAOHibernate  select結束");
		return bean;
	}

	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
        
        //testing#1
//	    TeamUserIdBean teamUserId = new TeamUserIdBean(100,200);
//	    TeamUserDAO dao = (TeamUserDAO) context.getBean("teamUserDAO");
//	    TeamUserBean bean= dao.select(teamUserId);
//	    System.out.println(bean);
	    
	  //testing#2
        ShareFileDAO dao = (ShareFileDAO) context.getBean("shareFileDAO");
        TeamUserDAO teamdao = (TeamUserDAO) context.getBean("teamUserDAO");
        
        ShareFileBean shareFileBean =  dao.selectByFileId(908);
//105 201
        TeamUserBean teamUserBean = new TeamUserBean();
        teamUserBean.setId(new TeamUserIdBean(105,201));
        teamUserBean.setTeam(shareFileBean.getTeamBean());
        teamUserBean.setUsers(shareFileBean.getUserBean());
        teamUserBean.setActiveDate(new java.util.Date());
        teamUserBean.setRights(1);
        System.out.println(teamUserBean);
        TeamUserBean result = teamdao.insert(teamUserBean);
        System.out.println(result);
        
//	    TeamUserBean bean= dao.select(teamUserId);
	    
//	    TeamUserIdBean teamUserId2 = new TeamUserIdBean(108,202);
//	    TeamUserBean bean2= dao.select(teamUserId2);
//	    
//	    
////	    teamUserBean.setActiveDate(activeDate);
//	    TeamUserIdBean teamUserId3 = teamUserId2;
//	    teamUserId3.setTeamId(200);
//	    teamUserBean.setId(teamUserId3);
//	    System.out.println(teamUserBean);
//	    
//	    TeamUserBean result = dao.insert(teamUserBean);
	    
	    sessionFactory.getCurrentSession().getTransaction().commit();
	    
	}

}
