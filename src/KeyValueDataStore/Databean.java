package KeyValueDataStore;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class Databean implements Serializable{
	private static final long serialVersionUID = 1L;
	public String key;
	public JSONObject value;
	public long starttime;
	public int ttl;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public JSONObject getValue() {
		return value;
	}
	public void setValue(JSONObject value) {
		this.value = value;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public int getTtl() {
		return ttl;
	}
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

}
