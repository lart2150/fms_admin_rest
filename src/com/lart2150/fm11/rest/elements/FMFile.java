package com.lart2150.fm11.rest.elements;

import java.util.*;

import javax.xml.bind.annotation.XmlRootElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.filemaker.fmslib.datatype.*;

@XmlRootElement
public class FMFile {
	Database myFile;
	public FMFile(Database file) {
		this.myFile = file;
	}
	
	public JSONObject getJson() {
		return FMFile.getJson(myFile);
	}

	protected static Map<String,Object> getJsonMap(Database file) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "file" );
		map.put("fileID", file.getID() );
		map.put("path", file.getFilePath() );
		map.put("status", file.getStatus());
		map.put("guestAllowed", file.isGuestAccountEnabled());
		map.put("connectedClients", file.getClientSet().toArray());
		map.put("connectedClientCount", file.getNumberOfClients());
		map.put("privlageSet", file.getExtendedPrivString());
		map.put("connectedClients", JSONArray.fromObject(file.getClientSet()));
		map.put("size", file.getSize());
		return map;
	}
	public static JSONObject getJson(Database file) {
		return JSONObject.fromObject( getJsonMap(file) );
	}
	public static JSONArray getJson(List<Database> files) {
		Vector<JSONObject> jsonFiles = new Vector<JSONObject>(files.size()); 
		for (Database file : files) {
			jsonFiles.add(JSONObject.fromObject( getJsonMap(file) ));
		}
		return JSONArray.fromObject(jsonFiles);
	}
}
