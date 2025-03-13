package biz.birque.coding.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Description("부서 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "departments")
@Entity
public class Departments {


    @Comment("부서 번호")
    @Id
    @Column(name = "dept_no", nullable = false)
    private String deptNo;

    @Comment("부서 이름")
    @Column(name = "dept_name", nullable = false)
    private String deptName;


}
