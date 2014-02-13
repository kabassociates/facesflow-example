package de.oio.jsfexamples.facesflows;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

/**
 *
 * @author Thomas Asel
 */
public class NaviagtionTargetListener implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        String target = (String) event.getNewValue();
        ConfigurableNavigationHandler nh =  (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
        nh.performNavigation(target);
    }
    
}
