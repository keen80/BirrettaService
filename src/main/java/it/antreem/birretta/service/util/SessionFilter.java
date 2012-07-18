package it.antreem.birretta.service.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Filtro per il controllo della sessione attiva e, per ora, unica modalit&agrave;
 * di autorizzazione per gli utenti.
 * 
 * @author alessio
 */
public class SessionFilter implements Filter {
    
    private static final boolean log_debug = true;
    private static final Log log = LogFactory.getLog(SessionFilter.class);
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public SessionFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (log_debug) {
            log.debug("SessionFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
	/*
         * for (Enumeration en = request.getParameterNames();
         * en.hasMoreElements(); ) { String name = (String)en.nextElement();
         * String values[] = request.getParameterValues(name); int n =
         * values.length; StringBuffer buf = new StringBuffer();
         * buf.append(name); buf.append("="); for(int i=0; i < n; i++) {
         * buf.append(values[i]); if (i < n-1) buf.append(","); }
         * log(buf.toString()); }
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (log_debug) {
            log.debug("SessionFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.

        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
	/*
         * for (Enumeration en = request.getAttributeNames();
         * en.hasMoreElements(); ) { String name = (String)en.nextElement();
         * Object value = request.getAttribute(name); log("attribute: " + name +
         * "=" + value.toString());
         *
         * }
         */

        // For example, a filter might append something to the response.
	/*
         * PrintWriter respOut = new PrintWriter(response.getWriter());
         * respOut.println("<P><B>This has been appended by an intrusive
         * filter.</B>");
         */
    }

    /**
     * Controllo che sia attiva una sessione.
     * // TODO: Commenti
     * 
     * 
     * @param request
     * @return 
     */
    private boolean checkSession(HttpServletRequest request){
        
        // TODO: complete!
        // Do not apply in login or register!!!
        
        return true;
    }
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException 
    {    
        if (log_debug) {
            log.debug("SessionFilter:doFilter()");
        }
        
        doBeforeProcessing(request, response);
        
        boolean authOk = checkSession((HttpServletRequest) request);
        if (!authOk)
        {
            HttpServletResponse res = (HttpServletResponse) response;
            res.setContentType("application/json");
            PrintWriter pw = res.getWriter();
            pw.println("{ \"error\" : { \"code\": 401, \"title\": \"Http 401 Unauthorized\", \"desc\" : \"No active session\", \"actionType\": null } }");
            return;
        }
        
        try 
        {
            chain.doFilter(request, response);
            
        } catch (Throwable t) {
            log.error("Error in doFilter: " + t.getLocalizedMessage(), t);
            HttpServletResponse res = (HttpServletResponse) response;
            res.setContentType("application/json");
            PrintWriter pw = res.getWriter();
            pw.println("{ \"error\" : { \"code\": 999, \"title\": \"Generic Error\", \"desc\" : \"Generic server-side error.\", \"actionType\": null } }");
            return;
        }
        
        doAfterProcessing(request, response);
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (log_debug) {                
                log.debug("SessionFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SessionFilter()");
        }
        StringBuilder sb = new StringBuilder("SessionFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
}