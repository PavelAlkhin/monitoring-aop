package service;

import org.testng.annotations.Test;
import uz.fincube.monitoring.consumer.QueueMetricsConsumer;
import uz.fincube.monitoring.context.MetricContextBlockingQueue;

import static java.lang.Thread.sleep;

public class MonitoringServiceTest {

    private final MetricContextBlockingQueue metricDtos = new MetricContextBlockingQueue();

    @Test
    public void setData() {
    }

    @Test
    public void incrementError() {
    }

    @Test
    public void testTakeQueue() {

    }

    @Test
    public void testPutPut() throws InterruptedException {

        QueueMetricsConsumer queueMetricsConsumer = new QueueMetricsConsumer(metricDtos, false);
        queueMetricsConsumer.run();

//        Thread thread = new Thread(() -> {
//            try {
//                while (true) {
//                    MetricDto take1 = metricDtos.getMetric();
//                    System.out.println(take1.name());
//                }
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        thread.start();

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                metricDtos.incrementSuccess("uz.fincube.customer.adapter.app.metric:type=basic,name=message-counter", 10);
            }
        });

        thread2.start();

        sleep(10000);
    }
}