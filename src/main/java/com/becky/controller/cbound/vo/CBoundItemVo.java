package com.becky.controller.cbound.vo;

import com.becky.entity.CBound;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CBoundItemVo implements Serializable {

  private Long id;
  private String name;
  private String operation;
  private String price;
  private Integer count;
  private Long date;
  private String pairId;

  public CBoundItemVo(CBound cBound) {
    this.id = cBound.getId();
    this.name = cBound.getName();
    this.operation = cBound.getOperation().getType();
    this.price = cBound.getPrice();
    this.count = cBound.getCount();
    this.date = cBound.getDate();
    this.pairId = cBound.getPairId();
  }
}
