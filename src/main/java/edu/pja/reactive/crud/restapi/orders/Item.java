package edu.pja.reactive.crud.restapi.orders;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Item {

  private UUID id;
  private String name;
  private BigDecimal amount;
  private BigDecimal price;

}
