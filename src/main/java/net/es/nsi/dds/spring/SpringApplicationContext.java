/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.es.nsi.dds.spring;

/**
 * Obtain a reference to the Spring bean that has been configured and built
 * declaratively by the Spring container from the legacy code.
 *
 * Shamelessly liberated from the web.
 *
 * @author Sujit Pal
 * http://sujitpal.blogspot.com/2007/03/accessing-spring-beans-from-legacy-code.html
 */
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Wrapper to always return a reference to the Spring Application Context from
 * within non-Spring enabled beans. Unlike Spring MVC's WebApplicationContextUtils
 * we do not need a reference to the Servlet context for this. All we need is
 * for this bean to be initialized during application startup.
 */
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    /**
    * This method is called from within the ApplicationContext once it is
    * done starting up, it will stick a reference to itself into this bean.
    * @param context a reference to the ApplicationContext.
    */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

    /**
    * This is about the same as context.getBean("beanName"), except it has its
    * own static handle to the Spring context, so calling this method statically
    * will give access to the beans by name in the Spring application context.
    * As in the context.getBean("beanName") call, the caller must cast to the
    * appropriate target class. If the bean does not exist, then a Runtime error
    * will be thrown.
    * @param beanName the name of the bean to get.
    * @param requiredType the type to be returned
    * @return a reference to the named bean.
    */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return CONTEXT.getBean(name, requiredType);
    }
    
    public static Object getBean(String name) {
        return CONTEXT.getBean(name);
    }
}