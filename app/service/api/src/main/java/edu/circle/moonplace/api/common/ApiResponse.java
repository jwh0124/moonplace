package edu.circle.moonplace.api.common;

import edu.circle.moonplace.api.common.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<E> {

    private int status;

    private String code;

    private String message = null;

    private E data = null;

    public ApiResponse(E data) {
        this.data = data;
    }

    public ApiResponse(StatusEnum status, E data) {
        this.status = status.getStatusCode();
        this.code = status.getCode();
        this.data = data;
    }

    public ApiResponse(StatusEnum status, String message) {
        this.status = status.getStatusCode();
        this.code = status.getCode();
        this.message = message;
    }

}
