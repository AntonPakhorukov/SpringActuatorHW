# Фреймворк Spring (HomeWork)
## Семинар 9. Spring Cloud. Микросервисная архитектура.

### Задание: 
Реализовать микросервисную архитектуру в рамках предыдущей ДЗ.

### Структура Пользователя (User):
1. ID - автоинкрементное
2. Name - не может быть пустым
3. Age - может быть пустым
4. PhoneNumber - не может быть пустым

### Структура Задачи (Task):
1. ID - автоинкрементное
2. Name - не может быть пустым
3. Description - может быть пустым
4. Status - устанавливается одно из возможных занчений (ToDo, InProgress, Done)
5. Date - устанавливается автоматически

### Реализация:
EurekaServerApplication запускается на порте 8761<br>
UserServiceApplication запускается на порте 8082 под именем userservice<br>
TaskServiceApplication запускается на порте 8081 под именем taskservice<br>
GatewayserviceApplication запускается на порте 8080<br>

### Управление:
POST /users - зарегистрировать пользователя<br>
GET / users - посмотреть список пользователей<br>
GET /users/{id} - найти пользователя по ID<br>
PUT /users/{id} - внести изменения в карточке пользователя по ID<br>
DELETE /users/{id} - удалить пользователя по ID<br>

POST /tasks - зарегистрировать задачу<br>
GET / tasks - посмотреть список задач<br>
GET /tasks/{id} - найти задачу по ID<br>
PUT /tasks/{id} - внести изменения в карточке задачи по ID<br>
DELETE /tasks/{id} - удалить задачу по ID<br>
