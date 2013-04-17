package pl.mszynkiewicz.jms.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 17.04.13
 */
@ManagedResource
@Service
public class ConsumerManagement {
    @Autowired
    private QueueMessageListener queueMessageListener;

    @ManagedOperation
    public void enableExceptionOnQueueConsumption() {
        queueMessageListener.setFailOnMessage(true);
    }

    @ManagedOperation
    public void disableExceptionOnQueueConsumption() {
        queueMessageListener.setFailOnMessage(false);
    }

}
