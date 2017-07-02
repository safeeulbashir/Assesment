
package com.safee.AirlineReservation.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = "com.safee.AirlineReservation")
@EnableTransactionManagement
public class MyConfiguration {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
	MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	servlet.setApplicationContext(applicationContext);
	servlet.setTransformWsdlLocations(true);
	return new ServletRegistrationBean(servlet, "/ws/*");
}

@Bean(name = "employee")
public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeeSchema) {
	DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	wsdl11Definition.setPortTypeName("FlightPort");
	wsdl11Definition.setLocationUri("/ws");
	wsdl11Definition.setTargetNamespace("http://www.safee.com/response");
	wsdl11Definition.setSchema(employeeSchema);
	/*wsdl11Definition.setRequestSuffix("Request");
	wsdl11Definition.setResponseSuffix("Response");
	*/return wsdl11Definition;
}
@Bean
public XsdSchema employeeSchema() {
	return new SimpleXsdSchema(new ClassPathResource("response.xsd"));
}


}
