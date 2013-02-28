import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import voldemort.client.ClientConfig;
import voldemort.client.SocketStoreClientFactory;
import voldemort.client.StoreClient;
import voldemort.client.StoreClientFactory;
import voldemort.client.protocol.admin.AdminClient;
import voldemort.client.protocol.admin.AdminClientConfig;
import voldemort.utils.ByteArray;

/**
 * @author Apurva Pawar
 */

/*
 * Basic Get All Keys with values works
 * Requires voldemort-1.1.7.jar
 * Requires all jar from voldemort library
 * Requires voldemort Server to be started from terminal  
 */

public class VoldyAdminClient {	
	
	StoreClient<String, String> InitVoldyClient(String IpAndPort, String StoreName)
	{
		
	    int maxThreads = 300;
	    ClientConfig clientConfig = new ClientConfig();
	    clientConfig.setMaxThreads(maxThreads);
	    clientConfig.setMaxConnectionsPerNode(maxThreads);
	    clientConfig.setConnectionTimeout(500, TimeUnit.MILLISECONDS);
	    clientConfig.setBootstrapUrls(IpAndPort);

	    StoreClientFactory factory = new SocketStoreClientFactory(clientConfig);
	    return factory.getStoreClient(StoreName);			
	}
	
	
	Iterator<ByteArray> GetAllKeys(String IpAndPort, String StoreName, int nodeId)
	{
		    
	    List<Integer> partitionList = new ArrayList<Integer>();
	    partitionList.add(0);//manually required to include partition list
	    partitionList.add(1);
	    AdminClient adminClient = new AdminClient(IpAndPort, new AdminClientConfig());
	    return adminClient.fetchKeys(nodeId, StoreName, partitionList, null, false);			
	}
	
	public static void main(String [] args) {

	    String IpAndPort = "tcp://localhost:6666";
	    String StoreName = "test";
	    int nodeId = 0;//manually required to enter node id
	    
	    //my assumption is that admin client will get all keys from mentioned node only
		
		
		VoldyAdminClient admin = new VoldyAdminClient();
		StoreClient<String, String> client = admin.InitVoldyClient(IpAndPort, StoreName);
	    Iterator<ByteArray> iterator = admin.GetAllKeys(IpAndPort, StoreName, nodeId);		
	    


	    String key = null;
	    String value = null;
	    while (iterator.hasNext()) {
	        key = new String(iterator.next().get());
	        value = client.getValue(key);
	        System.out.println("Key-Value-Pair::" + key + ":" + value);
	    }

	}

}
