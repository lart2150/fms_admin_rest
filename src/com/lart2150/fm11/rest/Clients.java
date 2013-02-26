package com.lart2150.fm11.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.filemaker.adminserver.AdminServerException;
import com.filemaker.adminserver.dbs.DBSConnectionException;
import com.filemaker.adminserver.dbs.DBSManager;
import com.filemaker.fmslib.datatype.*;

@Path("/clients")
public class Clients {
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getClients() {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		List<Client> clients = dbsManager.getClientList();
		String clientText = "";
		for (Client client : clients) {
			clientText += client.toString() + "\n";
		}
		return clientText;
	}
}
