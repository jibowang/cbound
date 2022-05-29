package com.becky.service;

import com.becky.entity.CBound;
import java.util.List;

public interface ICBoundService {

  void save(CBound cBound);

  CBound get(Long id);

  List<CBound> getList(String name, String operation, Long date, String pairId, String orderBy, String order);

  void delete(List<Long> ids);

  void update(CBound cBound);

  void updatePairs(Long inId, Long outId);

  void deletePairIds(List<Long> ids);
}
