package biz.brique.coding.test.entity.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

/* 테이블 중복 컬럼에 대하여 같이 쓰일 객체
   DeptEmp, DeptManager
*/
@Getter
@Embeddable
public class PeriodDate {
    @NotNull
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;


}
