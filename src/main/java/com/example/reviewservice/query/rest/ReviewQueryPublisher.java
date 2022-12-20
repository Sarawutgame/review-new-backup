package com.example.reviewservice.query.rest;

import com.example.reviewservice.core.ReviewEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewQueryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<ReviewRestModel> getReviews(){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "allreview", "hello");
        return ((List<ReviewRestModel>) result);
    }

    @GetMapping("/findById")
    public ReviewEntity getReviewById(@RequestParam String id){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbyid", id);
        return (ReviewEntity) result;
    }

    @GetMapping("/findByStoreType")
    public List<ReviewRestModel> getReviewsByStoreType(@RequestParam String storeType){
        Object result = rabbitTemplate.convertSendAndReceive("ReviewExchange", "reviewbytype", storeType);
        return ((List<ReviewRestModel>) result);
    }

}
