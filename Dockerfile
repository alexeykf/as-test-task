FROM gradle:7.0.0-jdk11 AS build
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN gradle clean build -x test

FROM adoptopenjdk/openjdk11:alpine-jre
RUN mkdir /usr/app
COPY --from=build /app/build/libs/app.jar /usr/app/app.jar
WORKDIR /usr/app
RUN addgroup --system juser && adduser -S -s /bin/false -G juser juser
RUN chown -R juser:juser /usr/app
USER juser
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
