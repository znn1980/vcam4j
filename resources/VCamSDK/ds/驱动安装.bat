@echo off
cls
color 0A
echo ������װ��...
%1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)&&exit
cd /d "%~dp0"
regsvr32 /s VCamSource.ax
regsvr32 /s VCamCOM.dll
echo ������װ��ɣ�
pause