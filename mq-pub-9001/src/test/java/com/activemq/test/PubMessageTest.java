package com.activemq.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;

/**
 * 测试消息发布
 */
@Slf4j
public class PubMessageTest {


    private static final String ACTIVEMQ_URL = "nio://candy1874.xyz:61608";



    @Test
    void testPub() throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false , Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("TESTQUEUE");

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < Integer.MAX_VALUE ; i ++) {

            TextMessage textMessage = session.createTextMessage();

            textMessage.setText("原来你是我最想留住的幸运 \t " + i);

            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

    }

    @Test
    void testSub1 () throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false , Session.AUTO_ACKNOWLEDGE);

        Queue testqueue = session.createQueue("TESTQUEUE");

        MessageConsumer consumer = session.createConsumer(testqueue);

        while (true) {
            TextMessage receive =(TextMessage) consumer.receive();
            log.warn("收到消息 \t :" + receive.getText());

        }


    }


    @Test
    void testSub2 () throws JMSException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = connectionFactory.createConnection();

        connection.start();

        Session session = connection.createSession(false , Session.AUTO_ACKNOWLEDGE);

        Queue testqueue = session.createQueue("TESTQUEUE");

        MessageConsumer consumer = session.createConsumer(testqueue);

        while (true) {
            TextMessage receive =(TextMessage) consumer.receive();
            log.warn("收到消息 \t :" + receive.getText());

        }


    }

}
