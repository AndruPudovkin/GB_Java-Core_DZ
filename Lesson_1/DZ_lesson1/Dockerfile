FROM openjdk:latest
WORKDIR /usr/src/app
COPY ./src/main/java .
RUN javac -sourcepath . -d out ./com/javaCore/andru/Main.java
WORKDIR /usr/src/app/out
CMD java -classpath . com.javaCore.andru.Main