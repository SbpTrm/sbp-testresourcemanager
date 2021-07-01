package ru.sbp.trm;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sbp.trm.config.TestResourceManagerConfigurationProperties;

@Slf4j
public class TestResourceManagerBot extends TelegramWebhookBot {

    private final TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties;

    public TestResourceManagerBot(TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties) {
        this.testResourceManagerConfigurationProperties = testResourceManagerConfigurationProperties;
    }

    @Override
    public String getBotUsername() {
        return testResourceManagerConfigurationProperties.getUsername();
    }

    @Override
    public String getBotToken() {
        return testResourceManagerConfigurationProperties.getToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

        if (update.getMessage() != null && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            log.info("Got update chat_id:" + chat_id + " message: " + update.getMessage());

            return new SendMessage(String.valueOf(chat_id), "Hi " + update.getMessage().getText());
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return testResourceManagerConfigurationProperties.getPath();
    }
}