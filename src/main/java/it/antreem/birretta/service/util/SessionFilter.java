package it.antreem.birretta.service.util;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.model.Session;
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
    
    /**
     * Controllo che sia attiva una sessione.
     * Nell'header: 'Cookie: username=<i>username</i>; sid=<i>sid</i>'
     * 
     * 
     * @param request
     * @return 
     */
    private boolean checkSession(HttpServletRequest request)
    {
        // Per le operazioni di login e register non interessa la sessione
        if (request.getRequestURI().endsWith("/rest/bserv/login") ||
                request.getRequestURI().endsWith("/rest/bserv/register")){
            return true;
        }
        
        // Altrimenti per tutto il resto deve esserci una sessione attiva
        String username = request.getHeader("btUsername");
        String sid = request.getHeader("btSid");
        
        if (username == null || sid == null){
            return false;
        }
        
        Session s = DaoFactory.getInstance().getSessionDao().findSessionByUsername(username);
        if (s == null || !s.getSid().equals(sid)){
            return false;
        }
        else 
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
            pw.println("{ \"error\" : { \"code\": \"HTTP401\", \"title\": \"Http 401 Unauthorized\", \"desc\" : \"No active session\", \"actionType\": null } }");
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
        //cross domain..
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
         ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "x-requested-with");        
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
}
