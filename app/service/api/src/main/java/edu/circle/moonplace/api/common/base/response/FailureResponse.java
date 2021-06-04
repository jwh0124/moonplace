package edu.circle.moonplace.api.common.base.response;

import edu.circle.moonplace.api.common.base.BaseResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailureResponse extends BaseResponse {

    private int status;

    private String code;

    private String message = null;

    public FailureResponse(StatusEnum statusEnum, String message) {
        this.status = statusEnum.getStatusCode();
        this.code = statusEnum.getCode();
        this.message = message;
    }
}
