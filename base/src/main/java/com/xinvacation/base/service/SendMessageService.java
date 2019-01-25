package com.xinvacation.base.service;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class SendMessageService {

    @Value("${message.app-id}")
    int appId;

    @Value("${message.app-key}")
    String appKey;
    
    @Value("${message.sms-sign}")
    String smsSign;
    
    private static final String NATION_CODE = "86";
    
    public SmsSingleSenderResult sendMessage(String[]params, String phone,int templateId) throws Exception{
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        SmsSingleSenderResult result = sender.sendWithParam(NATION_CODE, phone,
                    templateId, params, smsSign, "", "");
        return result;
    }
}
