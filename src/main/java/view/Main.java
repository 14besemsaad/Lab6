package view;

import model.City;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


import dao.Dao;  


public class Main {

	public static void main(String[] args) {
		
		Dao d=new Dao();
		BufferedReader in=null;
		try {
			in = new BufferedReader(new FileReader("C:\\Users\\saad1\\Downloads\\GeoLiteCity-Location.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterable<CSVRecord> records=null;
		try {
			records = CSVFormat.EXCEL.withHeader().parse(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ArrayList<City> city=new ArrayList();
		for(CSVRecord record: records)
		{
			City c=new City();
			c.setlocId(Integer.parseInt(record.get("locId")));
			c.setCountry(record.get("country"));
			c.setRegion(record.get("region"));
			c.setCity(record.get("city"));
			c.setPostalCode(record.get("postalCode"));
			c.setLatitude(record.get("latitude"));
			c.setLongitude(record.get("longitude"));
			if(!(record.size()<=7))
			{
				c.setMetroCode(record.get("metroCode"));
				c.setAreaCode(record.get("areaCode"));
			}
			city.add(c);
		}
				
			
	
		try{
		
		//d.saveCSV(city);
		Scanner s=new Scanner(System.in);
		String name1,name2;
		System.out.println("Give city name:");
		name1=s.nextLine();
		System.out.println("Give city name:");
		name2=s.nextLine();
		System.out.println(d.findDistance(name1,name2));
		} finally
		{
			
			d.finalize();
			System.out.println("Hi");
		}
		
		
	
	}

}
