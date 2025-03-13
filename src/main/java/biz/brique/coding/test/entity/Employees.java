package biz.brique.coding.test.entity;

import biz.brique.coding.test.enums.Gender;
import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Description("직원 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Employees")
@Entity
public class Employees {

    @Comment("직원 번호")
    @Id
    @Column(name = "emp_no", nullable = false)
    private int empNo;

    @Comment("생일")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Comment("성")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Comment("이름")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Comment("성별")
    @Column(name = "gender", nullable = false, length = 1)
    private Gender gender;

    @Comment("입사일")
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

}
