package biz.birque.coding.test.entity;


import biz.birque.coding.test.entity.common.PeriodDate;
import biz.birque.coding.test.entity.empeddables.DeptCompositeKey;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Description("직원 부서 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dept_emp")
@Entity
public class DeptEmp {

    @EmbeddedId
    private DeptCompositeKey id;

    @Embedded
    private PeriodDate period;
}
