import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.awt.desktop.*;

//API Key for Hetrix Tools: a1a37d5b2f9f70c2958d198d48f3f588

//API call https://api.hetrixtools.com/v2/<API_TOKEN>/blacklist-check/ipv4/<IP_ADDRESS>/


public class mainWeb {

	
	private static String[] HetrixObject =
			
			{"status",							//0 
			"api_calls_left", 					//1
			"blacklist_check_credits_left",		//2
			"blacklisted_count",				//3
			"blacklisted_on",					//4
			"links"								//5
			};
	
	
	
	//Getting the URI to invoke the request on
	private static String getUri() {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the IP to scan: ");
		String ip = scan.nextLine();
		
		return "https://api.hetrixtools.com/v2/a1a37d5b2f9f70c2958d198d48f3f588/blacklist-check/ipv4/" + ip + "/";
		
	}
	
	
	// Invoking the web request, returning the response in JSON:
	public static String InvokeWebRequest() throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.header("accept", "application.json")
				.uri(URI.create(getUri()))
				.build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		return response.body();
	}
	

	// Parse the JSON file NEED:
	//JSON FILE AS STRING
	public static JsonNode MappedObjects(String JSONfile) throws JsonMappingException, JsonProcessingException {
		
		ObjectMapper map = new ObjectMapper();
		return map.readTree(JSONfile);
		
	}
	
	
	//Select Object to display NEED: 
	//OBJECT NAME:
	//JSON NODE:
	private static void getObject(String object, JsonNode node) {
		
		System.out.println(node.get(object));
		
	}
	
	
	
	
	
	public static void main(String []args) throws IOException, InterruptedException {
		
		getObject(HetrixObject[4], MappedObjects(InvokeWebRequest()));
		
	}
	
}
