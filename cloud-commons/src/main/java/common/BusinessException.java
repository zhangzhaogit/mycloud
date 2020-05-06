package common;

/**
 * @Description 业务异常类
 * @Author zhangzhao
 * @Date 9:24 2020/5/3
 */
public class BusinessException extends RuntimeException{
    private int code;

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}