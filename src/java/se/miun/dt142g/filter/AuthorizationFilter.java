package se.miun.dt142g.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author William
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
	}

    @Override
	public void init(FilterConfig filterConfig) throws 
                ServletException {
        
        }
    @Override
	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {
            		try {

			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();

                        if(reqURI.indexOf("/show.xhtml") >= 0){
                            resp.sendRedirect(reqt.getContextPath());
                        }
                        
                        else if (reqURI.indexOf("/login.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					//|| reqURI.indexOf("/public/") >= 0
					//|| reqURI.contains("javax.faces.resource")
                               )
                        {               
				chain.doFilter(request, response);  
                        }
                        else if(reqURI.indexOf("/create.xhtml") >= 0){
                            resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
                        }
                        else if(reqURI.indexOf("/Antonsskafferi") >= 0){
                            chain.doFilter(request, response);
                        }
                        else
				resp.sendRedirect(reqt.getContextPath() + "/show.xhtml");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void destroy() {

	}
        }
