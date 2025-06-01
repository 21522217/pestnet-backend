package uit.app.com.pestnet.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
    private String error;
    private Pagination pagination;

    public static <T> ApiResponse<T> error(String message, int status) {
        return ApiResponse.<T>builder()
                .status(status)
                .message(message)
                .error(message)
                .data(null)
                .pagination(null)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pagination {
        private int pageNumber;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }
}
