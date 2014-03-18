package com.openshift.metrics.extension;

import java.util.List;
import java.util.Locale;

import org.jboss.as.controller.AbstractAddStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.ServiceVerificationHandler;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceController;
import org.quartz.SchedulerException;

public class MetricAddHandler extends AbstractAddStepHandler implements DescriptionProvider {
	public static final MetricAddHandler INSTANCE = new MetricAddHandler();
	
	public MetricAddHandler() {
	}
	
	@Override
	public ModelNode getModelDescription(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void populateModel(ModelNode operation, ModelNode model) throws OperationFailedException {
		MetricDefinition.NAME.validateAndSet(operation, model);
		MetricDefinition.SCHEDULE.validateAndSet(operation, model);
	}

	@Override
	protected void performRuntime(OperationContext context, ModelNode operation, ModelNode model, ServiceVerificationHandler verificationHandler, List<ServiceController<?>> newControllers) throws OperationFailedException {
		MetricsService service = (MetricsService) context.getServiceRegistry(true).getRequiredService(MetricsService.getServiceName());
		String metricName = MetricDefinition.NAME.resolveModelAttribute(context, model).asString();
		String schedule = MetricDefinition.SCHEDULE.resolveModelAttribute(context, model).asString();
//		try {
//			service.addJob(metricName, schedule);
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
