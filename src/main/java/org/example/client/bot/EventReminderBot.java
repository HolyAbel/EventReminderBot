package org.example.client.bot;

import org.example.client.Client;
import org.example.server.abstraction.service.EventService;
import org.example.server.abstraction.service.UserService;
import org.example.server.abstraction.service_interfaces.EventServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class EventReminderBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(EventReminderBot.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @Autowired
    private Client client;

    private static final String START = "/start";
    private static final String HELP = "/help";
    private static final String ADDEVENT = "/add_event";
    private static final String DELETEEVENT = "/delete_event";
    private static final String GETNEXTEVENT = "/next_event";

    private static final String COMMANDS = """
                Command list:
                /start - start
                /help - get help
                /next_event - get next event
            """;

    public EventReminderBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        var message = update.getMessage().getText().split(" ");
        var chatId = update.getMessage().getChatId();
        switch (message[0]) {
            case START -> {
                String userName = update.getMessage().getChat().getUserName();
                startCommand(chatId, userName);
            }
            case HELP -> helpCommand(chatId);
            case ADDEVENT -> addEvent(chatId, message[1], message[2], message[3], message[4]);
            case DELETEEVENT -> deleteEvent(chatId, message[1]);
            case GETNEXTEVENT -> getNextEvent(chatId);
        }
    }

    @Override
    public String getBotUsername() {
        return "event_reminder_pb_bot";
    }

    private void startCommand(Long chatId, String name) {
        try {
            userService.getByName(name);
        } catch (Exception e) {
            LOG.error("Error of getting user name;", e);
            sendMessage(chatId, "Can't connect to the server.");
        }
        var text = """
                Welcome to Event Reminder Bot, %s.
                """;
        var formattedText = String.format(text, name);
        sendMessage(chatId, formattedText);
    }

    private void helpCommand(Long chatId) {
        var text = """
                %s
                """;
        var formattedText = String.format(text, COMMANDS);
        sendMessage(chatId, formattedText);
    }

    public void addEvent(Long chatId, String summary, String datetime, String duration, String type) {
//        String input =
//        eventService.addEvent(new EventServiceInterface.AddEventDto((long)2, summary, (Instant)datetime, (long)duration, (int)type));
//        sendMessage(chatId, "Event add success.");
    }

    public void deleteEvent(Long chatId, String id) {

    }

    public void getNextEvent(Long chatId) {
        Mono<EventServiceInterface.EventDto> eventMono = eventService.getNext();
        var msg = Objects.equals(eventMono.block(), null) ? "Cannot find next event" : "Next event:\n" +
                "Summary: " + eventMono.block().summary() + "\n" +
                "Date and time: " + eventMono.block().datetime() + "\n" +
                "Duration: " + eventMono.block().duration() + " seconds";
        sendMessage(chatId, msg);
    }

    private void sendMessage(Long chatId, String text) {
        var chatIdStr = String.valueOf(chatId);
        var sendMessage = new SendMessage(chatIdStr, text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("Sending message error", e);
        }
    }
}
