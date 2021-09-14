package com.hcm.cms.alipay.TestDemo;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.junit.Test;

/**
 * @Author 华春萌
 * @Date 2021/7/9 16:02
 * @Version 1.0
 */
public class PhoneWebPay {
    /**
     * 手机网站支付
     */
    @Test
    public void Test01() {
        // 商户appid
        String APP_ID = "2016101300674261";
        // 私钥 pkcs8格式的
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNRnr1HetBz+fU2oaSmUlFBrXly+fZ8yyLwoUMOinrE5zozZ/Z2XpFgloyCXecjZoOQh7I2W9BcjACCfXXun1vG2t8C5is6ITxFbwLAEXDGnvA1sUXHNdFM/H2JUjpvTFEBwJx877yjyIjJKZbtAqW9bzSi53WkgtcUnuoN/9DtDtwbOMEoKybtpetAvLlG8+8Xo7OujTqVT6awHnZ5H6tfSYZjHa2/7ilYhzUQ+jlfZTyI0lfAkwyiB1wl03k0m6acqfMaScNGyuZ4oqV5At05MCPJYxtU6wyIuaTBJvyEy0QWav/fMLJrRnloFj4C+kOMnDnY6fcC8izDo4A5cuXAgMBAAECggEALjpi37Rzl+x1OvRq1qusCxdADDrl2i61Y+I58R9TOiYH0TH/3aRJW1dlNjxc1HCNeZ7vPd/QhQTdar1hob7XbrT1Pfj5c5L+dXkcOClVqpsO5bIhegeo9W8i/N2Ed1++MDlu9mTBFdP6Yq+KdDV8xZsRt3+SHXVanFItPoVcVQW3e0v7jKsFq2Yh6vI1Rx4PuIcX2kF2uW+DNFZyNiVKSgAoEQUj3scvMz6uvxR7WwxSFHoIYZvhT+xhxB8+ATBGXa3f55om6beTPCsvbcJCfub0T7umFssGHVYsrOPyL5h0OCu2MtQfDWLXOwKCU3VD75ogTuyuQx/V92rVDRyAyQKBgQC/MopPsWBkrKG+8J8Ez1k/I9Yh4hpgnxszhLrTmqFIMzI+KvtJIz1Fy/GdoiyB2aCVCpb1U98waVY7dg/yMYE9xJ+m0mfy9IJVws9lhiIFiDN8NzVYbXN9ZtjRczT/M53G2NkGOMR/zVxgsHcdUgc7YJUr/Q/0LPGoHo2pUK/gxQKBgQC9KGRZnIploSG/Tty2C+I2jlo5BxwihYbXdUOD1g0eZXHDBN0pYEvZhNDYOn4hrtyp8eJ7qA16jFpd1hVaeknOh8Ydw6Du6GZBstnx7AI+cmATzy6+8miqXfA0b4KmdGP/IQBKft0yoAokOrD1YnsO3PIiUd+DB20zCE/M+BqIqwKBgBfIWZwAHFVpg9UUaTNX92n7My+TyGQsZl3KLNMVcOpiM+W3wvoeAAkmjS9mdNLSFz9yycRKYoDLGYbb8K/XqJPeWV2O3o9CCtRQHywpuyGlB4Jz+5a8Rplaqa2j/vufmAicAKLdfNPI36zHRonHIk8ofGW0XL+qt4SKCGT/PGFhAoGAbo8KQDo3xfjtAaU1aY3+lQaVA1n9fsvuqUYXxRm6JzcBzAuuvH1VN/jiHE/r4wISZZ5EUyRBZIxeURRM/MoXE4jWKhVqndjl06aWrkjUcu1k7I8e8mo69MDdIbi/c0z6GWePhPsrjGhwJQbM4U/DP0eymo6iBjK0B6zm/5VP5OsCgYBfW3THvcHL9XDO9wgiklha7oCsTN+HHUKJ3ZhuCa4Xn/p8JC7XbDf8aFsMpP3qBUJV4hwwM1Nrjn3HFMjZtbXOFrwRyMJ1OoVFOxDOCPPkMqqvqpCPTS8Iy5M+CAaw0RBIyUzDfAP4K4zQp1zhPwtEizFd7CLeShkGXVnqwbvBoQ==";
        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String notify_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/notify_url.jsp";
        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        String return_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/return_url.jsp";
        // 请求网关地址
        String URL = "https://openapi.alipaydev.com/gateway.do";
        // 编码
        String CHARSET = "UTF-8";
        // 返回格式
        String FORMAT = "json";
        // 支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkPCblCoBOoy3UHzJd1K2a2hUvd6Cdg/C85hkma5g4Jx6TbzOJ3bmXdWcJgf2EKo+YiD6A3JV+Lxf1neT84Ah4Mg4Gn1c+qB8yQyhmNK9mowDcD468/FvrD9bxaeaES8nN/A8+iVWb841Apivyhz5nxqJCwAiKNiCXnNbeBFsC7i7wxCcw7jF+tCrQ1Nka708TxxdsfkXU9BpZdtX2OgrWaylSK/CiRKKrGYxwK5kTgKca4J13nfl/L1EBSHXhuLQ3scrHuiuuOju55BZOtswYhska/BLvoyPOispL7/ZS3h+u24nUcbgT1GWbspFYmFNbmpUPbtG7mH2TqVgw4+SxwIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
        // 日志记录目录
        String log_path = "/log";
        // RSA2
        String SIGNTYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
        alipayTradePrecreateModel.setOutTradeNo("1625820672798");
        alipayTradePrecreateModel.setTotalAmount("100.00");
        alipayTradePrecreateModel.setSubject("测试支付");
//        alipayTradePrecreateModel.setBody(requestData.getBody());
        request.setBizModel(alipayTradePrecreateModel);
        alipayTradePrecreateModel.setTimeoutExpress("2m"); //允许2分钟之内完成支付
        AlipayTradeWapPayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

    /**
     * 电脑下单支付
     */
    @Test
    public void Test02() {
        // 商户appid
        String APP_ID = "2016101300674261";
        // 私钥 pkcs8格式的
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNRnr1HetBz+fU2oaSmUlFBrXly+fZ8yyLwoUMOinrE5zozZ/Z2XpFgloyCXecjZoOQh7I2W9BcjACCfXXun1vG2t8C5is6ITxFbwLAEXDGnvA1sUXHNdFM/H2JUjpvTFEBwJx877yjyIjJKZbtAqW9bzSi53WkgtcUnuoN/9DtDtwbOMEoKybtpetAvLlG8+8Xo7OujTqVT6awHnZ5H6tfSYZjHa2/7ilYhzUQ+jlfZTyI0lfAkwyiB1wl03k0m6acqfMaScNGyuZ4oqV5At05MCPJYxtU6wyIuaTBJvyEy0QWav/fMLJrRnloFj4C+kOMnDnY6fcC8izDo4A5cuXAgMBAAECggEALjpi37Rzl+x1OvRq1qusCxdADDrl2i61Y+I58R9TOiYH0TH/3aRJW1dlNjxc1HCNeZ7vPd/QhQTdar1hob7XbrT1Pfj5c5L+dXkcOClVqpsO5bIhegeo9W8i/N2Ed1++MDlu9mTBFdP6Yq+KdDV8xZsRt3+SHXVanFItPoVcVQW3e0v7jKsFq2Yh6vI1Rx4PuIcX2kF2uW+DNFZyNiVKSgAoEQUj3scvMz6uvxR7WwxSFHoIYZvhT+xhxB8+ATBGXa3f55om6beTPCsvbcJCfub0T7umFssGHVYsrOPyL5h0OCu2MtQfDWLXOwKCU3VD75ogTuyuQx/V92rVDRyAyQKBgQC/MopPsWBkrKG+8J8Ez1k/I9Yh4hpgnxszhLrTmqFIMzI+KvtJIz1Fy/GdoiyB2aCVCpb1U98waVY7dg/yMYE9xJ+m0mfy9IJVws9lhiIFiDN8NzVYbXN9ZtjRczT/M53G2NkGOMR/zVxgsHcdUgc7YJUr/Q/0LPGoHo2pUK/gxQKBgQC9KGRZnIploSG/Tty2C+I2jlo5BxwihYbXdUOD1g0eZXHDBN0pYEvZhNDYOn4hrtyp8eJ7qA16jFpd1hVaeknOh8Ydw6Du6GZBstnx7AI+cmATzy6+8miqXfA0b4KmdGP/IQBKft0yoAokOrD1YnsO3PIiUd+DB20zCE/M+BqIqwKBgBfIWZwAHFVpg9UUaTNX92n7My+TyGQsZl3KLNMVcOpiM+W3wvoeAAkmjS9mdNLSFz9yycRKYoDLGYbb8K/XqJPeWV2O3o9CCtRQHywpuyGlB4Jz+5a8Rplaqa2j/vufmAicAKLdfNPI36zHRonHIk8ofGW0XL+qt4SKCGT/PGFhAoGAbo8KQDo3xfjtAaU1aY3+lQaVA1n9fsvuqUYXxRm6JzcBzAuuvH1VN/jiHE/r4wISZZ5EUyRBZIxeURRM/MoXE4jWKhVqndjl06aWrkjUcu1k7I8e8mo69MDdIbi/c0z6GWePhPsrjGhwJQbM4U/DP0eymo6iBjK0B6zm/5VP5OsCgYBfW3THvcHL9XDO9wgiklha7oCsTN+HHUKJ3ZhuCa4Xn/p8JC7XbDf8aFsMpP3qBUJV4hwwM1Nrjn3HFMjZtbXOFrwRyMJ1OoVFOxDOCPPkMqqvqpCPTS8Iy5M+CAaw0RBIyUzDfAP4K4zQp1zhPwtEizFd7CLeShkGXVnqwbvBoQ==";
        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String notify_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/notify_url.jsp";
        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        String return_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/return_url.jsp";
        // 请求网关地址
        String URL = "https://openapi.alipaydev.com/gateway.do";
        // 编码
        String CHARSET = "UTF-8";
        // 返回格式
        String FORMAT = "json";
        // 支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkPCblCoBOoy3UHzJd1K2a2hUvd6Cdg/C85hkma5g4Jx6TbzOJ3bmXdWcJgf2EKo+YiD6A3JV+Lxf1neT84Ah4Mg4Gn1c+qB8yQyhmNK9mowDcD468/FvrD9bxaeaES8nN/A8+iVWb841Apivyhz5nxqJCwAiKNiCXnNbeBFsC7i7wxCcw7jF+tCrQ1Nka708TxxdsfkXU9BpZdtX2OgrWaylSK/CiRKKrGYxwK5kTgKca4J13nfl/L1EBSHXhuLQ3scrHuiuuOju55BZOtswYhska/BLvoyPOispL7/ZS3h+u24nUcbgT1GWbspFYmFNbmpUPbtG7mH2TqVgw4+SxwIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
        // 日志记录目录
        String log_path = "/log";
        // RSA2
        String SIGNTYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
        alipayTradePrecreateModel.setOutTradeNo("1625822681179");
        alipayTradePrecreateModel.setTotalAmount("100.00");
        alipayTradePrecreateModel.setSubject("测试支付");
        alipayTradePrecreateModel.setProductCode("FAST_INSTANT_TRADE_PAY");
//        alipayTradePrecreateModel.setBody(requestData.getBody());
        request.setBizModel(alipayTradePrecreateModel);
        alipayTradePrecreateModel.setTimeoutExpress("2m"); //允许2分钟之内完成支付
        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 当面付交易创建接口
     */
    @Test
    public void Test03() {
        // 商户appid
        String APP_ID = "2016101300674261";
        // 私钥 pkcs8格式的
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNRnr1HetBz+fU2oaSmUlFBrXly+fZ8yyLwoUMOinrE5zozZ/Z2XpFgloyCXecjZoOQh7I2W9BcjACCfXXun1vG2t8C5is6ITxFbwLAEXDGnvA1sUXHNdFM/H2JUjpvTFEBwJx877yjyIjJKZbtAqW9bzSi53WkgtcUnuoN/9DtDtwbOMEoKybtpetAvLlG8+8Xo7OujTqVT6awHnZ5H6tfSYZjHa2/7ilYhzUQ+jlfZTyI0lfAkwyiB1wl03k0m6acqfMaScNGyuZ4oqV5At05MCPJYxtU6wyIuaTBJvyEy0QWav/fMLJrRnloFj4C+kOMnDnY6fcC8izDo4A5cuXAgMBAAECggEALjpi37Rzl+x1OvRq1qusCxdADDrl2i61Y+I58R9TOiYH0TH/3aRJW1dlNjxc1HCNeZ7vPd/QhQTdar1hob7XbrT1Pfj5c5L+dXkcOClVqpsO5bIhegeo9W8i/N2Ed1++MDlu9mTBFdP6Yq+KdDV8xZsRt3+SHXVanFItPoVcVQW3e0v7jKsFq2Yh6vI1Rx4PuIcX2kF2uW+DNFZyNiVKSgAoEQUj3scvMz6uvxR7WwxSFHoIYZvhT+xhxB8+ATBGXa3f55om6beTPCsvbcJCfub0T7umFssGHVYsrOPyL5h0OCu2MtQfDWLXOwKCU3VD75ogTuyuQx/V92rVDRyAyQKBgQC/MopPsWBkrKG+8J8Ez1k/I9Yh4hpgnxszhLrTmqFIMzI+KvtJIz1Fy/GdoiyB2aCVCpb1U98waVY7dg/yMYE9xJ+m0mfy9IJVws9lhiIFiDN8NzVYbXN9ZtjRczT/M53G2NkGOMR/zVxgsHcdUgc7YJUr/Q/0LPGoHo2pUK/gxQKBgQC9KGRZnIploSG/Tty2C+I2jlo5BxwihYbXdUOD1g0eZXHDBN0pYEvZhNDYOn4hrtyp8eJ7qA16jFpd1hVaeknOh8Ydw6Du6GZBstnx7AI+cmATzy6+8miqXfA0b4KmdGP/IQBKft0yoAokOrD1YnsO3PIiUd+DB20zCE/M+BqIqwKBgBfIWZwAHFVpg9UUaTNX92n7My+TyGQsZl3KLNMVcOpiM+W3wvoeAAkmjS9mdNLSFz9yycRKYoDLGYbb8K/XqJPeWV2O3o9CCtRQHywpuyGlB4Jz+5a8Rplaqa2j/vufmAicAKLdfNPI36zHRonHIk8ofGW0XL+qt4SKCGT/PGFhAoGAbo8KQDo3xfjtAaU1aY3+lQaVA1n9fsvuqUYXxRm6JzcBzAuuvH1VN/jiHE/r4wISZZ5EUyRBZIxeURRM/MoXE4jWKhVqndjl06aWrkjUcu1k7I8e8mo69MDdIbi/c0z6GWePhPsrjGhwJQbM4U/DP0eymo6iBjK0B6zm/5VP5OsCgYBfW3THvcHL9XDO9wgiklha7oCsTN+HHUKJ3ZhuCa4Xn/p8JC7XbDf8aFsMpP3qBUJV4hwwM1Nrjn3HFMjZtbXOFrwRyMJ1OoVFOxDOCPPkMqqvqpCPTS8Iy5M+CAaw0RBIyUzDfAP4K4zQp1zhPwtEizFd7CLeShkGXVnqwbvBoQ==";
        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String notify_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/notify_url.jsp";
        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        String return_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/return_url.jsp";
        // 请求网关地址
        String URL = "https://openapi.alipaydev.com/gateway.do";
        // 编码
        String CHARSET = "UTF-8";
        // 返回格式
        String FORMAT = "json";
        // 支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkPCblCoBOoy3UHzJd1K2a2hUvd6Cdg/C85hkma5g4Jx6TbzOJ3bmXdWcJgf2EKo+YiD6A3JV+Lxf1neT84Ah4Mg4Gn1c+qB8yQyhmNK9mowDcD468/FvrD9bxaeaES8nN/A8+iVWb841Apivyhz5nxqJCwAiKNiCXnNbeBFsC7i7wxCcw7jF+tCrQ1Nka708TxxdsfkXU9BpZdtX2OgrWaylSK/CiRKKrGYxwK5kTgKca4J13nfl/L1EBSHXhuLQ3scrHuiuuOju55BZOtswYhska/BLvoyPOispL7/ZS3h+u24nUcbgT1GWbspFYmFNbmpUPbtG7mH2TqVgw4+SxwIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
        // 日志记录目录
        String log_path = "/log";
        // RSA2
        String SIGNTYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
        alipayTradePrecreateModel.setOutTradeNo("1626059061201");
        alipayTradePrecreateModel.setSellerId("2088102179340535");
        alipayTradePrecreateModel.setTotalAmount("100.00");
        alipayTradePrecreateModel.setSubject("测试扫码支付");
        request.setBizModel(alipayTradePrecreateModel);
        alipayTradePrecreateModel.setTimeoutExpress("2m"); //允许2分钟之内完成支付
        AlipayTradePrecreateResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试退款接口
     */
    @Test
    public void Test04() {
        // 商户appid
        String APP_ID = "2016101300674261";
        // 私钥 pkcs8格式的
        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNRnr1HetBz+fU2oaSmUlFBrXly+fZ8yyLwoUMOinrE5zozZ/Z2XpFgloyCXecjZoOQh7I2W9BcjACCfXXun1vG2t8C5is6ITxFbwLAEXDGnvA1sUXHNdFM/H2JUjpvTFEBwJx877yjyIjJKZbtAqW9bzSi53WkgtcUnuoN/9DtDtwbOMEoKybtpetAvLlG8+8Xo7OujTqVT6awHnZ5H6tfSYZjHa2/7ilYhzUQ+jlfZTyI0lfAkwyiB1wl03k0m6acqfMaScNGyuZ4oqV5At05MCPJYxtU6wyIuaTBJvyEy0QWav/fMLJrRnloFj4C+kOMnDnY6fcC8izDo4A5cuXAgMBAAECggEALjpi37Rzl+x1OvRq1qusCxdADDrl2i61Y+I58R9TOiYH0TH/3aRJW1dlNjxc1HCNeZ7vPd/QhQTdar1hob7XbrT1Pfj5c5L+dXkcOClVqpsO5bIhegeo9W8i/N2Ed1++MDlu9mTBFdP6Yq+KdDV8xZsRt3+SHXVanFItPoVcVQW3e0v7jKsFq2Yh6vI1Rx4PuIcX2kF2uW+DNFZyNiVKSgAoEQUj3scvMz6uvxR7WwxSFHoIYZvhT+xhxB8+ATBGXa3f55om6beTPCsvbcJCfub0T7umFssGHVYsrOPyL5h0OCu2MtQfDWLXOwKCU3VD75ogTuyuQx/V92rVDRyAyQKBgQC/MopPsWBkrKG+8J8Ez1k/I9Yh4hpgnxszhLrTmqFIMzI+KvtJIz1Fy/GdoiyB2aCVCpb1U98waVY7dg/yMYE9xJ+m0mfy9IJVws9lhiIFiDN8NzVYbXN9ZtjRczT/M53G2NkGOMR/zVxgsHcdUgc7YJUr/Q/0LPGoHo2pUK/gxQKBgQC9KGRZnIploSG/Tty2C+I2jlo5BxwihYbXdUOD1g0eZXHDBN0pYEvZhNDYOn4hrtyp8eJ7qA16jFpd1hVaeknOh8Ydw6Du6GZBstnx7AI+cmATzy6+8miqXfA0b4KmdGP/IQBKft0yoAokOrD1YnsO3PIiUd+DB20zCE/M+BqIqwKBgBfIWZwAHFVpg9UUaTNX92n7My+TyGQsZl3KLNMVcOpiM+W3wvoeAAkmjS9mdNLSFz9yycRKYoDLGYbb8K/XqJPeWV2O3o9CCtRQHywpuyGlB4Jz+5a8Rplaqa2j/vufmAicAKLdfNPI36zHRonHIk8ofGW0XL+qt4SKCGT/PGFhAoGAbo8KQDo3xfjtAaU1aY3+lQaVA1n9fsvuqUYXxRm6JzcBzAuuvH1VN/jiHE/r4wISZZ5EUyRBZIxeURRM/MoXE4jWKhVqndjl06aWrkjUcu1k7I8e8mo69MDdIbi/c0z6GWePhPsrjGhwJQbM4U/DP0eymo6iBjK0B6zm/5VP5OsCgYBfW3THvcHL9XDO9wgiklha7oCsTN+HHUKJ3ZhuCa4Xn/p8JC7XbDf8aFsMpP3qBUJV4hwwM1Nrjn3HFMjZtbXOFrwRyMJ1OoVFOxDOCPPkMqqvqpCPTS8Iy5M+CAaw0RBIyUzDfAP4K4zQp1zhPwtEizFd7CLeShkGXVnqwbvBoQ==";
        // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
        String notify_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/notify_url.jsp";
        // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
        String return_url = "http://localhost:8080/alipay.trade.wap.pay-java-utf-8/return_url.jsp";
        // 请求网关地址
        String URL = "https://openapi.alipaydev.com/gateway.do";
        // 编码
        String CHARSET = "UTF-8";
        // 返回格式
        String FORMAT = "json";
        // 支付宝公钥
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkPCblCoBOoy3UHzJd1K2a2hUvd6Cdg/C85hkma5g4Jx6TbzOJ3bmXdWcJgf2EKo+YiD6A3JV+Lxf1neT84Ah4Mg4Gn1c+qB8yQyhmNK9mowDcD468/FvrD9bxaeaES8nN/A8+iVWb841Apivyhz5nxqJCwAiKNiCXnNbeBFsC7i7wxCcw7jF+tCrQ1Nka708TxxdsfkXU9BpZdtX2OgrWaylSK/CiRKKrGYxwK5kTgKca4J13nfl/L1EBSHXhuLQ3scrHuiuuOju55BZOtswYhska/BLvoyPOispL7/ZS3h+u24nUcbgT1GWbspFYmFNbmpUPbtG7mH2TqVgw4+SxwIDAQAB+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
        // 日志记录目录
        String log_path = "/log";
        // RSA2
        String SIGNTYPE = "RSA2";
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no","1626059061201");
//        bizContent.put("trade_no", "2021071222001448200501292921");
        bizContent.put("refund_amount", 5);
        bizContent.put("out_request_no", "HZ01RF003");
        //// 返回参数选项，按需传入
        //JSONArray queryOptions = new JSONArray();
        //queryOptions.add("refund_detail_item_list");
        //bizContent.put("query_options", queryOptions);
        request.setBizContent(bizContent.toString());
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }
}
