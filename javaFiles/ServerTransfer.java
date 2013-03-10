import java.util.Iterator;

import test.VoldyAdminClient;
import test.VoldemortClient;
import voldemort.client.StoreClient;
import voldemort.utils.ByteArray;


/**
 * @author Apurva Pawar
 */

/*
 * imports VoldemortClient and VoldyAdminClient from test package
 * initializes source and sink servers and stores
 * gets all keys from source server store
 * puts it to sink store within a loop
 * works on test backend database
 */


public class ServerTransfer {	
	
	
	public static void main(String [] args) {
		
		int nodeId = 0;
		
		VoldyAdminClient sourceServer = new VoldyAdminClient();
		//enter source IP and port and store name
		StoreClient<String, String> sourceStore = sourceServer.InitVoldyClient("tcp://localhost:6666","backend");
		//enter sink IP and port and store name
		VoldemortClient sinkStore = new VoldemortClient("tcp://192.168.1.4:6666","backend");	
		
		//get all keys from source server store
	    Iterator<ByteArray> allKeys = sourceServer.GetAllKeys("tcp://localhost:6666","backend", nodeId);
	    
	    String key = null;
	    String value = null;
	    while (allKeys.hasNext()) {
	        key = new String(allKeys.next().get());
	        value = sourceStore.getValue(key);
	        System.out.println("Key-Value-Pair::" + key + ":" + value);
	        sinkStore.putValueFor(key, value);//puts key/value in sink store within loop
	    }
	    
	
	}

}
