package mszynkiewicz.mail.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
@Service
public class MailSenderService {
    private static final Logger logger = Logger.getLogger(MailSenderService.class);

    public void send(String address, String content) {
        logger.info("should send message: " + content + " to: " + address);
    }
}
