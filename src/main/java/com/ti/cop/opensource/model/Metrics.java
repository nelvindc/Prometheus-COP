package com.ti.cop.opensource.model;


import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;


public class Metrics {
	
	public static final Counter HTTP_REQUEST_SERVICE_OPERATION = Counter.build()
			.name("http_requests_service_operation")
			.help("service invocation by operation")
			.labelNames("service", "operation", "method").register();
	
	public static final Counter HTTP_REQUEST_SERVICE_TOTAL = Counter.build()
			.name("http_requests_service_total")
			.help("service total invocation")
			.labelNames("service").register();

	public static final Histogram RESPONSE_TIME_HISTOGRAM = Histogram.build()
			.buckets(0.1, 0.5, 1.0, 1.5)
			.name("http_response_time_seconds_histogram")
			.help("histogram service response time")
			.labelNames("service", "operation", "method").register();


}
