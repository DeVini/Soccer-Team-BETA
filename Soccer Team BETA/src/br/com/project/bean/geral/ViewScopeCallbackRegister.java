package br.com.project.bean.geral;

import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructViewMapEvent;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

@SuppressWarnings("unchecked")
public class ViewScopeCallbackRegister implements ViewMapListener {

	@Override
	public boolean isListenerForSource(Object source) {
		return source instanceof UIViewRoot;
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {

		if (event instanceof PostConstructViewMapEvent) {
			PostConstructViewMapEvent viewMapEvent = (PostConstructViewMapEvent) event;
			UIViewRoot uiViewRoot = (UIViewRoot) viewMapEvent.getComponent();
			uiViewRoot.getViewMap().put(ViewScope.VIEW_SCOPE_CALLBACKS, new HashMap<String, Runnable>());

		} else if (event instanceof PreDestroyViewMapEvent) {
			PreDestroyViewMapEvent preDestroyViewMapEvent = (PreDestroyViewMapEvent) event;
			UIViewRoot uiViewRoot = (UIViewRoot) preDestroyViewMapEvent.getComponent();
			Map<String, Runnable> callBacks = (Map<String, Runnable>) uiViewRoot.getViewMap()
					.get(ViewScope.VIEW_SCOPE_CALLBACKS);
			if (callBacks != null) {
				for (Runnable c : callBacks.values()) {
					c.run();
				}
				callBacks.clear();
			}
		}
	}

}
