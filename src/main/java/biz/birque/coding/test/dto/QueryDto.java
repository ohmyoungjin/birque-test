package biz.birque.coding.test.dto;

import biz.birque.coding.test.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@ToString
/**
 * 쿼리 반환값 객체
 */
public class QueryDto {

    private int empNo;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate hireDate;
    private String deptName;
    private String title;
    private int maxSalary;


}
