package com.lart2150.fm11.rest.elements;

import java.util.HashMap;
import java.util.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.filemaker.fmslib.datatype.*;

public class FMClientConnection {
	protected static Map<String,Object> getJsonMap(ClientDatabase client) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "client" );
		map.put("fileID", client.getDatabaseID() );
		map.put("accountName", client.getAccountName() );
		map.put("privlageSet", client.getPrivSetName());
		map.put("connnectTime", client.getConnectTime());
		return map;
	}
	public static JSONObject getJson(ClientDatabase client) {
		return JSONObject.fromObject( getJsonMap(client) );
	}
	public static JSONArray getJson(HashMap<Integer, ClientDatabase>  clients) {
		Vector<JSONObject> jsonclients = new Vector<JSONObject>(clients.size()); 
		
		for (ClientDatabase client : clients.values()) {
			jsonclients.add(JSONObject.fromObject( getJsonMap(client) ));
		}
		return JSONArray.fromObject(jsonclients);
	}

}
