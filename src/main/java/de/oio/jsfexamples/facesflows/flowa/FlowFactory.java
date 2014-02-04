/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.oio.jsfexamples.facesflows.flowa;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;

/**
 *
 * @author Thomas Asel
 */
public class FlowFactory implements Serializable {
    
    private final static Logger LOG = Logger.getLogger(FlowFactory.class.getSimpleName());
    private static final long serialVersionUID = 1L;

    @Produces @FlowDefinition
    public Flow defineFlow( @FlowBuilderParameter FlowBuilder flowBuilder) {
        String flowId = "flow-a";   // id for this flow 
        flowBuilder.id("", flowId); // set flow id
        
        // add a view to the flow and mark it as start node of the flow graph
        flowBuilder.viewNode(flowId, "/" + flowId + "/" + flowId + ".xhtml").markAsStartNode(); 
        
        // add another view to the flow 
        flowBuilder.viewNode("flow-a-page-2", "/" + flowId + "/page2.xhtml");   

        // add another view to the flow 
        flowBuilder.viewNode("flow-a-page-3", "/common-page.xhtml");   
        
        // add a return node. The flow is exited with the outcome "home" once this node is reached.
        flowBuilder.returnNode("return-node").fromOutcome("home");
        
        return flowBuilder.getFlow();
    }
}