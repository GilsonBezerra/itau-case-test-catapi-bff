package br.com.itau.cat_api_bff.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        List<T> data,
        PaginationResponse pagination
) {
}
