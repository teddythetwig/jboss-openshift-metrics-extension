package com.openshift.metrics.extension;

import org.jboss.as.controller.PathElement;
import org.jboss.as.controller.SimpleAttributeDefinition;
import org.jboss.as.controller.SimpleAttributeDefinitionBuilder;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.registry.AttributeAccess;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.dmr.ModelType;

public class MetricDefinition extends SimpleResourceDefinition {
    public static final PathElement METRIC_PATH = PathElement.pathElement("metric");

    public static final MetricDefinition INSTANCE = new MetricDefinition();

    protected static final SimpleAttributeDefinition KEY =
            new SimpleAttributeDefinitionBuilder("key", ModelType.STRING)
                .setAllowExpression(false)
                .setXmlName("key")
                .setFlags(AttributeAccess.Flag.RESTART_ALL_SERVICES)
                .setAllowNull(false)
                .build();

    private MetricDefinition() {
        super(METRIC_PATH, OpenShiftSubsystemExtension.getResourceDescriptionResolver("schedule", "source", "metric"), MetricAddHandler.INSTANCE, MetricRemoveHandler.INSTANCE);
    }

    @Override
    public void registerAttributes(ManagementResourceRegistration resourceRegistration) {
        resourceRegistration.registerReadOnlyAttribute(KEY, null);
    }
}
