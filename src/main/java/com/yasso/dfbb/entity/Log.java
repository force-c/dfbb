package com.yasso.dfbb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("Log")
public class Log {

    @TableId
    private Integer id;

    private String logName;
}
