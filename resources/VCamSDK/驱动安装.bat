@echo off
cls
color 0A
echo 驱动安装中...
echo Run as administrator
cacls.exe "%SystemDrive%\System Volume Information" >nul 2>nul
if %errorlevel%==0 goto as_admin
if exist "%temp%\getadmin.vbs" del /f /q "%temp%\getadmin.vbs"
echo Set RequestUAC = CreateObject^("Shell.Application"^)>"%temp%\getadmin.vbs"
echo RequestUAC.ShellExecute "%~s0","","","runas",1 >>"%temp%\getadmin.vbs"
echo WScript.Quit >>"%temp%\getadmin.vbs"
"%temp%\getadmin.vbs" /f
if exist "%temp%\getadmin.vbs" del /f /q "%temp%\getadmin.vbs"
exit

:as_admin

cd /d %~dp0
set driver=driver
ver | find "10." && set driver=driver_win10
if /i "%PROCESSOR_IDENTIFIER:~0,3%" NEQ "X86" goto x64

devcon.exe install %driver%\VCamSDK.inf VCamSDKDevice
goto reg

:x64
echo Install 64bits driver on 64bits OS...
x64\devcon.exe install %driver%\VCamSDK.inf VCamSDKDevice
regsvr32 x64\VCamRenderer.ax /s

:reg
regsvr32 VCamRenderer.ax /s

echo 驱动安装完成！

pause
