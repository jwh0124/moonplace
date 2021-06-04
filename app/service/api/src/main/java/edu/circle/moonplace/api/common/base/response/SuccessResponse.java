package edu.circle.moonplace.api.common.base.response;

import edu.circle.moonplace.api.common.base.BaseResponse;
import edu.circle.moonplace.api.common.enums.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SuccessResponse<T> extends BaseResponse {

    private StatusEnum status = StatusEnum.OK;

    private T data;

    public SuccessResponse(T data) {
        this.data = data;
    }
}
