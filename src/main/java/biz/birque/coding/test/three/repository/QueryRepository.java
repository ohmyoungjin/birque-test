package biz.birque.coding.test.three.repository;

import biz.birque.coding.test.dto.QueryDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QueryRepository {

    List<QueryDto> problemThree();
}
