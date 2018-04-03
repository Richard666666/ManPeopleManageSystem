package com.qf.manpower.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 动态代理实现统一字符编码
 * @author xiu
 *
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpServletRequest requestProxy = (HttpServletRequest) Proxy.newProxyInstance(HttpServletRequest.class.getClassLoader(), 
				request.getClass().getInterfaces(), 
				new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				if("getParameter".equals(method.getName())){
					String m = request.getMethod();
					if("get".equalsIgnoreCase(m)){
						/*Object object = method.invoke(request, args);
						String string = (String) object;
						return new String(string.getBytes("iso8859-1"),"utf-8");*/
						return method.invoke(request, args);
					}else if ("post".equalsIgnoreCase(m)) {
						request.setCharacterEncoding("utf-8");
						return method.invoke(request, args);
						
					}
				}
				return method.invoke(request, args);
			}
		});
		
		chain.doFilter(requestProxy, response);
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
