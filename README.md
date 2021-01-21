Demo spring boot based on linkedin learning course from Frank Moley:
https://www.linkedin.com/learning/instructors/frank-p-moley-iii?u=42751868

###Rabbit MQ docker image
## installation
pull
```
docker pull rabbitmq:3.8.9-management

```
start
```
docker run --rm -d --name rabbit-docker -p 5671:5671 -p 5672:5672 -p 15672:15672 rabbitmq:3.8.9-management
```
stop
```
docker stop rabbit-docker
```
## access
Login with guest/guest at http://localhost:15672