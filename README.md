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

## 每日一句  

每日一句有很多推荐的数据来源
-  彩虹屁: https://chp.shadiao.app
