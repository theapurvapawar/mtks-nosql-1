import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;

/**
 * @author Apurva Pawar
 */

/*
 * Basic Get and Put for Strings works
 * Requires voldemort-1.1.7.jar
 * Requires all jar from voldemort library
 * Requires voldemort Server to be started from terminal  
 */


public class VoldemortClient {
	
	private StoreClient<String, String> client;
	
	VoldemortClient(String IpAndPort, String StoreName)
	{
		String bootstrapUrl = IpAndPort;
        StoreClientFactory factory = new SocketStoreClientFactory(new ClientConfig().setBootstrapUrls(bootstrapUrl));

        client = factory.getStoreClient(StoreName);			
	}
	
	String getValueOf(String key)
	{
		return client.getValue(key);	
	}

	void putValueFor(String key, String value)	{		
		    
        client.put(key, value);
	}	
	
	
    public static void main(String[] args) {   	
    	

    	VoldemortClient KVpair = new VoldemortClient("tcp://localhost:6666","test");    	

        String key = "this_is_key2",value="this_is_value";
        
        KVpair.putValueFor(key, value);        
        
    	// get the value
        value = KVpair.getValueOf(key);//cannot get value if key does not exist
        System.out.println("Key-Value-Pair::" + key + ":" + value);        

        // update the value
        KVpair.putValueFor(key, "this_is_new_value");
        
        value = KVpair.getValueOf(key);//cannot get value if key does not exist
        System.out.println("Key-Value-Pair::" + key + ":" + value);
    }

}