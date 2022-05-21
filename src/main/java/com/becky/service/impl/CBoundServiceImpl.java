package com.becky.service.impl;

import com.becky.common.OperationType;
import com.becky.entity.CBound;
import com.becky.repository.CBoundRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CBoundServiceImpl implements ICBoundService {

  private final CBoundRepository cBoundRepository;

  public CBoundServiceImpl(CBoundRepository cBoundRepository) {
    this.cBoundRepository = cBoundRepository;
  }

  @Override
  public void save(CBound cBound) {
    cBoundRepository.save(cBound);
  }

  @Override
  public CBound get(Long id) {
    return cBoundRepository.getReferenceById(id);
  }

  @Override
  public List<CBound> getList(String name, String operation, Long date, String pairId, String orderBy) {
    OperationType operationType = OperationType.from(operation);
    orderBy = StringUtils.isBlank(orderBy) ? "create_time" : orderBy;

    return cBoundRepository.getList(name, operationType, date, pairId, orderBy);
  }

  @Override
  public void delete(List<Long> ids) {
    cBoundRepository.deleteAllById(ids);
  }

  @Override
  public void update(CBound cBound) {
    cBoundRepository.save(cBound);
  }

  @Override
  @Transactional
  public void updatePairs(Long inId, Long outId) {
    String pairId = inId + "-" + outId;
    cBoundRepository.updatePairIds(List.of(inId, outId), pairId);
  }

  @Override
  @Transactional
  public void deletePairIds(List<Long> ids) {
    cBoundRepository.deletePairIds(ids);
  }
}
