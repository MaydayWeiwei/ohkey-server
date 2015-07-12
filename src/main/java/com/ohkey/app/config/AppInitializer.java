package com.ohkey.app.config;

import java.util.EnumSet;
import java.util.HashSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.ohkey.app.util.Constant;



//from Servlet 3.0: @WebServlet and @WebFilter have been added which enable the auto-charge of a Java when server is loaded
/*
 * WebApplicationInitializer is like a web.xml file
 * 
 * AbstractAnnotationConfigDispatcherServletInitializer is only a definition of a servlet
 * */

public class AppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		/*
		 * We can always use xml configration by calling
		 * XmlWebApplicationContext Class to charge the xml file Example:
		 * XmlWebApplicationContext rootContext= new XmlWebApplicationContext();
		 * rootContext.setConfigLocation("/WEB-INF/spring/root-context.xml");
		 * container.addListener(new ContextLoaderListener(rootContext));
		 */

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class, DBConfig.class,SecurityConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		/*
		 * We can always use xml configration by calling
		 * XmlWebApplicationContext Class to charge the xml file Example:
		 * XmlWebApplicationContext servletContext= new
		 * XmlWebApplicationContext(); servletContext.setConfigLocation(
		 * "/WEB-INF/spring/appServlet/servlet-context.xml");
		 * ServletRegistration.Dynamic dispatcher =
		 * container.addServlet("dispatcher", new
		 * DispatcherServlet(servletContext)); dispatcher.setLoadOnStartup(1);
		 * dispatcher.addMapping("/");
		 */

		/*
		 * First method:Another way to declare Spring Security filter
		 * AbstractAnnotationConfigDispatcherServletInitializer+
		 * AbstractAnnotationConfigDispatcherServletInitializer
		 * 
		 * Second methode:we can declare a spring security filter here directly
		 * 
		 * Example: FilterRegistration.Dynamic
		 * securityFilter=container.addFilter
		 * (AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME,
		 * DelegatingFilterProxy.class);
		 * securityFilter.addMappingForUrlPatterns(
		 * EnumSet.allOf(DispatcherType.class), false, "/*");
		 */
		FilterRegistration.Dynamic fr = container.addFilter("encodingFilter",
				new CharacterEncodingFilter());
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true,
				"/*");

		FilterRegistration.Dynamic corsFilter = container.addFilter("CORSFilter", SimpleCORSFilter.class);
	      corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
	      
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(MvcConfig.class);
//		dispatcherContext.getServletContext().setAttribute("model_image_url_base", "/app");
		
		//put the paramater in the servlet context which can be accessed by all the jsp
		

		HashSet<SessionTrackingMode> set = new HashSet<SessionTrackingMode>();
		set.add(SessionTrackingMode.COOKIE);
		container.setSessionTrackingModes(set);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

}