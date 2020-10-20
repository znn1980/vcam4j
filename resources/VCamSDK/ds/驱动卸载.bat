@echo off
cls
color 0A
echo 驱动卸载中...
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
cd /d "%~dp0"
regsvr32 /s /u VCamSource.ax
regsvr32 /s /u VCamCOM.dll
echo 驱动卸载完成！
pause