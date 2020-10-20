@echo off
cls
color 0A
echo 驱动安装中...
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
cd /d "%~dp0"
regsvr32 /s VCamSource.ax
regsvr32 /s VCamCOM.dll
echo 驱动安装完成！
pause