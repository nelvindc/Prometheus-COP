## Introduction

This is a sample REST api on which you can try using Prometheus (Hardcoded) and Micrometer as discussed on the brownbag session

## Setup if you want to run the code

1) Eclipse or STS or other IDE for java
2) Springboot plugin if you're not using STS
3) Maven 3+
4) Java 8, haven't tested yet on java 6 or 7
5) Postman or SOAPUI or browser for http request

## Operations on the todo rest-api

localhost:8080

[GET] localhost:8080/v1/samplerest/todo/ping

Welcome!

[GET] localhost:8080/v1/samplerest/todo/list

[
    {
        "id": 1,
        "description": "sample task"
    }
]

[POST] localhost:8080/v1/samplerest/todo/

* if using postman use body and raw for the payload (task to add)
* then should the response like this if you successfully added a task

{
    "id": 3,
    "description": "test add"
}

[DELETE] localhost:8080/v1/samplerest/todo/{id}

* v1/samplerest/todo/2 will delete task 2


[GET} localhost:8080/v1/prometheus

* should display the metrics provided by micrometer

## for Prometheus server

1) go to https://prometheus.io/download/
2) if you're on Windows, download and extract prometheus-2.21.0-rc.0.windows-amd64.tar.gz
3) update prometheus.yml with these scrape configs if you want to pull the metrics from todo app

    metrics_path: '/v1/prometheus'
    static_configs:
    - targets: ['localhost:8080']

4) click prometheus.exe to start the Prometheus server
5) open localhost:9090/graph from the browser
6) if you call any operation on todo app, you should be able to see results executing query
http_server_requests_seconds_bucket

## Activity

1) Try the hardcoded version of creating the metrics
- comment out the dependency for micrometer/prometheus
- activate the dependency on pom.xml for  Prometheus (metrics) stuff

2) Try to add Gauge and Summary metrics on com.ti.cop.opensource.model.Metrics
- you can use this link for reference: https://github.com/prometheus/client_java

3) populate the created metrics on com.ti.cop.opensource.controller.TodoController in any operation







