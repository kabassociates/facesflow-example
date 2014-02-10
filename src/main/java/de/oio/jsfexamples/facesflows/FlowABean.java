package de.oio.jsfexamples.facesflows;

import java.io.Serializable;
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
public class FlowABean implements Serializable{
    
    private static final Logger LOG = Logger.getLogger(FlowABean.class.getName());
    
    private String welcomeMessage = "Welcome to Flow A!";
    private int selectedPage = 3;
    private String someMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
    
    public void initialize() {
        Flow flow = FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlow();
        Map<Object, Object> flowScope = FacesContext.getCurrentInstance().getApplication().getFlowHandler().getCurrentFlowScope();

        LOG.info("Flow A initialized");
    }
    
    public String doNothing() {
        methodFlowNode("called from within another method");
        return "method-node";
    }
    
    public void methodFlowNode(String param) {
        LOG.info("Flow A method node reached with param: " + param);
    }
    
    public String exitMethodFlowNode() {
        LOG.info("Exiting flow A through method node");
        return "return-node";
    }

    public void finalize() {
        LOG.info("Flow A finalized");
    }
    
    @PostConstruct 
    public void onCreation() {
        LOG.info("Flow A created");
    }
    
    @PreDestroy 
    public void onDestruction() {
        LOG.info("Flow A destroyed");
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage = selectedPage;
    }

    public String getSomeMessage() {
        return someMessage;
    }

    public void setSomeMessage(String someMessage) {
        this.someMessage = someMessage;
    }
    
}
