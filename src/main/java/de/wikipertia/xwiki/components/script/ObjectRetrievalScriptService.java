package de.wikipertia.xwiki.components.script;

import com.xpn.xwiki.api.*;
import de.wikipertia.xwiki.components.IObjectRetrieval;
import org.xwiki.component.annotation.Component;
import org.xwiki.script.service.ScriptService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by lako on 01.03.2015.
 */
@Component
@Named("objectRetrieval")
@Singleton
public class ObjectRetrievalScriptService implements ScriptService {
    @Inject
    private IObjectRetrieval objectRetrieval;

    public com.xpn.xwiki.api.Object getTemplateObject(final Document document, final String xwikiClassName) {
        return this.objectRetrieval.getTemplateObject(document, xwikiClassName);
    }

    public List<com.xpn.xwiki.api.Object> getObjectsOrdered(final Document document, final String xwikiClassName, final String property, final Boolean ascending) {
        return this.objectRetrieval.getObjectsOrdered(document, xwikiClassName, property, ascending);
    }
}
