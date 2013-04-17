package pl.mszynkiewicz.jms.duplex;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
@Service
public class RespondingMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(RespondingMessageListener.class);

    @Autowired
    private JmsTemplate responseSender;

    @Override
    public void onMessage(Message message) {
        if ((message instanceof TextMessage)) {
            handleMessage((TextMessage) message);
        } else {
            logger.error("Invalid message type: " + message.getClass());
        }
    }

    private void handleMessage(final TextMessage message) {
        try {
            final String correlationID = message.getJMSCorrelationID();
            logger.info("Consumed message: " + message.getText() + " with correlationID: " + correlationID);
            responseSender.send(message.getJMSReplyTo(), new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage response = session.createTextMessage();
                    if (correlationID != null) {
                        response.setJMSCorrelationID(correlationID);
                    }
                    response.setText("response message for " + message.getText());
                    return response;
                }
            });
        } catch (JMSException jmsException) {
            logger.error("Error reading message", jmsException);
        }
    }

}
