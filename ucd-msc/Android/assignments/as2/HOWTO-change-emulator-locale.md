## Change it to English

    adb shell '
    setprop persist.sys.language en;
    setprop persist.sys.country IE;
    stop;
    sleep 5;
    start'

## Change it to Irish

    adb shell '
    setprop persist.sys.language ga;
    setprop persist.sys.country IE;
    stop;
    sleep 5;
    start'