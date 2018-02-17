package by.vadim.task.schedule;

import by.vadim.task.Application;
import by.vadim.task.service.ClientService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClientBalancePublisher {

    @Value("${K}")
    private int k;

    @Autowired
    private ClientService service;
    private final RabbitTemplate rabbitTemplate;

    public ClientBalancePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString  = "${M}")
    public void publisher(){
        service.getClientByBalanceValue(k).forEach(client -> rabbitTemplate.convertAndSend(Application.queueName, client.toString()));
    }
}
