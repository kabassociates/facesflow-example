/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.oio.jsfexamples.facesflows.flowa;

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
@FlowScoped("flow-a")
public class FlowABean {
    
    private static final Logger LOG = Logger.getLogger(FlowABean.class.getSimpleName());
    
    private String welcomeMessage = "Welcome to Flow A!";

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
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
