package biz.brique.coding.test.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@ToString
/**
 * 요청에 대한 응답 객체
 */
public class RandomDto {

    private int id;
    private String quote;
}
