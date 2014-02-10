package de.oio.jsfexamples.facesflows;

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
    public Flow defineFlowA( @FlowBuilderParameter FlowBuilder flowBuilder) {
        
        String flowId = "flow-a";   // id for this flow 
        flowBuilder.id("", flowId); // set flow id
        
        // add a view to the flow and mark it as start node of the flow graph
        flowBuilder.viewNode(flowId, "/" + flowId + "/" + flowId + ".xhtml").markAsStartNode(); 
        
        // add another view to the flow 
        flowBuilder.viewNode("flow-a-page-2", "/" + flowId + "/page2.xhtml");   

        // add another view to the flow 
        flowBuilder.viewNode("flow-a-page-3", "/common-page.xhtml"); 
        
        // add a method call node to the flow that transitions to another node
        flowBuilder.methodCallNode("method-node")       // name of the node
                .defaultOutcome("flow-a-page-3")        // use this outcome, if the method does not return a different one
                .expression("#{flowABean.methodFlowNode('test')}", new Class[]{String.class});  // expression to invoke
                   
   
        // add a method call node to the flow that exits the flow by returning an outcome containing the node id of the return node
        flowBuilder.methodCallNode("exit-method-node")
                .expression("#{flowABean.exitMethodFlowNode()}");

        flowBuilder.switchNode("switch-node")   // create a switch node
                .defaultOutcome("home")         // exit flow to homepage, if no valid selection happened
                // several switch statements
                .switchCase()  
                    .condition("#{flowABean.selectedPage==1}").fromOutcome("flow-a")
                .switchCase()
                    .condition("#{flowABean.selectedPage==2}").fromOutcome("flow-a-page-2")
                .switchCase()
                    .condition("#{flowABean.selectedPage==3}").fromOutcome("flow-a-page-3")
                .switchCase()
                    .condition("#{flowABean.selectedPage==4}").fromOutcome("return-node");

        // add a return node. The flow is exited with the outcome "home" once this node is reached.
        flowBuilder.returnNode("return-node").fromOutcome("home");
        
        // call this when the flow is entered
        flowBuilder.initializer("#{flowABean.initialize()}");
           
        // call this when the flow is exited
        flowBuilder.finalizer("#{flowABean.finalize()}");

        // Node to go directly to flow B
flowBuilder.flowCallNode("call-flow-b")
        .flowReference("", "flow-b")
        .outboundParameter("param", "#{flowABean.someMessage}");
        
        return flowBuilder.getFlow();
    }
    
    @Produces @FlowDefinition
    public Flow defineFlowB( @FlowBuilderParameter FlowBuilder flowBuilder) {
        String flowId = "flow-b";   
        flowBuilder.id("", flowId); 
        
        flowBuilder.inboundParameter("param", "#{flowBBean.inbound}");
        
        flowBuilder.viewNode(flowId, "/" + flowId + "/" + flowId + ".xhtml").markAsStartNode();
        flowBuilder.viewNode("flow-b-page-2", "/" + flowId + "/flowb2.xhtml");
        flowBuilder.returnNode("return-to-a").fromOutcome("flow-a-page-3");
        
        
        return flowBuilder.getFlow();
    }
}