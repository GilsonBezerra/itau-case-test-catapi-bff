package br.com.itau.cat_api_bff.listener;

import br.com.itau.cat_api_bff.controller.dto.BreedResponse;
import br.com.itau.cat_api_bff.entity.BreedMapper;
import br.com.itau.cat_api_bff.service.BreedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static br.com.itau.cat_api_bff.config.RabbitMQConfig.CAT_API_BREEDS_QUEUE;

@Component
@Slf4j
public class BreedConsumerListener {
    @Autowired
    BreedService service;

    @RabbitListener(queues = CAT_API_BREEDS_QUEUE)
    public void listen(Message<BreedResponse> message) {
        service.save(BreedMapper.toEntity(message.getPayload()));
    }
}
