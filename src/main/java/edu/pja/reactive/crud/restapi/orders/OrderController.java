package edu.pja.reactive.crud.restapi.orders;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/orders")
class OrderController {

  private static final List<Item> ITEMS = List.of(
          new Item(UUID.fromString("c0cd6957-d3a6-46dc-8240-3b4ff9f9e94b"), "Item 1", BigDecimal.ONE, BigDecimal.valueOf(11.5)),
          new Item(UUID.fromString("f1623704-d4bd-4de4-8890-a0993643a0d1"), "Item 2", BigDecimal.ONE, BigDecimal.valueOf(3.51))
  );

  @GetMapping
  public Flux<Order> getOrders() {
    return Flux.just(new Order(UUID.fromString("c0264c8e-6b95-4522-8092-136834be63f3"), ITEMS));
  }

  @GetMapping("/{id}")
  public Mono<Order> getOrder(@RequestParam UUID id) {
    return Flux.just(new Order(randomUUID(), ITEMS))
               .filter(o -> o.getId().equals(id))
               .singleOrEmpty();
  }

  @PostMapping
  @ResponseStatus(CREATED)
  public Mono<Order> addOrder(@RequestBody Order order) {
    return Mono.just(order);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public void deleteOrder(@RequestParam UUID id) {
    //todo delete order from DB
  }
}
