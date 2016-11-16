package dao;



import javax.annotation.Resource;

import model.activation_code;
import model.advertisement;
import model.seller;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
@Repository("AdDaoImp")
public class AdDaoImp implements AdDao{
	
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	private Session session;
	
	private Session getSession()
	{
		 
		if (session == null) {
			
			session = sessionFactory.openSession();
		} else {
			session=sessionFactory.getCurrentSession();
		}
		return session;
	}
//	private SessionFactory sessionFactory;
//    private Session session;
//    private static ServiceRegistry serviceRegistry; 
//    public static SessionFactory createSessionFactory(SessionFactory sessionFactory)throws HibernateException{  
//        Configuration configuration = new Configuration();  
//        configuration.configure("config/hibernate.cfg.xml");  
//        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();          
//        sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
//        return sessionFactory;  
//    }  
	
	
	
//	private Session getSession()
//	{
//		sessionFactory = createSessionFactory(sessionFactory);
//		if (session == null) {
//			
//			session = sessionFactory.openSession();
//		} else {
//			session = sessionFactory.getCurrentSession();
//		}
//		return session;
//	}
//	
	
	
	public void addAd(model.seller seller,String adtitle,String adimage){//为什么这里要用model.  ???
		
		session=getSession(); 
		session.beginTransaction();
		
		
		advertisement ad = new advertisement();
		ad.setSeller_id(seller.getSeller_id());
		ad.setAdvertisement_Title(adtitle);
		ad.setAdvertisement_Image(adimage);
		
		session.save(ad);
		session.flush();
		
		session.getTransaction().commit();
		session.close();
		if(session==null)System.out.println("qaq");
		
		
     
	}
	
	public advertisement checkAd(String adtitle){//为什么这里要用model.  ???
		String hql = "from advertisement where Advertisement_Title= :title";//ad要大写吗
		Query query = getSession().createQuery(hql);
		query.setString("title", adtitle);
		
		advertisement ad = (advertisement) query.uniqueResult();
		
		return ad;
	}

}
