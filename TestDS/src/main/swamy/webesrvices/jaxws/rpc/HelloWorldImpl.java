package main.swamy.webesrvices.jaxws.rpc;

import javax.jws.WebService;

@WebService (endpointInterface="main.swamy.webesrvices.jaxws.rpc.HelloWorld")

public class HelloWorldImpl implements HelloWorld {

	@Override
	public String getHelloWorldAsString(String name) {
		
		return "JAX-WS:"+name;
	}

}
