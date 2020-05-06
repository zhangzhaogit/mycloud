package common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 统一返回对象
 * @Author zhangzhao
 * @Date 9:25 2020/5/3
 */
@Data
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private  T      data;

    public CommonResult (){
        this.code = ResultCode.SUCCESS.getCode();
        this.message = ResultCode.SUCCESS.getInfo();
    }

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

    public CommonResult(T payment) {
        this.data = payment;
    }
}