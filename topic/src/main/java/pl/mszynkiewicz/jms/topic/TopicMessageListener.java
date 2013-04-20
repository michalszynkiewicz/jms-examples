package pl.mszynkiewicz.jms.topic;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
public class TopicMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(TopicMessageListener.class);

    private String selector;

    @Override
    public void onMessage(Message message) {
        if ((message instanceof TextMessage)) {
            handleMessage((TextMessage) message);
        } else {
            logger.error("Invalid message type: " + message.getClass());
        }
    }

    private void handleMessage(TextMessage message) {
        try {
            logger.info("Consumer[" + selector + "] consumed message: " + message.getText());
        } catch (JMSException jmsException) {
            logger.error("Error reading message", jmsException);
        }
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
