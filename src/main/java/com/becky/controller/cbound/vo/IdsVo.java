package com.becky.controller.cbound.vo;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdsVo implements Serializable {
  private List<Long> ids;
}
