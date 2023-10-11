# Event Reminder

## Реализованный функционал:
### User:
1. /getUser/{id} -- получение данных учетной записи пользователя, соответствующего id.
2. /signUp -- создание новой учетной записи пользователя.
3. /signIn -- вход в учетную запись пользователя.

### Event:
1. /getEvent/{id} -- получение события по id.
2. /getEvents/{datetime} -- получение события по datetime -- дате и времени.
3. /getEvents/{type} -- получение события по типу -- type.
4. /getNext -- получение следующего на очереди события.
5. /getDay -- получение событий, которые произойдут в блихжайшие 24 часа (1 день).
6. /getWeek -- получение событий, которые произойдут в блихжайшие 168 часов (7 дней).
7. /addEvent -- создание нового события.
8. /deleteEvent/{id} -- удаление события, соответствующего id.
9. /updateEvent/{id} -- обновление события, соответствующего id.

## Особенности реализации:
Были использованы такие технологии, как webflux и r2dbc. Используемая СУБД -- MySQL.

Рекурентность события определяется значением type. Если type = 0, то событие не рекурентно. Если же type = 1, 2, 3 и 4, то событие ежечасно, ежедневно, еженедельно и ежемесечно соответственно.
