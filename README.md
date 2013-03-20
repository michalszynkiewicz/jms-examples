mail-service
============

Notification sender which reads messages from a queue.

To run the application you need HornetQ server running with queue mailSenderQueue defined and JNDI enabled.
You can customize JNDI settings and queue name in jms.properties file.
If you use linux, you can user installHornetq.sh script to download and initialize the server.
If you use this script, you'll

To run either:
 - Run Server class in your favourite IDE
 - build maven project:
        mvn clean install
   and run mail-service jar from target/bin:
        java -jar target/bin/mail-service-1.0-SNAPSHOT.jar