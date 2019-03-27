package config;

import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource(value = { "classpath:config.properties" })
@ComponentScan({ "dao", "services", "controller", "mapper", "pagination", "validator", "view", "servlet", "config" })
public class SpringConfig implements WebApplicationInitializer, WebMvcConfigurer {

	@Autowired
	private Environment env;

	private static Logger logger = LoggerFactory.getLogger(SpringConfig.class);

	/**
	 * Set datasource with properties file.
	 * 
	 * @return ds configured datasource
	 */
	@Bean
	public HikariDataSource setupDataSource() {

		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl(env.getRequiredProperty("jdbcUrl"));
		ds.setUsername(env.getRequiredProperty("dataSource.user"));
		ds.setPassword(env.getRequiredProperty("dataSource.password"));

		try {
			Class.forName("com.mysql.jdbc.Driver");
			ds.setDriverClassName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error("ClassName is invalid.", e.getMessage());
		}

		return ds;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setPrefix("/WEB-INF/");
		bean.setSuffix(".jsp");
		return bean;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(setupDataSource());
		sessionFactory.setPackagesToScan("model");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	/**
	 * Load message property file.
	 * 
	 * @return messageSource the source message
	 */
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Set a cookie to keep up the selected langage during a period.
	 * 
	 * @return localeResolver the cookie
	 */
	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		localeResolver.setCookieName("my-locale-cookie");
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}

	@Bean
	public LocaleChangeInterceptor localeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeInterceptor());
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/locale/**").addResourceLocations("/locale/");
    }
}
