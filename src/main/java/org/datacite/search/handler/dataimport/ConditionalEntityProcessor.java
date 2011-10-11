package org.datacite.search.handler.dataimport;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.EntityProcessorBase;

public class ConditionalEntityProcessor extends EntityProcessorBase {

    public static final String CONDITIONAL_ATTRIBUTE = "hasField";
    
    boolean skip;

    boolean nextRowExecuted;

    @Override
    public void init(Context context) {
        super.init(context);
        String conditionalField = context.getEntityAttribute(CONDITIONAL_ATTRIBUTE);
        Object condition = context.resolve(conditionalField);
        skip = condition == null;
    }

    @Override
    public Map<String, Object> nextRow() {
        if (skip)
            return null;
        else {
            skip = true;
            return new HashMap<String, Object>();
        }

    }
}