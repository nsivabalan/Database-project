



import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;


public class Common {
	
	//RMQ attributes
	public static String RMQServer = "localhost";
	
	//Hbase attributes
	public static String HBASEServer = "localhost";
	public static String HBASEPort = "9000";
	public static String tableName ="scores";
	public static String[] familys = new String[]{"course"};
	public static String familyName = "course";

	//Static Functions
	public static <T> String Serialize(T message)
	{
		Gson gson = new Gson();
		return gson.toJson(message, message.getClass());
	}
	
	//TODO: Use this function instead of local deserialization function in RMQReceiver. 
	@SuppressWarnings("rawtypes")
	public static <T> T Deserialize(String json, Class className)
	{
		Gson gson = new Gson();
		return (T) gson.fromJson(json, className);
	}
	
	//Translate Node Address from Node Id.
/*	public static String GetNodeAddress(String nodeId){
		return NodeMap.get(nodeId);
	}
	
	//Add an entry to Node Id to Address Translation Map.
	public static void AddNodeAddress(String nodeId, String nodeAddress){
		NodeMap.put(nodeId, nodeAddress);
	}
	*/
	
	public static <T> MessageWrapper CreateMessageWrapper(T message){
		return new MessageWrapper(Common.Serialize(message), message.getClass());
	}
	
	public static Class GetClassfromString(String s) throws ClassNotFoundException
	{
		Class<?> cls = Class.forName(s);
		return cls;
	}
	
	/*public static Integer GetQuorumSize()
	{
		return (int) (Math.floor(Common.NoAcceptors/2) + 1);
	}
	*/
	
	public static Timestamp getUpdatedTimestamp(Timestamp original, int sec){
		
		Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        Timestamp later = new Timestamp(cal.getTime().getTime());
        return later;
	}
	//Instance Functions
	
	
}

