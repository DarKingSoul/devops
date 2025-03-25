package org.dev.devops.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja los atributos de error
 */
@Component
public class CustomAttributes extends DefaultErrorAttributes {

    /**
     * Metodo que maneja los atributos de error
     * @param request the source request
     * @param options options for error attribute contents
     * @return a map of error attributes
     */
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable throwable = super.getError(request);
        if(throwable instanceof CustomException) {
            CustomException customException = (CustomException) throwable;
            errorAttributes.put("status", customException.getStatus());
            errorAttributes.put("message", customException.getMessage());
        }
        return errorAttributes;
    }
}
