/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.oio.jsfexamples.facesflows.flowa;

import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.faces.flow.Flow;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

/**
 *
 * @author Thomas Asel
 */
@Named
@FlowScoped("flow-a")
public class FlowABean {
    
    private static final Logger LOG = Logger.getLogger(FlowABean.class.getName());
    
    private String welcomeMessage = "Welcome to Flow A!";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
    
    public void initialize() {
        Flow flow = FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlow();
        Map<Object, Object> flowScope = FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope();

        LOG.info("Flow initialized");
    }
    
    public void methodFlowNode(String param) {
        LOG.info("Method node reached with param: " + param);
    }
    
    public String exitMethodFlowNode() {
        LOG.info("Exiting flow through method node");
        return "return-node";
    }

    public void finalize() {
        LOG.info("Flow finalized");
    }
    
    @PostConstruct 
    public void onCreation() {
        LOG.info("created");
    }
    
    @PreDestroy 
    public void onDestruction() {
        LOG.info("destroyed");
    }
    
}
