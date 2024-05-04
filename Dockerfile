FROM openjdk:17-oracle

EXPOSE 8081

ADD target/graphql-0.0.1-SNAPSHOT.jar graphql.jar

ENTRYPOINT ["java","-jar","graphql.jar"]
