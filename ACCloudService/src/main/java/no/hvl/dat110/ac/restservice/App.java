package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description
			
		
		
		//Spør etter tilgang
		post("/accessdevice/log/", (req, res) -> {
			Gson gson = new Gson();
        	
			AccessMessage s = gson.fromJson(req.body(), AccessMessage.class);
			int id = accesslog.add(s.getMessage());
			return gson.toJson(accesslog.get(id));
		});
		
		// Hent logg-en
		get("/accessdevice/log/", (req, res) -> {
			Gson gson = new Gson();

		
			return accesslog.toJson();
			
		});
			
		// Hent logg-en til et spesifikk id
		get("/accessdevice/log/:id", (req, res) -> {
			
			//Henter id
			Gson gson = new Gson();
			Integer id = Integer.parseInt(req.params(":id"));
			
			return gson.toJson(accesslog.get(id));
			
		});
				
		// Endre kode
		put("/accessdevice/code", (req, res) -> {
			Gson gson = new Gson();
        	
			AccessCode ac = gson.fromJson(req.body(), AccessCode.class);
			accesscode.setAccesscode(ac.getAccesscode());
			
			return gson.toJson("Koden er endret til: " + accesscode.toString());
		});
		
		
		//Hent kode
		get("/accessdevice/code", (req, res) -> {
			Gson gson = new Gson();
			return gson.toJson(accesscode);
		});
		
		//Slett log
		delete("/accessdevice/log", (req, res) -> {
			accesslog.clear();
			
			return accesslog.toJson();
					
		});
    }
    
}
