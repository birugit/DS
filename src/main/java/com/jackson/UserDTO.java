package com.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author swamy on 12/21/20
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO implements Serializable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String firstName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;

}
