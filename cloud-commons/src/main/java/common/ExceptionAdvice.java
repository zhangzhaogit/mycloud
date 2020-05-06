package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 全局异常
 * @Author zhangzhao
 * @Date 9:26 2020/5/3
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult errorHandler(Exception ex) {
        CommonResult result=new CommonResult();
        if(ex instanceof BusinessException){
//            ex=  (BusinessException)ex;
            result.setCode(ResultCode.WRONG.getCode());
            result.setMessage(ex.getMessage());

        }else{
            result.setCode(ResultCode.WRONG.getCode());
            result.setMessage(ResultCode.WRONG.getInfo());
        }
        logger.error(ex.getMessage(),ex);
        return result;
    }
}
