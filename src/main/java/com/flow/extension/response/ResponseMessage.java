package com.flow.extension.response;

import com.flow.extension.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
    ResponseStatus status;
    Object data;

    public ResponseMessage(ResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }
    public ResponseMessage(ResponseStatus status) {
        this.status = status;
        this.data = data;
    }
    public static ResponseMessage success(Object data) {
        return new ResponseMessage(ResponseStatus.SUCCESS, data);
    }

    public static ResponseMessage success() {
        return new ResponseMessage(ResponseStatus.SUCCESS);
    }
}
