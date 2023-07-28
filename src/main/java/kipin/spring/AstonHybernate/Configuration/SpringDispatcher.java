package kipin.spring.AstonHybernate.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringDispatcher extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class}; //вставляем в класс конфиг
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //все запросы от пользователя посылаем на dispatcher servlet
    }




//    @Override
//    public void onStartup(ServletContext aServletContext) throws ServletException {
//        super.onStartup(aServletContext);
//        registerHiddenFieldFilter(aServletContext);
//    }
//
//    private void registerHiddenFieldFilter(ServletContext aContext) {
//        aContext.addFilter("hiddenHttpMethodFilter",
//                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
//    }


}
