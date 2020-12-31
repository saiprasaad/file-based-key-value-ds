package KeyValueDataStore;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import java.util.*;
public class Main {
	public static void main(String args[]) throws IOException, ParseException, InterruptedException
	{
		Scanner scan=new Scanner(System.in);
		int n,i;
		String key="";
		String name="";
		String filepath="";
		String gender="";
		int age;
		int ttlvalue;
		System.out.println("If you want to specify Filepath give the location else give NA");
		filepath=scan.nextLine();
		if(filepath.equals("NA"))
		filepath="C:\\Java\\default\\data";
		else
		filepath=filepath+"\\data";
		CRDOperations store=new CRDOperations(filepath);
		JSONObject json = new JSONObject();
//										**USER INPUT **
//		System.out.println("Enter the no of users to store");
//		n=scan.nextInt(); // no of users
//		for(i=0;i<n;i++)
//		{
//			scan.nextLine();
//			System.out.println("Enter the Key");
//			key=scan.nextLine();
//			if(store.exists(key))
//			{
//				System.out.println("Key Already Exists");
//			}
//			else
//			{
//			System.out.println("Enter the Name");
//			name=scan.nextLine();
//			System.out.println("Enter the gender");
//			gender=scan.nextLine();
//			System.out.println("Enter the age");
//			age=scan.nextInt();
//			System.out.println("If you want to specify TTL give the value in seconds else give -1");
//			ttlvalue=scan.nextInt();
//			json.put("name", name);
//			json.put("gender", gender);
//			json.put("age", age);
//			store.createfn(key, json,ttlvalue);
//			json.clear();
//			}
//		}
		json.put("name", "saiprasaad");
		json.put("gender","male");
		json.put("age", 20);
		store.createfn("sai",json,-1); //creates a new entry with the given values in the file
		json.clear();
		json.put("name", "user");
		json.put("gender","male");
		json.put("age", 21);
		store.createfn("user",json,2); //creates a new entry with the given values in the file
		System.out.println(store.read("user"));//reads the user key values
		Thread.sleep(5000); //sleeps for 5 seconds
		System.out.println(store.read("user"));//key not found as it gets deleted after specified ttl
		System.out.println(store.read("sai"));//reads the user key values
		System.out.println(store.read("xyz"));//key not found
		System.out.println(store.delete("sai"));//values with key sai gets deleted
		System.out.println(store.delete("user"));//key not found
	}
}
	
