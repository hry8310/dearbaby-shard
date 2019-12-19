jar_file="./commons-cli-1.4.jar:./commons-io-2.5.jar:./fastjson-1.2.58.jar:./mysql-connector-java-5.1.36.jar:./slf4j-api-1.7.7.jar:./commons-dbcp-1.4.jar:./commons-pool-1.5.4.jar:./log4j-1.2.17.jar:./shard-view-0.0.1-SNAPSHOT.jar:./slf4j-log4j12-1.7.7.jar"

java -cp ${jar_file} dearbaby.hz.shard.view.Starter -c ./shard_view.properties

