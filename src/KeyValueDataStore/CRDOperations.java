package KeyValueDataStore;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CRDOperations {
	String filePath="";
	public CRDOperations(String path)
	{
		this.filePath=path;
	}
	HashMap<String,Databean> hm;
	FileInputStream fileip;
	ObjectInputStream objectip;
	FileOutputStream fileop;
	ObjectOutputStream objectop;
	public synchronized String create(String key,JSONObject value,int ttl)
	{
		if(exists(key))
		{
			return "key already exists";
		}
		else if(key.length()>32)
		{
			return "key size is greater than 32 chars";
		}
		else
		{
		Databean ds=new Databean();
		ds.setKey(key);
		ds.setValue(value);
		ds.setTtl(ttl);
		ds.setStarttime((new Date().getTime()));
//		System.out.println(ds.getKey()+" "+ds.getStarttime()+" "+ds.ttl+" "+ds.getValue());
		try {
			File file = new File(filePath);
			if (file.exists()) {
				if(file.length()>1024*1024*1024)
					return "file size exceeded 1GB";
				fileip = new FileInputStream(file);
				objectip = new ObjectInputStream(fileip);
				hm = (HashMap<String, Databean>) objectip.readObject();
				hm.put(ds.getKey(), ds);
				fileop = new FileOutputStream(file);
				objectop = new ObjectOutputStream(fileop);
				objectop.writeObject(hm);
				fileip.close();
				objectip.close();
				fileop.close();
				objectop.close();
				fileip = new FileInputStream(file);
				objectip = new ObjectInputStream(fileip);
				hm = (HashMap<String, Databean>) objectip.readObject();
				return "successfully created";
			} else {
				hm = new HashMap<String, Databean>();
				hm.put(ds.getKey(), ds);
				fileop = new FileOutputStream(file);
				objectop = new ObjectOutputStream(fileop);
				objectop.writeObject(hm);
				fileop.close();
				objectop.close();
				return "successfully created";
			}
		}
		catch(Exception e) {
			System.out.println(e);
			return "failed in creation";
		}
		
		}
	}
	public synchronized String read(String key)
	{
//		System.out.println(key);
		try {
			File file = new File(filePath);
			if (file.exists() && exists(key)) {
				fileip = new FileInputStream(file);
				objectip = new ObjectInputStream(fileip);
				hm = (HashMap<String, Databean>) objectip.readObject();				
				fileip.close();
				objectip.close();
				return (key+" "+hm.get(key).getValue());
			} 
			else
			{
				return "Key not found";
			}
		} catch (Exception exception) {
			return "Key not found";
		}
	}
	public synchronized String delete(String key)
	{
		if(exists(key))
		{
		try {
			File file = new File(filePath);
			if (file.exists()) {
				fileip = new FileInputStream(file);
				objectip = new ObjectInputStream(fileip);
				hm = (HashMap<String, Databean>) objectip.readObject();
				fileip.close();
				objectip.close();
				hm.remove(key);
				fileop = new FileOutputStream(file);
				objectop = new ObjectOutputStream(fileop);
				objectop.writeObject(hm);
				fileop.close();
				objectop.close();
				return "sucessfully deleted";
			}
		} catch (Exception exception) {return "deletion failed";}
		}
		else
		{
				return "Key not found";
		}
		return "";
	}
	public synchronized Boolean exists(String key)
	{
		Boolean existvar=false;
		try {
			File file = new File(filePath);
			if (file.exists()) {
				fileip = new FileInputStream(file);
				objectip = new ObjectInputStream(fileip);
				hm = (HashMap<String, Databean>) objectip.readObject();
				fileip.close();
				objectip.close();
//				System.out.println(hm);
				if(hm.containsKey(key))
					existvar=true;
				fileop = new FileOutputStream(file);
				objectop = new ObjectOutputStream(fileop);
				objectop.writeObject(hm);
				fileop.close();
				objectop.close();
			}
			if (existvar) {
				Databean ds = hm.get(key);
//				System.out.println(ds.getKey());
				long now = new Date().getTime();
				int ttlvalue=ds.getTtl();
				long diff=(now - ds.getStarttime());
				if (ttlvalue> 0 && diff >= (ttlvalue*1000)) {
					hm.remove(key);
					fileop = new FileOutputStream(file);
					objectop = new ObjectOutputStream(fileop);
					objectop.writeObject(hm);
					fileop.close();
					objectop.close();
					existvar = false;
				}
			}
		} catch (Exception exception) {}
		return existvar;
	}
}