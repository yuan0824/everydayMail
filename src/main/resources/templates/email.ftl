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
            <img style="height:26px;vertical-align:middle;"  src="${weather.weatherimg!}">
            <span style="display:inline-block">${weather.type!}</span>
        </div>
        <span style="width:25%; text-align:center;">${weather.temperature!}</span>

        <div style="width:25%; ">
        <#if weather.air <= 50>
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#8fc31f; border-radius:15px; text-align:center;">${weather.air!}${weather.air_level!}</span>
        <#elseif (weather.air > 50 && weather.air <= 100)>
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#d7af0e; border-radius:15px; text-align:center;">${weather.air!}${weather.air_level!}</span>
        <#elseif (weather.air > 100 && weather.air <= 300)>
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#f39800; border-radius:15px; text-align:center;">${weather.air!}${weather.air_level!}</span>
        <#else>
            <span style="display:inline-block;padding:0 8px;line-height:25px;color:#e2361a; border-radius:15px; text-align:center;">${weather.air!}${weather.air_level!}</span>
        </#if>
        </div>
    </div>
</div>

<div style="text-align:center;margin:35px 0;">
    <span style="display:block;margin-top:55px;color:#676767;font-size:15px">ONE · 一个</span>
    <span style="display:block;margin-top:25px;font-size:22px; color:#9d9d9d; ">${.now?string["yyyy/MM/dd"]}</span>
    <img src="${one.img}" style="width:100%;margin-top:10px;">
    <div style="margin:10px auto;width:85%;color:#5f5e5e;" >${one.context}</div>
</div>


</body>

</html>