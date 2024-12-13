package com.mini.bank;

import java.text.ParseException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyApp {
	public static void main(String[] args) throws ParseException{
		ApplicationContext appContext=new AnnotationConfigApplicationContext(com.mini.bank.init.SpringInitializer.class);
		System.out.println("Spring desktop application starting---");
		//OnlineBusiness ob=appContext.getBean(OnlineBusiness.class);
		//ob.useWebService();
	}
}
