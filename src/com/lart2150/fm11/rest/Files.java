package com.lart2150.fm11.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.filemaker.adminserver.*;
import com.filemaker.adminserver.dbs.*;
import com.lart2150.fm11.rest.elements.FMFile;

@Path("/files")
public class Files {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFiles() {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		/*List<Database> files = dbsManager.getFileList();
		String filesText = "";
		for (Database file : files) {
			filesText += file.toString() + "\n";
		}*/
		return FMFile.getJson(dbsManager.getFileList()).toString();
	}
	@GET
	@Path("file/{fileid: [0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFile(@PathParam("fileid") String fileid) {
		DBSManager dbsManager;
		try {
			dbsManager = ServerSingleton.getDBSManager();
		} catch (AdminServerException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		} catch (DBSConnectionException e) {
			throw new RuntimeException("FM Exception: " + e.getMessage());
		}
		
		return FMFile.getJson(dbsManager.getFileById(new Integer(fileid))).toString();
	}
}
