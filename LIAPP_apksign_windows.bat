@echo off
title LIAPP Signing with apksigner
SETLOCAL ENABLEDELAYEDEXPANSION
Color 3f

rem Check the input value, please enter the value according to your environment.
set "KeyStorePath=debug.keystore"
set "ALIAS_NAME=debugkey"
set "STORE_PASS=password"
set "KEY_PASS=password"
set "SDK_BUILD_PATH=D:\AndroidSDK\build-tools\35.0.0"
set "APKSIGNER_PATH=%SDK_BUILD_PATH%\lib\apksigner.jar"

rem File Check
set "CLASSPATH=%~1"
set "FILE_PATH=%~dp1%~n1"
set "FILE_NAME=%~nx1"
set "FILE_DIR=%~dp1"
set "ZIPALIGNED_PATH=%FILE_PATH%.zipaligned.apk"
set "SIGNED_FILE=%FILE_PATH%.signed.apk"

if "%~x1"==".apk" (
    echo Classpath: "%CLASSPATH%"
    echo File path: "%FILE_PATH%"
    echo File name: "%FILE_NAME%"
    echo File directory: "%FILE_DIR%"

    echo APK File Checked!
    echo - "%CLASSPATH%"
    echo Zip align Start!
    "%SDK_BUILD_PATH%\zipalign.exe" -f -v 4 "%CLASSPATH%" "%ZIPALIGNED_PATH%"
    echo.
    echo Zip Align Complete!
    echo - "%ZIPALIGNED_PATH%"
    echo.
    echo APK Signing Start!
    echo.

    java -jar "%APKSIGNER_PATH%" sign -v --out "%SIGNED_FILE%" --ks "%KeyStorePath%" --ks-pass pass:%STORE_PASS% --key-pass pass:%KEY_PASS% --ks-key-alias "%ALIAS_NAME%" "%ZIPALIGNED_PATH%" 
    Color 0C
    echo.
    echo Please Check Signing Message.
    echo If you see "error" or "Exception," close the window.
    echo If no problem, press any key to Verify Signing.
    pause >nul
    Color 3f
    echo.
    echo Verifying Signing...
    echo - "%SIGNED_FILE%"
    echo.
    java -jar "%APKSIGNER_PATH%" verify -v --print-certs "%SIGNED_FILE%"
    echo.
    echo Press any key to exit.
    pause >nul
    exit
)

echo File is not APK type.
echo Please check your file.
echo - "%CLASSPATH%"
echo.
echo Press any key to exit.
pause >nul
endlocal
exit
