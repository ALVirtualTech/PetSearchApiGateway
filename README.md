# Pet Search Service

веб-сервис для поиска потерявшихся домашних животных.

## API Gateway:

API Gateway являет точкой входа для запросов от веб-клиента и мобильных клиентов. 

API Gateway передает запросы на обработку специализированным микросервисам и сам также зарегистрирован в регистре сервисов.

В качестве регистра сервисов используется Consul. Перед запуском сервиса Api Gateway необходимо предварительно запустить Consul.

## REST API предоставляемое сервисом:

**Авторизация (доступ для всех):**

curl -i -X POST -d username=user -d password=password -c c:\curl\cookies.txt http://localhost:8080/login

**CRUD операции с объявлениями:**

Получение объявлений:

curl -i -X GET -d start=0 -d size=20 http://localhost:8080/api/adverts

Создание объявлений:

curl -i -X POST -H "Content-Type: application/json" \
    -d  {[data for adverts]} \
    http://localhost:8080/api/adverts
    
Получение объявлений заданного пользователя:

curl -i -X GET -d start=0 -d size=20 http://localhost:8080/api/adverts/{userId}

Получение объявления по его идентификатору:

curl -i -X GET -d id=1 http://localhost:8080/api/advert

Создание объявления:

curl -i -X POST -H "Content-Type: application/json" \
    -d  {data for advert} \
    http://localhost:8080/api/advert


Изменение объявления:

curl -i -X PUT -H "Content-Type: application/json" \
    -d  {data for advert} \
    http://localhost:8080/api/advert

Удаление объявления по его идентификатору:

curl -i -X DELETE -d id=1 http://localhost:8080/api/advert

**Поиск объявлений:**