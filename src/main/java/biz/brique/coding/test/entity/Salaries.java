package biz.brique.coding.test.entity;

import biz.brique.coding.test.entity.empeddables.SalariesCompositeKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Description("급여 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "salaries")
@Entity
public class Salaries {

    @EmbeddedId
    private SalariesCompositeKey id;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;
}
