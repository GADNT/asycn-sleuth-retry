title GAD
set COMMON_JVM_ARGS=-Dserver.port=8030 -Dspring.profiles.active=sandbox
set APP_ARGS=-Xms256m -Xmx256m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5030

call gradle clean build %COMMON_JVM_ARGS%

FOR /F "tokens=* USEBACKQ" %%F IN (`dir /b build\libs\*.jar`) DO (
  SET JAR_FILE=%%F
)

ECHO %JAR_FILE%

call java %APP_ARGS% %COMMON_JVM_ARGS% -jar build\libs\%JAR_FILE%

pause