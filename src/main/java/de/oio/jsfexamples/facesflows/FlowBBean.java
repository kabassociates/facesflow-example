package de.oio.jsfexamples.facesflows;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author Thomas Asel
 */

@Named
@FlowScoped("flow-b")
public class FlowBBean implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(FlowBBean.class.getName());
    
    private String welcomeMessage = "Welcome to Flow B!";
    private String inbound;

    @PostConstruct 
    public void onCreation() {
        LOG.info("Flow B created");
    }
    
    @PreDestroy 
    public void onDestruction() {
        LOG.info("Flow B destroyed");
    }
    
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getInbound() {
        return inbound;
    }

    public void setInbound(String inbound) {
        this.inbound = inbound;
    }

}
