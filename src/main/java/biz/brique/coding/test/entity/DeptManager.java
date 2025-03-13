package biz.brique.coding.test.entity;

import biz.brique.coding.test.entity.common.PeriodDate;
import biz.brique.coding.test.entity.empeddables.DeptCompositeKey;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Description("직원 부서장 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "dept_manager")
@Entity
public class DeptManager {

    @EmbeddedId
    private DeptCompositeKey id;

    @Embedded
    private PeriodDate period;
}

