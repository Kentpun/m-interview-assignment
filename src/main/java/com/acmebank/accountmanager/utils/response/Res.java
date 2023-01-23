package com.acmebank.accountmanager.utils.response;

public class Res {
    public static <T> Result<T> success() {
        return new Result<>(SystemResponseMessage.SYS0000);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SystemResponseMessage.SYS0000, data);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(SystemResponseMessage.SYS9999, data);
    }

    public static <T> Result<T> error(Response response, T data) {
        return new Result<>(response, data);
    }

    public static <T> Result<T> error(Response response) {
        return new Result<>(response);
    }
}
