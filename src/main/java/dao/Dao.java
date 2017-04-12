package dao;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.Transaction;


import model.City;
import model.HibernateUtil;

public class Dao {
	public Session currentSession=null;
	public Dao()
	{
		HibernateUtil.createSessionFactory();
		currentSession=HibernateUtil.getSessionFactory().openSession();
	}
	public void finalize()
	{
		currentSession.close();
	}
	
	public void saveCSV(ArrayList<City> city)
	{
		
		Transaction t=currentSession.beginTransaction();
		for(City c: city)
		{
			currentSession.persist(c);
		}
		
		t.commit();
	}
	public double findDistance(String a, String b)
	{
		Transaction t=currentSession.beginTransaction();
//		Query query1=currentSession.createQuery("select latitude from City where city=:"+a);
//		Query query2=currentSession.createQuery("select longitude from City where city=:"+a);
//		Query query3=currentSession.createQuery("select latitude from City where city=:"+b);
//		Query query4=currentSession.createQuery("select longitude from City where city=:"+b);
//		
////		query1.setParameter("city", a);
////		query2.setParameter("city", a);
////		query3.setParameter("city", b);
////		query4.setParameter("city", b);
//		
//		List<City> list1= query1.getResultList();
//		List<City> list2=query2.getResultList();
//		List<City> list3=query3.getResultList();
//		List<City> list4=query4.getResultList();
//		
//		Iterator<City> iterator1=list1.iterator();
//		Iterator<City> iterator2=list2.iterator();
//		Iterator<City> iterator3=list3.iterator();
//		Iterator<City> iterator4=list4.iterator();
		double lat1=0,lat2=0,lon1=0,lon2=0;
		Query q=currentSession.createQuery("from City");
		List<City> c=q.getResultList();
		
		for(City sc:c)
		{
			if(sc.getCity().equals(a))
			{
				lat1=Double.parseDouble(sc.getLatitude());
				lon1=Double.parseDouble(sc.getLongitude());
			}
			if(sc.getCity().equals(b))
			{
				lat2=Double.parseDouble(sc.getLatitude());
				lon2=Double.parseDouble(sc.getLongitude());
			}
			
		}
		
	
		
		double theta = lon1 - lon2;
		double dist = Math.sin((lat1* Math.PI / 180.0)) * Math.sin((lat2* Math.PI / 180.0)) + Math.cos((lat1)* Math.PI / 180.0) * Math.cos((lat2* Math.PI / 180.0)) * Math.cos((theta* Math.PI / 180.0));
		dist = Math.acos(dist);
		dist =(dist* 180 / Math.PI);
		dist = dist * 60 * 1.1515;
		
		dist = dist * 1.609344;
		

		return (dist);
		
	}
}
