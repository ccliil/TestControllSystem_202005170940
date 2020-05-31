package com.cdtu.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

public  class SmsProperties {
    private  SmsProperties(){}
    public static String accessKeyId="LTAI4FkQcHbBcExV6Xp8Rpzj";//LTAI4FkQcHbBcExV6Xp8Rpzj
     public static String accessKeySecret="uzklCX57Tzdv54dbrZOlhvs6BS9Q2b";//uzklCX57Tzdv54dbrZOlhvs6BS9Q2b
    public  static String signName="易购商城";
    public  static String verifyCodeTemplate="SMS_173476358";
}
