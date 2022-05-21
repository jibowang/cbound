package com.becky.repository;

import com.becky.common.OperationType;
import com.becky.entity.CBound;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CBoundRepository extends JpaRepository<CBound, Long> {

  @Query(value =
      "select cb from CBound cb "
          + "where (:name is null or cb.name like CONCAT('%',:name,'%')) "
          + "and (:operation is null or cb.operation = :operation) "
          + "and (:date is null or cb.date = :date) "
          + "and (:pairId is null or cb.pairId = :pairId) "
          + "order by :orderBy asc")
  List<CBound> getList(@Param("name") String name,
                      @Param("operation") OperationType operation,
                      @Param("date") Long date,
                      @Param("pairId") String pairId,
                      @Param("orderBy") String orderBy);

  @Modifying
  @Query(value = "update CBound cb set cb.pairId = :pairId where cb.id in :ids")
  void updatePairIds(@Param("ids") List<Long> ids, @Param("pairId") String pairId);

  @Modifying
  @Query(value = "update CBound cb set cb.pairId = null where cb.id in :ids")
  void deletePairIds(@Param("ids") List<Long> ids);
}
