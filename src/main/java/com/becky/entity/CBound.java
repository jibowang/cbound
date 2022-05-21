package com.becky.entity;

import com.becky.common.OperationType;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CBound implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "operation", nullable = false)
  @Enumerated(EnumType.STRING)
  private OperationType operation;

  @Column(name = "price", nullable = false)
  private String price;

  @Column(name = "count", nullable = false)
  private Integer count;

  @Column(name = "date")
  private Long date;

  @Column(name = "pair_id")
  private String pairId;

  @Column(name = "create_time")
  private Instant createTime;
}
