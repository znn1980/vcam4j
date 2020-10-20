@echo off
cls
color 0A
echo 驱动卸载中...
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
if /i "%PROCESSOR_IDENTIFIER:~0,3%" NEQ "X86" goto x64

devcon.exe remove VCamSDKDevice
goto reg

:x64
x64\devcon.exe remove VCamSDKDevice
regsvr32 x64\VCamRenderer.ax /u /s

:reg
regsvr32 VCamRenderer.ax /u /s

echo 驱动卸载完成！

pause