package register.model.dao;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;
import register.model.TeamUserBean;
import register.model.TeamUserId;

public class TeamUserDAOHibernate  {

	private Session session;
	public TeamUserDAOHibernate(Session session) {
        this.session = session;
    }
	
	public Session getSession() {
		return session;
	}

	public TeamUserDAOHibernate() {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	
	public TeamUserBean select(TeamUserId id) {
		//查詢 Select
		//get(): 資料不存在回傳null
		return (TeamUserBean)getSession().get(TeamUserBean.class,id);
		
		//load() 方法同get():資料不存在則產生Runtime Exception
	}



	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

//			select(int id)
			TeamUserDAOHibernate dao = new TeamUserDAOHibernate(session);
			TeamUserId id = new TeamUserId(5,1);
			TeamUserBean bean = dao.select(id); 
			System.out.println(bean);
			
			
			//List<ProductBean> select()
//			ProductDAO dao = new ProductDAOHibernate(session);
//			List<ProductBean> beans = dao.select();
//			System.out.println(beans);
			
			//ProductBean insert(ProductBean bean)
//			ProductBean bean = new ProductBean();
//			bean.setId(12);
//			bean.setName("test");
//			ProductDAO dao3 = new ProductDAOHibernate(session);
//			ProductBean result = dao3.insert(bean);
			
			//ProductBean update(....)
//			ProductDAO dao = new ProductDAOHibernate(session);
//			ProductBean result = dao.update("test",200,ProductBean.convertDate("2015-05-05"),200,12);
//			System.out.println(result);
			
			//delete(int id)
//			ProductDAO dao = new ProductDAOHibernate(session);
//			System.out.println(dao.delete(12));
			
			session.getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
