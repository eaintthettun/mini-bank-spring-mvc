package com.mini.bank.init;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//similar with web.xml
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("Root Config method in web initializer");
		return new Class[] {HibernateInitializer.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Servlet Config method in web initializer");
		return new Class[] {SpringInitializer.class};
	}

	@Override
	protected String[] getServletMappings() {//<servlet-mapping>
		System.out.println("Servlet mapping method in web initializer");
		return new String[] {"/"};
	}
	
}
