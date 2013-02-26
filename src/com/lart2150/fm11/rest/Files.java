package com.lart2150.fm11.rest;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.filemaker.adminserver.*;
import com.filemaker.adminserver.dbs.*;
import com.filemaker.fmslib.datatype.*;
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
}
