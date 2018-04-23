package com.google.apigee.edge.xmlrpc;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;

import com.apigee.flow.execution.ExecutionContext;
import com.apigee.flow.execution.ExecutionResult;
import com.apigee.flow.execution.spi.Execution;
import com.apigee.flow.message.MessageContext;

public class EdgeXMLRPC implements Execution {

	@Override
	public ExecutionResult execute(MessageContext messageContext, ExecutionContext executionContext) {		
		try {
			int x, y;

			x = Integer.parseInt(messageContext.getVariable("x"));
			y = Integer.parseInt(messageContext.getVariable("y"));
			
			XmlRpcClient server = new XmlRpcClient("https://edge-xmlrpc.appspot.com/RPC2");
			Vector params = new Vector();
			params.addElement(new Integer(x));
			params.addElement(new Integer(y));

			String result = (String) server.execute("qService.request", params);
			SimpleRequest s = (SimpleRequest) Utils.fromString(result);

			int sum = s.premium;
			System.out.println("The sum is: " + s.premium + ", for firstName " + s.firstName);
			messageContext.setVariable("firstName", s.firstName);
			messageContext.setVariable("lastName", s.lastName);
			messageContext.setVariable("country", s.country);
			messageContext.setVariable("memberId", s.memberId);
			messageContext.setVariable("premium", s.premium);

			return ExecutionResult.SUCCESS;

		} catch (Exception exception) {
			exception.printStackTrace();
			messageContext.setVariable("exception", exception);
			System.err.println("JavaClient: " + exception);
			return ExecutionResult.ABORT;
		}
	}

}
