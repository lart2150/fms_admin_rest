package com.lart2150.fm11.rest.elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.filemaker.fmslib.datatype.*;

public class FMClient {
	Client myClient;
	public FMClient(Client client) {
		this.myClient = client;
	}
	
	public JSONObject getJson() {
		return FMClient.getJson(myClient);
	}

	protected static Map<String,Object> getJsonMap(Client client) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "client" );
		map.put("clientID", client.getID() );
		map.put("databaseConnections", FMClientConnection.getJson(client.getClientDatabaseMap()));
		map.put("connectionID", client.getConnectionId());
		map.put("sessionID", client.getSessionId());
		map.put("transactionTime", client.getTransactionTime());
		map.put("adminComponentID", client.getAdminComponentID());
		map.put("isAdmin", client.isAdmin());
		map.put("clientOS", client.getOS().toString());
		map.put("sessionName", client.getSessionName());
		map.put("clientName", client.getClientName());
		map.put("installCode", client.getInstallCode());
		map.put("clientIP", client.getClientAddress());
		map.put("macAddress", client.getMACAddress());
		map.put("version", client.getAppVersion());
		map.put("language", client.getAppLanguage());
		map.put("privilageExtension", client.getPrivilegeExtension());
		map.put("connectionTime", client.getConnectionTime());
		map.put("hasStats", client.isHasStats());
		return map;
	}
	public static JSONObject getJson(Client client) {
		return JSONObject.fromObject( getJsonMap(client) );
	}
	public static JSONArray getJson(List<Client> clients) {
		Vector<JSONObject> jsonclients = new Vector<JSONObject>(clients.size()); 
		for (Client client : clients) {
			jsonclients.add(JSONObject.fromObject( getJsonMap(client) ));
		}
		return JSONArray.fromObject(jsonclients);
	}
}
