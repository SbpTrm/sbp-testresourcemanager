package ru.sbp.trm;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sbp.trm.config.TestResourceManagerConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

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

        log.info("Incoming update: {}",update);
        if (!update.hasCallbackQuery()) {
            return sendInlineKeyBoardMessage(update.getMessage().getChatId(), "С чего начнем?", getMainMenu());
        }
        return null;
    }

    private InlineKeyboardMarkup getMainMenu() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton showFree = InlineKeyboardButton.builder()
                .text(BotActions.SHOW_FREE.action)
                .callbackData(BotActions.SHOW_FREE.toString()).build();

        InlineKeyboardButton showMy = InlineKeyboardButton.builder()
                .text(BotActions.SHOW_MY.action)
                .callbackData(BotActions.SHOW_MY.toString()).build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(showFree);
        keyboardButtonsRow.add(showMy);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;

    }

    public BotApiMethod<?> sendInlineKeyBoardMessage(long chatId, String messageText, InlineKeyboardMarkup inlineKeyboardMarkup) {
        return SendMessage.builder().chatId(String.valueOf(chatId)).text(messageText).replyMarkup(inlineKeyboardMarkup).build();
    }

    @Override
    public String getBotPath() {
        return testResourceManagerConfigurationProperties.getPath();
    }

    enum BotActions {
        SHOW_FREE("Свободные стенды"),
        SHOW_MY("Мои стенды"),
        ORDER("Занять стенд"),
        DISMISS("Освободить стенд");
        private String action;

        BotActions(String action) {
            this.action = action;
        }

    }
}