# Event Reminder

## Telegram bot
Ссылка на бота в Телеграм: [Event Reminder Bot](t.me/event_reminder_pb_bot)

Command list:

    /start
    
    /help - get help
    
    /add_event {summary} {date} {time} {duration} {type} - add event;
    
    types: 0 - non recurring, 1 - hourly, 2 - daily, 3 - weekly, 4 - monthly
    
    /update_event {id} {summary} {date} {time} {duration} {type} - update event by id
    
    /delete_event {id} - delete event by id
    
    /next_event - get next event
    
    /day_event - get events for a day
    
    /week_event - get events for a week
    
    /rec_events - get all recurring tasks

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/help.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/start.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/add_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/update_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/delete_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/next_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/day_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/week_event.JPG)

![image](https://github.com/HolyAbel/EventReminderBot/blob/master/rec_events.JPG)

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
5. /getDay - получение событий, которые произойдут в блихжайшие 24 часа (1 день).
6. /getWeek - получение событий, которые произойдут в блихжайшие 168 часов (7 дней).
7. /addEvent - создание нового события.
8. /deleteEvent/{id} - удаление события, соответствующего id.
9. /updateEvent/{id} - обновление события, соответствующего id.

## Особенности реализации
Были использованы такие технологии, как webflux и r2dbc. Используемая СУБД - MySQL.

Рекурентность события определяется значением type. Если type = 0, то событие нерекурентно. Если же type = 1, 2, 3 и 4, то событие ежечасно, ежедневно, еженедельно и ежемесечно соответственно.
