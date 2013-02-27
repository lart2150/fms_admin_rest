package com.lart2150.fm11.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.filemaker.adminserver.AdminServerException;
import com.filemaker.adminserver.dbs.DBSConnectionException;
import com.filemaker.adminserver.dbs.DBSManager;
import com.lart2150.fm11.rest.elements.*;

@Path("/clients")
public class Clients {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getClients() {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		return FMClient.getJson(dbsManager.getClientList()).toString();
	}
	
	@GET
	@Path("client/{clientid: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFile(@PathParam("clientid") String clientid) {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		
		return FMClient.getJson(dbsManager.getClientById(new Integer(clientid))).toString();
	}
}
