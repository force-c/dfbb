package com.yasso.dfbb.rocketmq;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/21 11:32
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@Getter
@Setter
public class TheMessage {
    private String name;
    private int size;
}
