package com.ssg.wannavapibackend.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageUpdateDTO {

    @Size(min = 2, max = 10, message = "2자 이상 10자 이하만 가능합니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣]+$", message = "한글만 가능합니다.")
    private String name;

    private String zipCode;

    private String roadAddress;

    private String landLotAddress;

    @Size(max = 100, message = "100자 이하만 가능합니다.")
    private String detailAddress;
}
