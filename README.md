# ererydayMail
基于Spring Boot的每日定时发送邮件。包括三部分，自定义内容，天气，每日一句。

## 自定义内容
FreeMarker模板引擎
- email.ftl
```html
<div>
    <span>今天是认识你的第</span>
    <span>${day}</span>
    <span>天</span>
</div>
```

## 天气
调用**天行API**获取天气类型，温度，空气质量  
调用**SOJSON API**获取温馨提醒

- 天行：https://www.tianapi.com/apiview/72
- SOJSON：https://www.sojson.com/blog/305.html

公用方法
```java
    static String request(String httpUrl, String httpArg) {
        BufferedReader reader;
        String result = null;
        StringBuilder sbf = new StringBuilder();
        httpUrl = httpUrl + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
```

## 每日一句  

每日一句有很多推荐的数据来源
- 彩虹屁: https://chp.shadiao.app
![](https://github.com/yuan0824/img/blob/master/%E5%BD%A9%E8%99%B9%E5%B1%81.png)  
- 一言：https://hitokoto.cn/
![](https://github.com/yuan0824/img/blob/master/%E4%B8%80%E8%A8%80.png)
- 渣男说话的艺术：https://lovelive.tools
![](https://github.com/yuan0824/img/blob/master/%E6%B8%A3%E7%94%B7%E8%AF%B4%E8%AF%9D%E7%9A%84%E8%89%BA%E6%9C%AF.png)
- 一个:http://wufazhuce.com/
![](https://github.com/yuan0824/img/blob/master/%E4%B8%80%E4%B8%AA.png)
