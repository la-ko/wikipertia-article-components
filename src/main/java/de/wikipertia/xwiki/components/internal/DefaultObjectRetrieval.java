package de.wikipertia.xwiki.components.internal;

import com.xpn.xwiki.api.Document;
import com.xpn.xwiki.api.Object;
import com.xpn.xwiki.objects.BaseObject;
import de.wikipertia.xwiki.components.IObjectRetrieval;
import org.xwiki.bridge.DocumentAccessBridge;
import org.xwiki.component.annotation.Component;
import org.xwiki.component.manager.ComponentLookupException;
import org.xwiki.component.manager.ComponentManager;
import org.xwiki.query.Query;
import org.xwiki.query.QueryException;
import org.xwiki.query.QueryManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lako on 01.03.2015.
 */
@Component
@Singleton
public class DefaultObjectRetrieval implements IObjectRetrieval {
    @Inject
    private DocumentAccessBridge documentAccessBridge;

    @Inject
    private ComponentManager componentManager;

    @Override
    public com.xpn.xwiki.api.Object getTemplateObject(Document document, String xwikiClassName) {
        return document.getObject(xwikiClassName, "__rank", "-1");
    }

    @Override
    public List<com.xpn.xwiki.api.Object> getObjectsOrdered(Document document, String xwikiClassName, String property, Boolean isAscendingOrder) {
        List<Object> ret = new ArrayList<>();

        try {
            final QueryManager queryManager = (QueryManager) componentManager.getInstance(QueryManager.class);

            // For Number properties, the sorting does not work if Number Type 'long' is selected;
            // Number Type 'integer' does work
            String queryStr = "SELECT obj FROM BaseObject obj, XWikiDocument doc, BaseProperty prop" +
                    " WHERE obj.className = '" + xwikiClassName + "' AND obj.name = doc.fullName AND doc.id = :docId" +
                    " AND prop.name = '" + property + "' AND prop.id.id = obj.id ORDER BY prop.value";

            String order = " DESC";
            if(isAscendingOrder) {
                order = " ASC";
            }

            queryStr += order;

            final Query query = queryManager.createQuery(queryStr, Query.HQL);
            query.bindValue("docId", document.getId());

            List<BaseObject> objects = query.execute();
            for(BaseObject bo : objects) {
                ret.add(document.getObject(xwikiClassName, bo.getNumber()));
            }
        } catch (ComponentLookupException e) {
            throw new IllegalStateException("Could not instantiate QueryManager.", e);
        } catch (QueryException e) {
            throw new IllegalStateException("Could not create query.", e);
        }

        return ret;
    }
}
