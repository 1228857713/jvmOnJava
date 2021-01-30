package com.wanghzen.api;

import lombok.*;

import java.io.Serializable;

/**
 * Description:
 * Datetime:    2020/11/29   下午3:54
 * Author:   王震
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hello implements Serializable {
    String message;
    String description;
}
