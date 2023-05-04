package io.github.natanaeldepaulo.api.application.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Optional;

@Data
public class UpdatePostRequest {
    private Optional<String> title;
    private Optional<String> description;
}
