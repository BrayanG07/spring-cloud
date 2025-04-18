package hn.springcloud.msvc.libs.commons.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse {
    private int code;
    private String message;
    private String description;
    private Object data;
}
