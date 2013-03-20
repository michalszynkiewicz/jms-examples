package mszynkiewicz.mail;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author: Michal Szynkiewicz, michal.l.szynkiewicz@gmail.com
 * Date: 18.03.13
 */
public class Server {
    public static void main(String... args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.start();
    }
}
