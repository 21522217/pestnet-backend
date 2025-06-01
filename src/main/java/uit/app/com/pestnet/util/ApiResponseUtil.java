package uit.app.com.pestnet.util;

import org.springframework.http.HttpStatus;
import uit.app.com.pestnet.dto.ApiResponse;

import java.util.List;

public class ApiResponseUtil {

    public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
        return ApiResponse.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String errorMessage, HttpStatus status) {
        return ApiResponse.<T>builder()
                .status(status.value())
                .message("Request failed")
                .error(errorMessage)
                .build();
    }

    public static <T> ApiResponse<List<T>> paginated(List<T> data, String message, HttpStatus status, org.springframework.data.domain.Page<?> page) {
        ApiResponse.Pagination pagination = ApiResponse.Pagination.builder()
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();

        return ApiResponse.<List<T>>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .pagination(pagination)
                .build();
    }
}
