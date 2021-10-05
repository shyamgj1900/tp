@echo off
setlocal enableextensions
pushd %~dp0

cd ..
call gradlew clean shadowJar

cd build\libs
for /f "tokens=*" %%a in (
    'dir /b *.jar'
) do (
    set jarloc=%%a
)

move %jarloc% ../..
cd ../..

java -jar %jarloc% < text-ui-test\input.txt > text-ui-test\ACTUAL.TXT

del %jarloc%

cd text-ui-test

FC ACTUAL.TXT EXPECTED.TXT >NUL && ECHO Test passed! || Echo Test failed!