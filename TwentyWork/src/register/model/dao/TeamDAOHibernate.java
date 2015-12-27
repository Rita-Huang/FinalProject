package register.model.dao;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;
import register.model.TeamBean;

public class TeamDAOHibernate  {

	private Session session;
	public TeamDAOHibernate(Session session) {
        this.session = session;
    }
	
	public Session getSession() {
		return session;
	}

	public TeamDAOHibernate() {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	
	public TeamBean select(int id) {
		//查詢 Select
		//get(): 資料不存在回傳null
		return (TeamBean)getSession().get(TeamBean.class,id);
		
		//load() 方法同get():資料不存在則產生Runtime Exception
	}



	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

//			select(int id)
			TeamDAOHibernate dao = new TeamDAOHibernate(session);
			TeamBean bean = dao.select(1); 
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
