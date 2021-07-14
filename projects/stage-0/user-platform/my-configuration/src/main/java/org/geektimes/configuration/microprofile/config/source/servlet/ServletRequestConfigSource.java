package org.geektimes.configuration.microprofile.config.source.servlet;

import org.geektimes.configuration.microprofile.config.source.MapBasedConfigSource;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.Map;

import static java.lang.String.format;

public class ServletRequestConfigSource extends MapBasedConfigSource {
    private final ServletRequest servletRequest;

    protected ServletRequestConfigSource(ServletRequest servletRequest) {
        super(format("Servlet[RemoteAddress:%s,Timestamp:%d] Request Parameters", servletRequest.getRemoteAddr(), System.currentTimeMillis()), 600);
        this.servletRequest = servletRequest;
    }

    @Override
    protected void prepareConfigData(Map configData) throws Throwable {
        Enumeration<String> servletRequestParameterNames = servletRequest.getParameterNames();
        while (servletRequestParameterNames.hasMoreElements()) {
            String parameterName = servletRequestParameterNames.nextElement();
            configData.put(parameterName, servletRequest.getParameterValues(parameterName));
        }
    }
}
