var playSound = function () {

    var borswer = window.navigator.userAgent.toLowerCase();
    if ( borswer.indexOf( "ie" ) >= 0 )
    {
        //IE内核浏览器
        var embed = document.embedPlay;
        //浏览器不支持 audion，则使用 embed 播放
        embed.volume = 100;
        embed.play();
        sleep(3000);
    } else
    {
        var audio = document.getElementById("audioPlay");
        //浏览器支持 audio
        audio.play();
        sleep(3000);
    }
};