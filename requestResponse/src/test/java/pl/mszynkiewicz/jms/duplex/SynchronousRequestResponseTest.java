package pl.mszynkiewicz.jms.duplex;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;

import javax.jms.*;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 19.03.13
 */
public class SynchronousRequestResponseTest {
    private static final Logger logger = Logger.getLogger(SynchronousRequestResponseTest.class);

    private static JmsTemplate messageSender;

    @BeforeClass
    public static void initialize() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("testContext.xml");
        messageSender = context.getBean(JmsTemplate.class);
    }

    @Ignore
    @Test
    public void synchronousRequestResponseWithSelector() throws JMSException {
        final String correlationId = generateRandomDouble();
        messageSender.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setJMSCorrelationID(correlationId);
                Queue outputQueue = session.createQueue("outputQueue");
                message.setJMSReplyTo(outputQueue);
                message.setText("Message with correlationId " + correlationId);
                return message;
            }
        });
        logger.info("sent message. Waiting for response...");
        Message responseMessage = messageSender.receiveSelected("outputQueue", "JMSCorrelationID='" + correlationId + "'"); // locking consumption
        logger.info("Response: " + ((TextMessage) responseMessage).getText());
    }

    @Ignore
    @Test
    // todo:
    public void synchronousRequestResponseWithTemporaryQueue() throws JMSException {
        messageSender.execute(new SessionCallback<Queue>() {
            @Override
            public Queue doInJms(Session session) throws JMSException {
                Queue responseQueue = session.createTemporaryQueue();
                TextMessage message = session.createTextMessage();

                Queue requestQueue = session.createQueue(messageSender.getDefaultDestinationName());
                message.setJMSReplyTo(responseQueue);
                message.setText("Message for number " + generateRandomDouble());
                session.createProducer(requestQueue)
                        .send(message);
                logger.info("sent message. Waiting for response...");
                TextMessage response = (TextMessage) session.createConsumer(responseQueue).receive();
                logger.info("Response: " + response.getText());
                return responseQueue;
            }
        });
    }

    private String generateRandomDouble() {
        return String.valueOf(Math.random());
    }
}
