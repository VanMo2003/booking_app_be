package com.example.booking_app.config;

import com.example.booking_app.utils.VNPayUtil;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Configuration
public class VNPayConfig {
    @Value("${vnpay.url}")
    String vnp_PayUrl;
    @Value("${vnpay.returnUrl}")
    String vnp_ReturnUrl;
    @Value("${vnpay.tmnCode}")
    String vnp_TmnCode;
    @Value("${vnpay.secretKey}")
    String secretKey;
    @Value("${vnpay.version}")
    String vnp_Version;
    @Value("${vnpay.command}")
    String vnp_Command;
    @Value("${vnpay.orderType}")
    String orderType;

    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef",  VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang:" +  VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", this.orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnpParamsMap;
    }

//    public static String hash(String key, String data) throws Exception {
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
//        byte[] bytes = sha256_HMAC.doFinal(data.getBytes());
//        StringBuilder hash = new StringBuilder();
//        for (byte b : bytes) {
//            hash.append(String.format("%02x", b));
//        }
//        return hash.toString();
//    }
}
