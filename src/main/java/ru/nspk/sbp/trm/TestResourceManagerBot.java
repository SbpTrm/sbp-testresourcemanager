package ru.nspk.sbp.trm;

import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nspk.sbp.trm.config.TestResourceManagerConfigurationProperties;

public class TestResourceManagerBot extends TelegramWebhookBot {

    private final TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties;

    public TestResourceManagerBot(TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties) {
        this.testResourceManagerConfigurationProperties = testResourceManagerConfigurationProperties;
    }

    @Override
    public String getBotUsername() {
        return testResourceManagerConfigurationProperties.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return testResourceManagerConfigurationProperties.getBotToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return testResourceManagerConfigurationProperties.getBotPath();
    }
}