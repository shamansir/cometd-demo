/**
 * 
 */
package name.foobar.cometddemo;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.cometd.Bayeux;
import org.cometd.Channel;
import org.cometd.Client;
import org.cometd.server.BayeuxService;
import org.cometd.server.ext.TimesyncExtension;

/**
 * <dl>
 * <dt>Project:</dt> <dd>cometd-demo</dd>
 * <dt>Package:</dt> <dd>name.foobar.cometddemo</dd>
 * </dl>
 *
 * <code>CometdEntryPoint</code>
 *
 * <p>Description</p>
 *
 * @author Ulric Wilfred <shaman.sir@gmail.com>
 * @date Apr 6, 2011 7:36:25 PM 
 *
 */
@SuppressWarnings("serial")
public class CometdEntryPoint extends GenericServlet {

    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        throw new ServletException("'service' method is not appliable to this servlet");        
    }
    
    @Override
    public void init() throws ServletException {
        
        // Grab the Bayeux object
        Bayeux bayeux = (Bayeux)getServletContext().getAttribute(Bayeux.ATTRIBUTE);  
        
        final BayeuxService service = new ComedDemoService(bayeux);
        
        bayeux.addExtension(new TimesyncExtension());        
      
    }
    
    public class ComedDemoService extends BayeuxService {
        
        final Channel chatChannel;

        public ComedDemoService(Bayeux bayeux) {
            super(bayeux, "cometddemo");
            
            chatChannel = bayeux.getChannel("/chat", true);

            subscribe("/login", "tryLogin");            
        }
        
        public void tryLogin(final Client remote, Map<String, Object> data) {
            final Map<String, String> enterInfo = new HashMap<String, String>();
            enterInfo.put("status", "ENTERED at " + new Date().toString() + " : " + data.get("username"));
            
            chatChannel.publish(getClient(),
                                enterInfo, 
                                String.valueOf(2383783));            
        }
        
    }
    

}
