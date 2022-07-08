package com.demo.kubernetes.k8sservice2;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "k8s-service-1", url = "${K8S_SERVICE_1_URL:http://localhost:8080}")
public interface K8SService1API {
    @GetMapping("/hostname")
    public String hostname();

}
