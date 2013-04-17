package pl.mszynkiewicz.jms.queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
@Service
public class QueueMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(QueueMessageListener.class);

    private boolean failOnMessage = false;

    @Autowired
    private QueueMessageHandlerService messageHandlerService;

    @Override
    public void onMessage(Message message) {
        if (failOnMessage) {
            throw new RuntimeException("Intentional failure. To make app handle messages correctly, use JMX");
        }
        if ((message instanceof TextMessage)) {
            handleMessage((TextMessage) message);
        } else {
            logger.error("Invalid message type: " + message.getClass());
        }
    }

    private void handleMessage(TextMessage message) {
        try {
            String address = message.getStringProperty("address");
            String content = message.getText();
            messageHandlerService.handle(address, content);
        } catch (JMSException jmsException) {
            logger.error("Error reading message", jmsException);
        }
    }

    public void setFailOnMessage(boolean failOnMessage) {
        this.failOnMessage = failOnMessage;
    }
}
