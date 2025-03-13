package biz.brique.coding.test.entity.empeddables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
public class DeptCompositeKey implements Serializable {

    private int empNo;

    private String deptNo;
}
