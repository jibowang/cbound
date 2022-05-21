package com.becky.controller.cbound.vo;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PairRequestVo implements Serializable {
  private Long inId;
  private Long outId;
}
