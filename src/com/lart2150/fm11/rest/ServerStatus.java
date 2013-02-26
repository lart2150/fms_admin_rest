package com.lart2150.fm11.rest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.filemaker.adminserver.*;
import com.filemaker.adminserver.dbs.*;

@Path("/server-status")
public class ServerStatus {
	/*
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public ServerStatus(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}*/
	
	
	// retuns the number of connected clients
	// Use /rest/server-status/client-count
	// to get the total number of records
	@GET
	@Path("client-count")
	@Produces(MediaType.TEXT_HTML)
	public String getClientCount() {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		return String.valueOf(dbsManager.getClientList().size());
	}
	
	// retuns the number of files
	// Use /rest/server-status/client-count
	// to get the total number of records
	@GET
	@Path("file-count")
	@Produces(MediaType.TEXT_HTML)
	public String getfileCount() {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		return String.valueOf(dbsManager.getFileList().size());
	}
	
	
	// retuns if the db server is running
	// Use /rest/server-status/client-count
	// to get the total number of records
	@GET
	@Path("db-running")
	@Produces(MediaType.TEXT_HTML)
	public String getDBRunning() {
		AdminServerManager admin;
		try {
			admin = ServerSingleton.getAdminServerManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		return String.valueOf(admin.isDBSAvailable());
	}
}
