package de.wikipertia.xwiki.components;

import com.xpn.xwiki.api.*;
import com.xpn.xwiki.api.Object;
import org.xwiki.component.annotation.Role;

import java.util.List;

/**
 * Created by lako on 01.03.2015.
 */
@Role
public interface IObjectRetrieval {
    /**
     * Retrieve the template object of the of a given XWiki class on the passed document.
     *
     * @param document       - the XWiki document to get the template object from
     * @param xwikiClassName - the template objects' XWiki class name
     * @return
     */
    public abstract com.xpn.xwiki.api.Object getTemplateObject(final Document document, final String xwikiClassName);

    /**
     * Retrieve all objects of the given class in the given document ordered by given property in ascending or descending order.
     *
     * @param document       - the XWiki document to retrieve the objects for
     * @param xwikiClassName - the objects' XWiki class name
     * @param property       - the sorting property
     * @param ascending      - <code>true</code> ascending order; <code>false</code> descending order
     * @return sorted list of objects
     */
    public abstract List<com.xpn.xwiki.api.Object> getObjectsOrdered(final Document document, final String xwikiClassName, final String property, final Boolean isAscendingOrder);
}
