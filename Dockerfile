FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/apigateway-service-1.0.jar ApiGatewayService.jar
ENTRYPOINT ["java","-jar","ApiGatewayService.jar"]

# docker image 생성
# docker build -t hwk0173/apigateway-service:1.0 .

# docker 컨테이너 생성 및 실행
# docker run -d -p 8000:8000 --network ecommerce-network \
# -e "spring.cloud.config.uri=http://config-service:8888" \
# -e "spring.rabbitmq.host=rabbitmq" \
# -e "eureka.client.serviceUrl.defaultZone=http://discovery-service:8761/eureka/" \
# --name apigateway-service \
# hwk0173/apigateway-service:1.0
