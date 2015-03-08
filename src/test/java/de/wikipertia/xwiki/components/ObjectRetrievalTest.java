package de.wikipertia.xwiki.components;

import de.wikipertia.xwiki.components.IObjectRetrieval;
import de.wikipertia.xwiki.components.internal.DefaultObjectRetrieval;
import org.junit.Test;
import org.xwiki.test.jmock.AbstractMockingComponentTestCase;
import org.xwiki.test.jmock.annotation.MockingRequirement;

/**
 * Created by lako on 01.03.2015.
 */
@MockingRequirement(DefaultObjectRetrieval.class)
public class ObjectRetrievalTest extends AbstractMockingComponentTestCase<IObjectRetrieval> {
    @Test
    public void testGetTemplateObject() {

    }
}
