package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import KeyValueDataStore.CRDOperations;
import KeyValueDataStore.Main;

class testclass {
	
	@Test
	void test_create() {
		String s1="";
		String s2="";
		String s="successfully created";
		String snew="key already exists";
		CRDOperations datastoreobj=new CRDOperations("C:\\Java\\default\\data");
		JSONObject jsonobjtest=new JSONObject();
		jsonobjtest.put("name", "suriyaa v");
		jsonobjtest.put("gender", "female");
		jsonobjtest.put("age", 22);
		s1=datastoreobj.create("suriya",jsonobjtest,-1);
		s2=datastoreobj.create("suriya",jsonobjtest,-1);
		assertEquals(s1,s);
		assertEquals(s2,snew);
		datastoreobj.delete("suriya");	
	}
	
	@Test
	void test_read() {
		String s1="";
		String s="suriya {\"gender\":\"female\",\"name\":\"suriyaa v\",\"age\":22}";
		CRDOperations datastoreobj=new CRDOperations("C:\\Java\\default\\data");
		JSONObject jsonobjtest=new JSONObject();
		jsonobjtest.put("name", "suriyaa v");
		jsonobjtest.put("gender", "female");
		jsonobjtest.put("age", 22);
		s1=datastoreobj.create("suriya",jsonobjtest,-1);
		s1=datastoreobj.read("suriya");
		assertEquals(s1,s);
		datastoreobj.delete("suriya");
		s1=datastoreobj.read("suriya");
		s="Key not found";
		assertEquals(s1,s);
	}
	
	@Test
	void test_delete() {
		CRDOperations datastoreobj=new CRDOperations("C:\\Java\\default\\data");
		JSONObject jsonobjtest=new JSONObject();
		jsonobjtest.put("name", "suriyaa v");
		jsonobjtest.put("gender", "female");
		jsonobjtest.put("age", 22);
		datastoreobj.create("suriya",jsonobjtest,-1);
		String s1="";
		String s="sucessfully deleted";
		s1=datastoreobj.delete("suriya");
		assertEquals(s1,s);
		s="Key not found";
		s1=datastoreobj.delete("suriya");
		assertEquals(s1,s);
	}
	
	@Test
	void test_ttl() throws InterruptedException {
		String s1="";
		String s="suriya {\"gender\":\"female\",\"name\":\"suriyaa v\",\"age\":22}";
		CRDOperations datastoreobj=new CRDOperations("C:\\Java\\default\\data");
		JSONObject jsonobjtest=new JSONObject();
		jsonobjtest.put("name", "suriyaa v");
		jsonobjtest.put("gender", "female");
		jsonobjtest.put("age", 22);
		s1=datastoreobj.create("suriya",jsonobjtest,1);
		s1=datastoreobj.read("suriya");
		assertEquals(s1,s);
		Thread.sleep(2000);
		s1=datastoreobj.read("suriya");
		s="Key not found";
		assertEquals(s1,s);
	}

}
