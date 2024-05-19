@echo off

if not defined JAVA_HOME (
    echo "Missing JAVA_HOME environment variable, use set"
    exit /b 1
)

set root=%~dp0
set root=%root:~0,-1%

set native=%root%\lib\native
set jar=%root%\inzh-llm-http-service-0.2.jar
set PATH=%native%;%PATH%

"%JAVA_HOME%\bin\java.exe" -Djava.library.path="%native%" -jar "%jar%" --home "%root%" %*

pause