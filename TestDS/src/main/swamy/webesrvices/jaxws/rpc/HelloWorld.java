package main.swamy.webesrvices.jaxws.rpc;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

//service endpointinterface

@WebService

@SOAPBinding(style = Style.RPC)

public interface HelloWorld {
	
	@WebMethod String getHelloWorldAsString(String name);
	
}
