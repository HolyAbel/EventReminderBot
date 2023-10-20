# Event Reminder

## Telegram bot
Ссылка на бота в Телеграм: t.me/event_reminder_pb_bot

Список комманд:

    /start
    
    /help - получить список комманд
    
    /add_event {summary} {date} {time} {duration} {type} - добавить событие (типы событий (type): 0 - не повторяющееся, 1 - ежечасное, 2 - ежедневное, 3 - еженедельное, 4 - ежемесячное)
    
    /update_event {id} {summary} {date} {time} {duration} {type} - изменить событие с соответствующим id
    
    /delete_event {id} - удалить событие с соответствующим id
    
    /next_event - получить следующее событие
    
    /day_event - получить события на следующие 24 часа
    
    /week_event - получить на следующие 168 часов
    
    /rec_events - получить все повторяющиеся события

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/help.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/start.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/add_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/update_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/delete_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/next_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/day_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/week_event.JPG)

## Реализованный функционал сервера
### User
1. /getUser/{id} - получение данных учетной записи пользователя, соответствующего id.
2. /signUp - создание новой учетной записи пользователя.
3. /signIn - вход в учетную запись пользователя.

### Event
1. /getEvent/{id} - получение события по id.
2. /getEvents/{datetime} -- получение события по datetime - дате и времени.
3. /getEvents/{type} - получение события по типу - type.
4. /getNext - получение следующего на очереди события.
5. /getDay - получение событий, которые произойдут в близжайшие 24 часа (1 день).
6. /getWeek - получение событий, которые произойдут в близжайшие 168 часов (7 дней).
7. /addEvent - создание нового события.
8. /deleteEvent/{id} - удаление события, соответствующего id.
9. /updateEvent/{id} - обновление события, соответствующего id.

## Особенности реализации
Были использованы такие технологии, как webflux и r2dbc. Используемая СУБД - MySQL.

Рекурентность события определяется значением type. Если type = 0, то событие нерекурентно. Если же type = 1, 2, 3 и 4, то событие ежечасно, ежедневно, еженедельно и ежемесечно соответственно.
