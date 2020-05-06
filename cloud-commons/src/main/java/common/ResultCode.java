package common;

public enum ResultCode {
    SUCCESS(0, "成功"),
    WRONG(1, "操作失败");

    private int code;
    private String info;

    private ResultCode(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }
}
