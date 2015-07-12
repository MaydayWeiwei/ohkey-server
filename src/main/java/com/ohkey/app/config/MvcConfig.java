package com.ohkey.app.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.theme.SessionThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ohkey.app.util.Constant;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.ohkey.app.controller","com.ohkey.app.admin.controller"})
// We should not declare com.francepormo.app it will parse other configuration
// files in the package
// @ImportResource("classpath:meanbeans.xml") One way to combine Annotation
// Configuration et XML Configuration
// We can find some methods in the WebMvcConfiguraerAdapter
public class MvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory
			.getLogger(MvcConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Override
	/*
	 * Configure a handler to delegate unhandled requests by forwarding to the
	 * Servlet container's "default" servlet. It's like <default-servlet-handler
	 * />
	 */
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(themechangeInterceptor());
	}

	@Bean
	// If we define a bean in the xml file, so here we have to declare an bean
	public InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver commonMultipartResolver = new CommonsMultipartResolver();
		commonMultipartResolver
				.setMaxUploadSize(Constant.UPLOAD_GENERNAL_MAX_SIZE);
		logger.info("the upload file max size is set to :"
				+ Constant.UPLOAD_GENERNAL_MAX_SIZE);
		return commonMultipartResolver;
	}

	// start configuration i18n
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Bean
	public ThemeChangeInterceptor themechangeInterceptor() {
		ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
		interceptor.setParamName("theme");
		return interceptor;
	}

	@Bean(name = "localeResolver")
	// we have to well define bean name in order that it can be used by other
	// context
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("fr"));
		return localeResolver;
	}

	@Bean(name = "messageSource")
	// where to find the message informations
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasename("i18n/messages");
		resource.setDefaultEncoding("UTF-8");
		resource.setFallbackToSystemLocale(true);
		resource.setUseCodeAsDefaultMessage(false);
		return resource;
	}

	@Bean(name = "themeResolver")
	public SessionThemeResolver themeResolver() {
		SessionThemeResolver themeResolver = new SessionThemeResolver();
		themeResolver.setDefaultThemeName("zh");
		return themeResolver;
	}

	@Bean(name = "themeSource")
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		themeSource.setBasenamePrefix("themes/theme-base-");
		return themeSource;
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonMessageConverter());
	}

	@Bean
	// create and return a mesage converter which will be user in the
	// configureMessageConverter 
	//deprecated and rejected we declare a jackson message converter instead 
	StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
		
		

		return converter;
	}
	
	MappingJackson2HttpMessageConverter jacksonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		return converter;
	}

	// end configuration i18n

	/*
	 * 1. <annotation-driven /> We don't declare it any more we can
	 * automatically use autowired annotation
	 */
}
