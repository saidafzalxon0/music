# JAR faylni o'zgartiring (Proyekt nomi va versiyasiga mos keladi)
ARG JAR_FILE=target/music.jar

# Java va Maven o'gohlantiruvchilari orqali o'zgartirishni boshlang
FROM openjdk:17-jdk-alpine
VOLUME /tmp

# Maven proyektni "target" papkasiga nusxalang
COPY target/music-0.0.1-SNAPSHOT.jar app.jar

# Uchish portini ma'lum qiling (Spring Boot proyektida ko'rsatilgan port)
EXPOSE 8080

# Java jar faylini boshlatish
ENTRYPOINT ["java","-jar","/app.jar"]
