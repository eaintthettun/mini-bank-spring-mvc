package com.mini.bank.business;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/*@Component
public class OnlineBusiness {
	@Autowired
	public RestTemplate restTemplate;
	
	public OnlineBusiness() {
		System.out.println("I am ready to catch up web service in desktop constructor");
	}
	/*@SuppressWarnings("unchecked")
	public void useWebService() throws ParseException, org.json.simple.parser.ParseException {
		String ano="0000000001";
		//1) define Web Service URL
		String validUrl="http://localhost:8080/Mini_Bank_Client/checkValid/{accNo}";
		//2) need to given parameter 
		HashMap<String,String>  param=new HashMap<String,String>();
		param.put("accNo",ano);
		//3)call the web service
	   String retStr=this.restTemplate.getForObject(validUrl, String.class,param);
	   System.out.println(retStr);
	   //4) convert the respond to JSON
	   JSONParser jsParser=new JSONParser();
	   JSONObject jsObj=(JSONObject)jsParser.parse(retStr);
	   System.out.println("Accept Return ==>"+jsObj.get("status"));
	   if((Boolean)jsObj.get("status")) {
		   System.out.println(ano+" is VALID");
		   StringBuilder amtUrl=new StringBuilder("http://localhost:8080/Mini_Bank_Client/checkAmt");
		   amtUrl.append("?accNo=");
		   amtUrl.append(ano);
		   amtUrl.append("&amt=");
		   amtUrl.append(100000);
		   retStr=this.restTemplate.getForObject(amtUrl.toString(), String.class);		
		   jsObj=(JSONObject)jsParser.parse(retStr);
		   System.out.println("Accept Amount Return ==>"+jsObj.get("status"));
		   if((Boolean)jsObj.get("status")){
			   StringBuilder transferUrl=new StringBuilder("http://localhost:8080/Mini_Bank_Client/transfer");
			   jsObj=new JSONObject();
			   jsObj.put("from","0000000001");
			   jsObj.put("to","0000000003");
			   jsObj.put("amt",50000);
			   this.restTemplate.put(transferUrl.toString(),jsObj.toJSONString());
		   }
		   
	   }else {
		   System.out.println(ano+" is INVALID");
	   }
	}
}*/