package ms.mmontilla.registry.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class WebConfig implements InitializingBean {

    private DispatcherServlet servlet;

    public WebConfig(DispatcherServlet servlet) {
        this.servlet = servlet;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        servlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
