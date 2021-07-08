package ru.sbp.trm.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sbp.trm.core.BotActions;

import java.util.ArrayList;
import java.util.List;

public class MainMenuHandler implements CallbackHandler{
    private final BotActions action = BotActions.SHOW_MAIN_MENU;

    @Override
    public SendMessage getResponse(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton showFree = InlineKeyboardButton.builder()
                .text(BotActions.SHOW_FREE.getMessageText())
                .callbackData(BotActions.SHOW_FREE.toString()).build();

        InlineKeyboardButton showMy = InlineKeyboardButton.builder()
                .text(BotActions.SHOW_MY.getMessageText())
                .callbackData(BotActions.SHOW_MY.toString()).build();

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(showFree);
        keyboardButtonsRow.add(showMy);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId())).text(action.getMessageText()).replyMarkup(inlineKeyboardMarkup).build();
    }
}
