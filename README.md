# ererydayMail
基于Spring Boot的每日定时发送邮件

## 预览
包括三部分，自定义内容，天气，每日一句。
效果如下  

![](https://github.com/yuan0824/img/blob/master/email-1.png)![](https://github.com/yuan0824/img/blob/master/email-2.png)

### 自定义内容
FreeMarker模板引擎
- email.ftl
```html
<div>
    <span>今天是认识你的第</span>
    <span>${day}</span>
    <span>天</span>
</div>
```

### 天气
调用**天行API**获取天气类型，温度，空气质量  
调用**SOJSON API**获取温馨提醒

- 天行：https://www.tianapi.com/apiview/72
- SOJSON：https://www.sojson.com/blog/305.html

公用方法
```java
String request(String httpUrl, String httpArg) {
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

### 每日一句  

每日一句有很多推荐的数据来源
- 彩虹屁: https://chp.shadiao.app
![](https://github.com/yuan0824/img/blob/master/%E5%BD%A9%E8%99%B9%E5%B1%81.png)  
- 一言：https://hitokoto.cn/
![](https://github.com/yuan0824/img/blob/master/%E4%B8%80%E8%A8%80.png)
- 渣男说话的艺术：https://lovelive.tools
![](https://github.com/yuan0824/img/blob/master/%E6%B8%A3%E7%94%B7%E8%AF%B4%E8%AF%9D%E7%9A%84%E8%89%BA%E6%9C%AF.png)
- 一个:http://wufazhuce.com/
![](https://github.com/yuan0824/img/blob/master/%E4%B8%80%E4%B8%AA.png)

本项目使用的是最后一个，韩寒监制的「ONE·一个」APP
> 复杂世界里，一个就够了

JSoup爬虫爬取每日一句话和一张图
```java
static One getOne() throws IOException, ParseException {
    Date now = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date day = dateFormat.parse("2019-09-01");
    int days = (int) ((now.getTime() - day.getTime()) / (1000*3600*24));
    int value = 2562 + days - 6;
    String url = "http://wufazhuce.com/one/" + value;
    Document document = Jsoup.connect(url).get();

    Elements elements1 = document.select("meta[name=description]");
    String content = elements1.get(0).attributes().get("content");

    Elements elements2 = document.select("meta[property=og:image]");
    String img = elements2.get(0).attributes().get("content");

    One one = new One();
    one.setContext(content);
    one.setImg(img);
    return one;
}
```

## 配置
一般需要配置的文件

### 定时任务
- ScheduledTasks
```java
@Slf4j
@Component
public class ScheduledTasks {
    private final SendEmail sendEmail;

    @Autowired
    public ScheduledTasks(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void reportCurrentTime() throws TemplateException, IOException, MessagingException, ParseException {
        log.info("start send");
        sendEmail.send();
        log.info("end send");
    }
}
```
使用cron表达式表示执行时间，默认每天8点
- 在线Cron表达式生成器: http://cron.qqe2.com/

### 天气预报城市
- .properties
```
city=泸州
```

### 邮箱
- .properties
```
# 使用SMTP协议发送，发件地址
spring.mail.host=smtp.163.com
spring.mail.username=15234076721@163.com
spring.mail.password=xxxx

# 收件邮箱
mailto=15234076721@163.com
```

## Demo
执行send方法可以手动发一封邮件
- MailTest
```
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private SendEmail sendEmail;

    @Test
    public void send() throws MessagingException, IOException, TemplateException, ParseException {
        sendEmail.send();
    }
}
```

## 欢迎
欢迎阅读和review代码

