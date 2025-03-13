package biz.brique.coding.test.three.repository;

import biz.brique.coding.test.dto.QueryResponseDto;

import java.util.List;
/* 사용 기술이 달라질 수 있음에 따라 Interface 선언 */
public interface QueryRepository {

    List<QueryResponseDto> problemThree();
}
