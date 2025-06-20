package com.example.test.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    @NotNull
    @Min(1)
    private Integer pageNumber;
    @NotNull
    @Min(1)
    private Integer pageSize;
}
