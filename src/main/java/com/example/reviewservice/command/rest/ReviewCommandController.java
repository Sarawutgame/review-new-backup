package com.example.reviewservice.command.rest;




import com.example.reviewservice.command.CreateReviewCommand;
import com.example.reviewservice.command.UpdateReviewCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/review")
public class ReviewCommandController {

    private final CommandGateway commandGateway;

    @Autowired
    public ReviewCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

//    @GetMapping
//    public String getReview(){
//        return "Hello_Ja";
//    }

    @PostMapping
    public String createReview(@RequestBody CreateReviewModel model){
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
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @PutMapping(value = "/update")
    public String update(@RequestBody UpdateReviewModel model){
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
                .ban(model.isBan())
                .range(model.getRange())
                .delivery(model.isDelivery())
                .pickUp(model.isPickUp())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;

    }

    @DeleteMapping
    public String deleteReview(){
        return "Delete Review";
    }
}
