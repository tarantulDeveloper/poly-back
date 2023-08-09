mvn clean package -DskipTests

echo 'Copy files...'

scp -i ~/.ssh/google-frankfurt \
    ./target/*.jar \
    beksdeveloper@34.65.179.105:~/lovz-server/server.jar

echo 'Restart the server...'

ssh -i  ~/.ssh/google-frankfurt beksdeveloper@34.65.179.105 << EOF
pgrep java | xargs kill -9
nohup java -jar lovz-server/server.jar &
EOF

echo 'Bye!'