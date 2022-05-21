package com.becky.controller.cbound;

import static com.becky.common.Constants.RESULT;
import static com.becky.common.Constants.SUCCESS;

import com.becky.common.OperationType;
import com.becky.controller.cbound.vo.CBoundItemVo;
import com.becky.controller.cbound.vo.CBoundVo;
import com.becky.controller.cbound.vo.IdsVo;
import com.becky.controller.cbound.vo.PairRequestVo;
import com.becky.entity.CBound;
import com.becky.service.impl.ICBoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "CBounds 操作")
@RestController
@RequestMapping(path = "/api/v1/cbounds")
public class CBoundController {

  private final ICBoundService cBoundService;

  public CBoundController(ICBoundService cBoundService) {
    this.cBoundService = cBoundService;
  }

  @ApiOperation(value = "创建 CBound")
  @PostMapping
  public Map<String, Object> save(CBoundVo cBoundVo) {
    Map<String, Object> result = new HashMap<>();

    CBound cBound = cBoundVo.convert2CBound();
    cBoundService.save(cBound);

    result.put(RESULT, SUCCESS);
    return result;
  }

  @ApiOperation(value = "获取 CBound 详情")
  @GetMapping("{id}")
  public CBoundItemVo getById(@PathVariable("id") Long id) throws Exception {
    CBound cBound = cBoundService.get(id);

    if (cBound == null) {
      throw new Exception("找不到资源");
    }

    return new CBoundItemVo(cBound);
  }

  @ApiOperation(value = "获取 CBound 列表")
  @GetMapping
  public List<CBoundItemVo> getList(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "operation", required = false) String operation,
                                    @RequestParam(value = "date", required = false) Long date,
                                    @RequestParam(value = "pairId", required = false) String pairId,
                                    @RequestParam(value = "orderBy", required = false) String orderBy) {

    List<CBound> cBounds = cBoundService.getList(name, operation, date, pairId, orderBy);

    List<CBoundItemVo> cBoundItemVos = new ArrayList<>();
    for (CBound cBound : cBounds) {
      cBoundItemVos.add(new CBoundItemVo(cBound));
    }

    return cBoundItemVos;
  }

  @ApiOperation(value = "删除 CBound 列表")
  @PostMapping("/batchDelete")
  public Map<String, Object> batchDelete(@RequestBody IdsVo ids) {
    Map<String, Object> result = new HashMap<>();

    cBoundService.delete(ids.getIds());

    result.put(RESULT, SUCCESS);
    return result;
  }

  @ApiOperation(value = "修改 CBound")
  @PutMapping("/{id}")
  public Map<String, Object> put(@PathVariable("id") Long id,
                                 @RequestBody CBoundVo cBoundVo) throws Exception {
    Map<String, Object> result = new HashMap<>();

    CBound cBound = cBoundService.get(id);
    if (cBound == null) {
      throw new Exception("数据不存在");
    }
    cBoundVo.merge(cBound);
    cBoundService.update(cBound);

    result.put(RESULT, SUCCESS);
    return result;
  }

  @ApiOperation(value = "创建 CBound 匹配")
  @PutMapping("/pairs")
  public Map<String, Object> putPairs(@RequestBody PairRequestVo pairRequestVo) throws Exception {
    Map<String, Object> result = new HashMap<>();

    Long inId = pairRequestVo.getInId();
    if (inId == null) {
      throw new Exception("参数错误");
    }
    Long outId = pairRequestVo.getOutId();
    if (inId == null) {
      throw new Exception("参数错误");
    }

    CBound inCBound = cBoundService.get(inId);
    if (inCBound == null) {
      throw new Exception("数据不存在");
    }
    CBound outCBound = cBoundService.get(outId);
    if (outCBound == null) {
      throw new Exception("数据不存在");
    }

    cBoundService.updatePairs(inId, outId);

    result.put(RESULT, SUCCESS);
    return result;
  }

  @ApiOperation(value = "删除 CBound 匹配")
  @PostMapping("/pairs/batchDelete")
  public Map<String, Object> pairBatchDelete(@RequestBody IdsVo ids) {
    Map<String, Object> result = new HashMap<>();

    cBoundService.deletePairIds(ids.getIds());

    result.put(RESULT, SUCCESS);
    return result;
  }
}
