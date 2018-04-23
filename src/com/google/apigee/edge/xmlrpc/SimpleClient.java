package com.google.apigee.edge.xmlrpc;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;

public class SimpleClient {
	public static void main(String[] args) {
		try {
			XmlRpcClient server = new XmlRpcClient("https://edge-xmlrpc.appspot.com/RPC2");
			Vector params = new Vector();
			params.addElement(new Integer(17));
			params.addElement(new Integer(13));

			String result = (String) server.execute("qService.request", params);
			SimpleRequest s = (SimpleRequest) Utils.fromString(result);

			int sum = s.premium;
			System.out.println("The sum is: " + s.premium + ", for firstName " + s.firstName);

		} catch (Exception exception) {
			exception.printStackTrace();
			System.err.println("JavaClient: " + exception);
		}
	}

}
