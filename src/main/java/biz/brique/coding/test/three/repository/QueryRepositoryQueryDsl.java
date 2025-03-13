package biz.brique.coding.test.three.repository;


import biz.brique.coding.test.dto.QueryResponseDto;
import biz.brique.coding.test.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryRepositoryQueryDsl implements QueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QDepartments departments = QDepartments.departments;
    private final QDeptEmp deptEmp = QDeptEmp.deptEmp;
    private final QEmployees employees = QEmployees.employees;
    private final Qtitles titles = Qtitles.titles;
    private final QSalaries salaries = QSalaries.salaries;

    @Override
    public List<QueryResponseDto> problemThree() {
        return queryFactory.select(Projections.constructor(QueryResponseDto.class,
                        employees.empNo,
                        employees.firstName,
                        employees.lastName,
                        employees.gender,
                        employees.hireDate,
                        departments.deptName,
                        titles.id.title,
                        salaries.salary.max()
                ))
                .from(employees)
                .join(deptEmp).on(deptEmp.id.empNo.eq(employees.empNo))
                .join(departments).on(departments.deptNo.eq(deptEmp.id.deptNo))
                .join(titles).on(titles.id.empNo.eq(employees.empNo))
                .join(salaries).on(salaries.id.empNo.eq(employees.empNo))
                .where(employees.hireDate.goe(LocalDate.of(2000, 1, 1)))
                .groupBy(employees.empNo)
                .fetch();
    }
}
