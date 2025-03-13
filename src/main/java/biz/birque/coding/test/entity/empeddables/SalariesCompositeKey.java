package biz.birque.coding.test.entity.empeddables;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Getter
public class SalariesCompositeKey implements Serializable {

    private int empNo;
    private LocalDate fromDate;

}
