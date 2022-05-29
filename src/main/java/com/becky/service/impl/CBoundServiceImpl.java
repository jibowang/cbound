package com.becky.service.impl;

import com.becky.common.OperationType;
import com.becky.entity.CBound;
import com.becky.repository.CBoundRepository;
import com.becky.service.ICBoundService;
import java.util.List;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
  public List<CBound> getList(String name, String operation, Long date, String pairId, String sortBy, String sort) {
    OperationType operationType = OperationType.from(operation);

    sortBy = StringUtils.isBlank(sortBy) ? "createTime" : sortBy;
    sort = StringUtils.isBlank(sort) ? "asc" : sort;

    return cBoundRepository.getList(name, operationType, date, pairId, Sort.by(Direction.fromString(sort), sortBy));
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
