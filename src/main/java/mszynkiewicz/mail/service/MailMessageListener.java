package mszynkiewicz.mail.service;

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
public class MailMessageListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(MailMessageListener.class);

    @Autowired
    private MailSenderService mailSenderService;

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
            String address = message.getStringProperty("address");
            String content = message.getText();
            mailSenderService.send(address, content);
        } catch (JMSException jmsException) {
            logger.error("Error reading message", jmsException);
        }
    }
}
