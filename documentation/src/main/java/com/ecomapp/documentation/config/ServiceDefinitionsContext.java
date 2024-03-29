package com.ecomapp.documentation.config;

import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class ServiceDefinitionsContext {
    private final ConcurrentHashMap<String,String> serviceDescriptions;

    private ServiceDefinitionsContext(){
        serviceDescriptions = new ConcurrentHashMap<String, String>();
    }

    public void addServiceDefinition(String serviceName, String serviceDescription){
        serviceDescriptions.put(serviceName, serviceDescription);
    }

    public String getSwaggerDefinition(String serviceId){
        return this.serviceDescriptions.get(serviceId);
    }

    public List<SwaggerResource> getSwaggerDefinitions(){
        return  serviceDescriptions.keySet().stream().map(s -> {
            SwaggerResource resource = new SwaggerResource();
            resource.setLocation("/service/" + s);
            resource.setName(s);
            resource.setSwaggerVersion("2.0");
            return resource;
        }).collect(Collectors.toList());
    }
}
