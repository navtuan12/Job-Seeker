package com.navtuan12.job_seeker_server.dto.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> {
    private boolean success;
    private int code;
    private String message;
    private T result;
    private Map<String, Object> additionalProperties = new HashMap<>();

    // public ApiResponse(boolean success, String message, int code) {
    //     this.success = success;
    //     this.message = message;
    //     this.code = code;
    // }
    
    // Methods to manage extra fields
    @JsonAnySetter
    public void addAdditionalProperty(String key, Object value) {
        this.additionalProperties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}