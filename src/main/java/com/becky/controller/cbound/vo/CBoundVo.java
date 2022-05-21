package com.becky.controller.cbound.vo;

import com.becky.common.OperationType;
import com.becky.entity.CBound;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CBoundVo implements Serializable {

  private static DecimalFormat df = new DecimalFormat("0.00");
  static {
    df.setRoundingMode(RoundingMode.HALF_UP);
  }

  private String name;
  private String operation;
  private Double price;
  private Integer count;
  private Long date;

  public CBound convert2CBound() {
    CBound cBound = new CBound();
    cBound.setName(name);
    cBound.setOperation(OperationType.from(operation));
    cBound.setPrice(df.format(price));
    cBound.setDate(date);
    cBound.setCount(count);
    cBound.setCreateTime(Instant.now());
    return cBound;
  }

  public void merge(CBound cBound) {
    if (name != null) {
      cBound.setName(name);
    }
    if (operation != null) {
      cBound.setOperation(OperationType.from(operation));
    }
    if (price != null) {
      cBound.setPrice(df.format(price));
    }
    if (date != null) {
      cBound.setDate(date);
    }
    if (count != null) {
      cBound.setCount(count);
    }
  }
}
