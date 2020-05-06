package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
 * @Description  付款实体
 * @Author zhangzhao
 * @Date 9:29 2020/5/3
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
//    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String serial;


}
