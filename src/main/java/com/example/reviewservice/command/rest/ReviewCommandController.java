package com.example.reviewservice.command.rest;


import com.example.reviewservice.command.CreateReviewCommand;
import com.example.reviewservice.command.UpdateReviewCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
public class ReviewCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ReviewCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @RabbitListener(queues = "CreateReviewQueue")
    public String createReview(CreateReviewModel model){
        CreateReviewCommand command = CreateReviewCommand.builder()
                ._id(UUID.randomUUID().toString())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .phone(model.getPhone())
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @RabbitListener(queues = "UpdateReviewQueue")
    public String update(UpdateReviewModel model){
        UpdateReviewCommand command = UpdateReviewCommand.builder()
                ._id(model.get_id())
                .name(model.getName())
                .branch(model.getBranch())
                .store_type(model.getStore_type())
                .description(model.getDescription())
                .imageId(model.getImageId())
                .address(model.getAddress())
                .timeOpen(model.getTimeOpen())
                .timeClose(model.getTimeClose())
                .personReview(model.getPersonReview())
                .phone(model.getPhone())
                .rating(model.getRating())
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .userId(model.getUserId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;

    }

}
