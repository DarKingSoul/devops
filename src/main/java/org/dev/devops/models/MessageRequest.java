package org.dev.devops.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequest {

    private String message;
    private String to;
    private String from;
    private Integer timeToLifeSec;

}
