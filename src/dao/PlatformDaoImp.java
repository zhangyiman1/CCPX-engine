package dao;

import java.util.ArrayList;
import java.util.List;

import dao.PlatformDao;

import javax.annotation.Resource;

import model.Offer;
import model.Request;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import org.hibernate.Query;

import model.Request;

@Repository("PlatformDaoImp")
public class PlatformDaoImp implements PlatformDao {
	// 声明sessionFactory

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session session;

	// 获取当前session的方法
	private Session getSession() {

		if (session == null) {
			session = sessionFactory.openSession();
		} else {
			session = sessionFactory.getCurrentSession();
		}
		return session;
	}

	// OFFER: -OPEN -CLOSED -REMOVED
	// EXCHANGE: -PENDING -CLOSED -REMOVED -DECLINED

	@Override
	public List<Offer> searchExcahnge(Integer sellerFrom, Integer sellerTo, Integer pointsFrom, Integer pointsToMin) {
		List<Offer> list = new ArrayList<Offer>();
		String sql = "from Offer where pointsFrom>= :pointsToMin and pointsToMin<= :pointsFrom "
				+ "and sellerFrom =:sellerTo and sellerTo =:sellerFrom and status= :status";
		Query query = getSession().createQuery(sql);
		query.setInteger("sellerFrom", sellerFrom);
		query.setInteger("sellerTo", sellerTo);
		query.setInteger("pointsFrom", pointsFrom);
		query.setInteger("pointsToMin", pointsToMin);
		query.setString("status", "OPEN");
		list = query.list();
		return list;
	}

	@Override
	public List<Request> showLatestTransaction(Integer sellerFrom, Integer sellerTo) {

		// TODO Auto-generated method stub
		String sql = "from Request where " + "(sellerFrom =? and sellerTo =? " + "and status = 'CLOSED')"
				+ " or (sellerTo =? and sellerFrom =? " + "and status = 'CLOSED')" + " order by updateTime desc";
		Query query = getSession().createQuery(sql);
		query.setMaxResults(5);// cann't write limit 5 in sql
		query.setInteger(0, sellerFrom);
		query.setInteger(1, sellerTo);
		query.setInteger(2, sellerFrom);
		query.setInteger(3, sellerTo);
		System.out.println(query.list());
		List<Request> requests = query.list();
		return requests;
	}

	@Override
	public List<Offer> showRecommendationList(Integer sellerFrom, Integer sellerTo, Integer pointsFrom,
			Integer pointsToMin) {
		List<Offer> list = new ArrayList<Offer>();
		String hql = "from Offer where pointsFrom>= :pointsToMin and pointsToMin<= :pointsFrom "
				+ "and sellerFrom =:sellerTo and sellerTo =:sellerFrom and status= :status";
		Query query = getSession().createQuery(hql);
		query.setInteger("sellerFrom", sellerFrom);
		query.setInteger("sellerTo", sellerTo);
		query.setInteger("pointsFrom", pointsFrom);
		query.setInteger("pointsToMin", pointsToMin);
		query.setString("status", "OPEN");
		Offer offer = (Offer) query.uniqueResult();
		list.add(offer);
		return list;
	}

	@Override
	public Boolean removeExchange(Integer request_id, Integer user_from) {
		System.out.println("PlatformDaoImp");
		System.out.println("request_id: " + request_id);
		System.out.println("user_from: " + user_from);

		String hql = "update Request set  status=:status where Rid=:Rid and userFrom=:userFrom";
		Query query = getSession().createQuery(hql);
		query.setInteger("Rid", request_id);
		query.setInteger("userFrom", user_from);
		query.setString("status", "REMOVED");
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("Request_ID " + request_id + " has been removed! Successfully");
		} else {
			System.out.println("Request_ID " + request_id + " has not been removed! Failed");
		}
		return (a > 0 ? true : false);
	}

	@Override
	public Boolean declineExchange(Integer request_id, Integer user_to) {
		System.out.println("PlatformDaoImp");
		System.out.println("request_id: " + request_id);
		System.out.println("user_to: " + user_to);
		// status 2 = Declined
		String hql = "update Request set  status=:status where Rid=:Rid and userTo=:userTo";
		Query query = getSession().createQuery(hql);
		query.setInteger("Rid", request_id);
		query.setInteger("userTo", user_to);
		query.setString("status", "DECLINED");
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("Request_ID " + request_id + " has been declined! Successfully");
		} else {
			System.out.println("Request_ID " + request_id + " has not been declined! Failed");
		}
		return (a > 0 ? true : false);
	}

	@Override
	public Boolean removeOffer(Integer offer_id, Integer user_id) {
		System.out.println("PlatformDaoImp");
		System.out.println("offer_id: " + offer_id);
		System.out.println("user_id: " + user_id);
		// status 3= removed status 1=avalaible 2=done

		String hql = "update Offer set  STATUS=:STATUS where OFFER_ID=:OFFER_ID and USER_ID=:USER_ID";
		Query query = getSession().createQuery(hql);
		query.setInteger("OFFER_ID", offer_id);
		query.setInteger("USER_ID", user_id);
		query.setString("STATUS", "REMOVED");
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("OFFER_ID " + offer_id + " has been removed! Successfully");
		} else {
			System.out.println("OFFER_ID " + offer_id + " has not been removed! Failed");
		}
		return (a > 0 ? true : false);
	}

	@Override
	public Boolean updateOfferStatus(Integer offer_id, Integer user_from) {
		String sql = "update Offer set  STATUS=:STATUS where OFFER_ID=:OFFER_ID and USER_ID=:USER_ID";
		Query query = getSession().createQuery(sql);
		query.setInteger("OFFER_ID", offer_id);
		query.setInteger("USER_ID", user_from);
		query.setString("STATUS", "CLOSED");
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("OFFER_ID " + offer_id + " has been removed! Successfully");
		} else {
			System.out.println("OFFER_ID " + offer_id + " has not been removed! Failed");
		}
		return (a > 0 ? true : false);
	}

	@Override
	public Boolean updateRequestStatus(Integer request_id, Integer user_from) {
		String sql = "update Request set  status=:status where Rid=:Rid and userTo=:userTo";
		Query query = getSession().createQuery(sql);
		query.setInteger("Rid", request_id);
		query.setInteger("userTo", user_from);
		query.setString("status", "CLOSED");
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("Request_ID " + request_id + " has been declined! Successfully");
		} else {
			System.out.println("Request_ID " + request_id + " has not been declined! Failed");
		}
		return (a > 0 ? true : false);
	}

	@Override
	public Boolean acceptRequest(Integer request_id) {
		String hql = "update Request set status=:status where Rid=:Rid";
		Query query = getSession().createQuery(hql);
		query.setString("status", "CLOSED");
		query.setInteger("Rid", request_id);
		int a = query.executeUpdate();
		if (a > 0) {
			System.out.println("request has been accepted");
		} else {
			System.out.println("request acceptting is failed");
		}
		return a > 0 ? true : false;
	}
}
