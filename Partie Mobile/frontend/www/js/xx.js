function start()
{
                cordova.plugins.notification.local.schedule({
                id         : 1,
                title      : 'Il existe un problème dans un examen',
                text       : 'Consulter la liste des alertes',
                sound       : "file://sound/beep.wav",
                every      : 'minute',
                autoClear  : false,
                at         : new Date(new Date().getTime() + 10*1000)
                });
}
function stop()
{
    cordova.plugins.notification.local.cancel(1,
    function() {
    alert('ok, ' + 1 + ' canceled');
    }
    );
}

function STOP2()
{
    alert("Hello");
    start();
    var x = sessionStorage.getItem("VU");
    if(x=='1'){
        stop();
        alert("Stoped");
        }
}