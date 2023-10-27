package com.ssafy.member.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MemberDTO {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Request{
        private Long id;
        private String name;
        private String email;
        private String password;
        private String phoneNumber;
        private String birth;
        private String fingerPrint;
        private String pinNumber;
    }
}
