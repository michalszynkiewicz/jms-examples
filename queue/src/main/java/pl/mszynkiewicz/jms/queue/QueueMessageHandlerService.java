package pl.mszynkiewicz.jms.queue;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
@Service
public class QueueMessageHandlerService {
    private static final Logger logger = Logger.getLogger(QueueMessageHandlerService.class);

    public void handle(String address, String content) {
        logger.info("should send message: " + content + " to: " + address);
    }
}
