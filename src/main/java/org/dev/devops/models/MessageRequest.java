package org.dev.devops.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequest {

    private String message;
    private String to;
    private String from;
    private Integer timeToLifeSec;

}
