package ms.mmontilla.registry.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class WebConfig implements InitializingBean {

    @Autowired
    private DispatcherServlet servlet;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("11111111111111111111111111111111111111111111");
        servlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
