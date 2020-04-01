<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
</head>

<body style="margin:0;padding:0;">
<div style="width:100%; margin: 40px auto;font-size:20px; color:#5f5e5e;text-align:center">
    <span>今天是认识你的第</span>
    <span style="font-size:24px;color:rgb(221, 73, 73)"  >${day}</span>
    <span>天</span>
</div>
<div style="width:100%; margin: 0 auto;color:#5f5e5e;text-align:center">
    <span style="display:block;color:#676767;font-size:20px">${weather.notice!}</span>
    <span style="display:block;margin-top:15px;color:#676767;font-size:15px">今日天气</span>

    <div style="display: flex;margin-top:5px;height: 30px;line-height: 30px;justify-content: space-around;align-items: center;">
        <span style="width:15%; text-align:center;">${weather.city!}</span>
        <div style="width:35%; text-align:center;">
            <img style="height:26px;vertical-align:middle;"  src="${weather.weatherImg!}">
            <span style="display:inline-block">${weather.type!}</span>
        </div>
        <span style="width:25%; text-align:center;">${weather.temperature!}</span>

        <div style="width:25%; ">
        <#if weather.quality == "优">
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#008000; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        <#elseif weather.quality == "良">
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#ffff00; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        <#elseif weather.quality == "轻度污染">
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#ffa500; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        <#elseif weather.quality == "中度污染">
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#ff0000; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        <#elseif weather.quality == "重度污染">
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#800080; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        <#else>
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#a52a2a; border-radius:15px; text-align:center;">${weather.aqi!}${weather.quality!}</span>
        </#if>
        </div>
    </div>
</div>

<div style="text-align:center;margin:35px 0;">
    <span style="display:block;margin-top:55px;color:#676767;font-size:15px">ONE · 一个</span>
    <span style="display:block;margin-top:25px;font-size:22px; color:#9d9d9d; ">${.now?string["yyyy/MM/dd"]}</span>
    <img src="${one.img}" style="width:100%;margin-top:10px;">
    <#assign nums=one.contents/>
    <#list nums as num>
        <div style="margin:10px auto;width:85%;color:#5f5e5e;" >${num}</div>
    </#list>

</div>

</body>

</html>