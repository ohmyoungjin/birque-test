package biz.birque.coding.test.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    M("MALE", "남자"),
    F("FEMALE", "여자"),
    ;

    private final String description;
    private final String memo;
}
