<h1 align="center">File Based Key Value Data Store</h1>
<h3 align="center">A Java Application for Key-Value Data Store that performs basic CRD Operations</h3>
<h3>Methods : </h3>
<ol>
<li>
<h4>public synchronized String create(String key,JSONObject value,int ttl):</h4>
     <ul>
     <li>Has three arguments-key, value and Time to Live</li>
     <li>This methods checks if the key already exists or not . If exists return "key already exists" , if the key size is greather than 32chars return ""key size is greater than 32 chars" else stores the key value in the file and return "successfully created"</li>
     </ul>
</li>
<li>
<h4>public synchronized String read(String key):</h4>
     <ul>
     <li>Has one argument-key</li>
     <li>This methods checks if the key exists or not . If not exists return "Key not found" else returns the value stored in the file for the given key</li></ul>
</li>
<li>
<h4>public synchronized String delete(String key):</h4>
     <ul>
     <li>Has one argument-key</li>
     <li>This methods checks if the key exists or not . If not exists return "Key not found" else deletes the value with the given key</li></ul>
</li>
<li>
<h4>public synchronized Boolean exists(String key):</h4>
     <ul>
     <li>Has one argument-key</li>
     <li>This methods checks if the key exists or not . If key exists return true , else return false</li></ul>
</li>
</ol>
