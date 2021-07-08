package ru.sbp.trm;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sbp.trm.config.TestResourceManagerConfigurationProperties;
import ru.sbp.trm.core.BotActions;
import ru.sbp.trm.handlers.CallbackHandler;
import ru.sbp.trm.handlers.CallbackHandlersFactory;

@Slf4j
public class TestResourceManagerBot extends TelegramWebhookBot {

    private final TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties;
    private final CallbackHandlersFactory callbackHandlersFactory;

    public TestResourceManagerBot(TestResourceManagerConfigurationProperties testResourceManagerConfigurationProperties, CallbackHandlersFactory callbackHandlersFactory) {
        this.testResourceManagerConfigurationProperties = testResourceManagerConfigurationProperties;
        this.callbackHandlersFactory = callbackHandlersFactory;
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

        log.info("Incoming update: {}", update);
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            CallbackHandler callbackHandler = callbackHandlersFactory.getCallbackHandler(callbackData);
            return callbackHandler.getResponse(update);

        } else {
            CallbackHandler callbackHandler = callbackHandlersFactory.getCallbackHandler(BotActions.SHOW_MAIN_MENU.toString());
            return callbackHandler.getResponse(update);
        }
    }

    @Override
    public String getBotPath() {
        return testResourceManagerConfigurationProperties.getPath();
    }
}