package com.example.test.response;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class SearchResponse<T> {
    @NonNull
    private String responseStatus;
    @NonNull
    private String message;
    @NonNull
    private Integer pageNo;
    @NonNull
    private Integer pageSize;
    @NonNull
    private List<T> listData;
}
