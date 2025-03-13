package biz.birque.coding.test.entity;

import biz.birque.coding.test.entity.empeddables.TitlesCompositeKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jdk.jfr.Description;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Description("직급 부서장 정보")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "titles")
@Entity
public class titles {

    @EmbeddedId
    private TitlesCompositeKey id;
    private LocalDate toDate;
}
