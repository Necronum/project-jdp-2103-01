package com.kodilla.ecommercee;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/order")
public class OrderController {

    private final GenericEntityRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenericEntity> getAllOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenericEntity getOrderById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Order not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addOrder(@Valid @RequestBody RESTOrderCommand command){
        repository.save(command.toGenericEntity());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrder(@PathVariable Long id, @RequestBody RESTOrderCommand command) {
        repository.findById(id)
                .ifPresent(order -> {
                    order.setValue(command.getValue());
                    repository.save(order);});
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrderById(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @Data
    private static class RESTOrderCommand {
        @NotBlank(message = "Please provide a value")
        private String value;

        GenericEntity toGenericEntity() {
            return new GenericEntity(value);
        }
    }
}
