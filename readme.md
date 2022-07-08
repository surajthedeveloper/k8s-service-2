# Kubernetes Demo

## Steps to setup the project
- Add the plugin ```id 'com.palantir.docker' version '0.33.0'``` in build.gradle file
- Create a Docket file in build.gradle file level with following contents:
```aidl
FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Denv=dev","-cp","app:app/lib/*","com.demo.kubernetes.k8sservice2.K8sService2Application"]
```
- Add the task in build.gradle for building the docker image
```aidl
task unpack(type: Copy) {
    dependsOn bootJar
    from(zipTree(tasks.bootJar.outputs.files.singleFile))
    into("build/dependency")
}

docker {
    name "${project.name}:${project.version}"
    copySpec.from(tasks.unpack.outputs).into("dependency")
    buildArgs(['DEPENDENCY': "dependency"])
}
```
- Create docker image by running the following command ```gradle clean build docker```
- Create docker account in https://www.docker.com/
- login to docker in cli using following command ```docker login``` provide username and password
- ```docker tag k8s-service-2:0.0.1 <docker_account_name>/k8s-service-2:0.0.1```
- ```docker push <docker_account_name>/k8s-service-2:0.0.1``` to push the image into the docker repository

## Note:
- For enabling Liveness and Readiness probes add the following configuration in application.properties file
    - management.endpoint.health.probes.enabled=true

- 