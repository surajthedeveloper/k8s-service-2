package com.demo.kubernetes.k8sservice2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomConfig {
    private Config service1;
    private Config service2;
}
