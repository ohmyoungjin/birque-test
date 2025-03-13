package biz.brique.coding.test.entity.empeddables;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@Getter
public class TitlesCompositeKey implements Serializable {

    private int empNo;

    private String title;

    private LocalDate fromDate;
}
