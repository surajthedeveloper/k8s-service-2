package com.demo.kubernetes.k8sservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class K8sService2Application {
    @Autowired
    private K8SService1API service1;

    @Value("${APPLICATION_NAME:}")
    private String applicationName;

    public static void main(String[] args) {
        SpringApplication.run(K8sService2Application.class, args);
    }

    @GetMapping("/hello-world-from-service-1")
    public String getHelloWorldFromService1() {
        return applicationName + " ::: " + service1.helloWorld();
    }

}
