package main.swamy.webesrvices.jaxws.rpc;

import javax.xml.ws.Endpoint;

public class HelloWorldPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:7779/ws/hello", new HelloWorldImpl());

	}

}
