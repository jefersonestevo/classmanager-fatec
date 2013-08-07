package br.com.classmanager.web.componentes.context;

import java.util.Map;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import br.com.classmanager.web.componentes.context.annotation.ClassManagerViewScoped;

public class ViewContext implements Context {

    public Class<ClassManagerViewScoped> getScope() {
        return ClassManagerViewScoped.class;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object get(Contextual contextual, CreationalContext creationalContext) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (Object) viewMap.get(bean.getName());
        }
        else {
            Object t = bean.create(creationalContext);
            viewMap.put(bean.getName(), t);
            return t;
        }
    }

    @SuppressWarnings("rawtypes")
    private Map getViewMap() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = fctx.getViewRoot();
        return viewRoot.getViewMap(true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object get(Contextual contextual) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (Object) viewMap.get(bean.getName());
        }
        else {
            return null;
        }
    }

    public boolean isActive() {
        return true;
    }
}
