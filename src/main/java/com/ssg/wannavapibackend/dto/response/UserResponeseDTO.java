package com.ssg.wannavapibackend.dto.response;

import com.ssg.wannavapibackend.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponeseDTO {

    private Long id;
    private String profile;
    private String nickName;
    private String email;
}
