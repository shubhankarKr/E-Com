# FROM openjdk:17-jdk
# EXPOSE 8080
# VOLUME /tmp
# ARG DEPENDENCY=target/dependency
# COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY ${DEPENDENCY}/META-INF /app/META-INF
# COPY ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["java","-cp","app:app/lib/*","eCom.backEnd.BackEndApplication"]


##### Stage 1 - Lets build the "deployable package"

# FROM maven:3.9-eclipse-temurin-17 as backend-build
# WORKDIR /fullstack/backend

# ### Step 1 - Copy pom.xml and download project dependencies

# # Dividing copy into two steps to ensure that we download dependencies 
# # only when pom.xml changes
# COPY pom.xml .
# # dependency:go-offline - Goal that resolves all project dependencies, 
# # including plugins and reports and their dependencies. -B -> Batch mode
# RUN mvn dependency:go-offline -B

# ### Step 2 - Copy source and build "deployable package"
# COPY src ./src
# RUN mvn package -DskipTests

# # Unzip
# RUN mkdir -p target/dependency && (cd target; jar -xf ../*.war)

# ##### Stage 2 - Let's build a minimal image with the "deployable package"
# FROM openjdk:17-jdk
# # VOLUME /tmp
# ARG DEPENDENCY=/fullstack/backend/target/dependency
# COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=backend-build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=backend-build ${DEPENDENCY}/BOOT-INF/classes /app
# ENTRYPOINT ["java","-cp","app:app/lib/*","eCom.backEnd.BackEndApplication"]

FROM maven:3.9-eclipse-temurin-17 as backend-build
WORKDIR /fullstack/backend

### Step 1 - Copy pom.xml and download project dependencies

# Dividing copy into two steps to ensure that we download dependencies 
# only when pom.xml changes
COPY pom.xml .
# dependency:go-offline - Goal that resolves all project dependencies, 
# including plugins and reports and their dependencies. -B -> Batch mode
RUN mvn dependency:go-offline -B

### Step 2 - Copy source and build "deployable package"
COPY src ./src
RUN mvn package -DskipTests

# Unzip the jar file
# RUN mkdir -p target/dependency && (cd target; jar -xf ./*.war)
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.war ; ls -R ./)

##### Stage 2 - Let's build a minimal image with the "deployable package"
FROM openjdk:17-jdk
ARG DEPENDENCY=/fullstack/backend/target/dependency
VOLUME /tmp
ADD /target/*.war app.war
ENTRYPOINT ["java","-jar","app.war"]
