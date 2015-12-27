package register.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;
import register.model.MememberBean;

public class MememberDAOHibernate  {

	private Session session;
	public MememberDAOHibernate(Session session) {
        this.session = session;
    }
	
	public Session getSession() {
		return session;
	}

	public MememberDAOHibernate() {
	    session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	
	public MememberBean select(int id) {
		//查詢 Select
		//get(): 資料不存在回傳null
		return (MememberBean)getSession().get(MememberBean.class,id);
		
		//load() 方法同get():資料不存在則產生Runtime Exception
	}



	public static void main(String[] args) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

//			select(int id)
			MememberDAOHibernate dao = new MememberDAOHibernate(session);
			MememberBean bean = dao.select(5); 
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
