package com.example.skgasutils.Utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommonRes {
    //응답 데이터
    private Object data;
    //응답 상태
    private String status;
    //응답 상태 메세지
    private String msg;
}
