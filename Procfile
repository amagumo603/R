web: java $JAVA_OPTS -jar target/bot2-0.0.1-SNAPSHOT.jar --server.port=$PORT
web: java $JAVA_OPTS -jar lib/gdata-client-1.0.jar --port $PORT target/*.war
web: java $JAVA_OPTS -jar lib/gdata-client-meta-1.0.jar --port $PORT target/*.war
web: java $JAVA_OPTS -jar lib/gdata-core-1.0.jar --port $PORT target/*.war
web: java $JAVA_OPTS -jar lib/gdata-spreadsheet-3.0.jar --port $PORT target/*.war
web: java $JAVA_OPTS -jar lib/gdata-spreadsheet-meta-3.0.jar --port $PORT target/*.war
web: java $JAVA_OPTS -jar lib/jsr305.jar --port $PORT target/*.war