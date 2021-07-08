package ru.sbp.trm.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.sbp.trm.core.BotActions;
import ru.sbp.trm.core.ResourceData;
import ru.sbp.trm.core.ResourceRepository;
import ru.sbp.trm.core.ResourceRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ShowMyResourcesHandler implements CallbackHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final BotActions ACTION = BotActions.SHOW_FREE;

    //Todo сделать корректно
    ResourceRepository resourceRepository = new ResourceRepositoryImpl() {
    };
    @SneakyThrows
    @Override
    public SendMessage getResponse(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<ResourceData> myResources = resourceRepository.getMyResources();
        if (myResources.isEmpty()) {
            InlineKeyboardButton showMainMenu = InlineKeyboardButton.builder()
                    .text(BotActions.SHOW_MAIN_MENU.getButtonText()).build();

            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(showMainMenu);

            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            rowList.add(keyboardButtonsRow);
            inlineKeyboardMarkup.setKeyboard(rowList);
            return SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId()))
                    .text("Сорри Бро, сейчас всё занято.").replyMarkup(inlineKeyboardMarkup).build();
        } else {
            List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
            for (ResourceData freeResource : myResources) {
                InlineKeyboardButton showMainMenu = InlineKeyboardButton.builder()
                        .text(freeResource.getName())
                        .callbackData(OBJECT_MAPPER.writeValueAsString(ResourceData.builder().build()))
                        .build();

                List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
                keyboardButtonsRow.add(showMainMenu);


                rowList.add(keyboardButtonsRow);
                //TODO add main menu button
            }
            inlineKeyboardMarkup.setKeyboard(rowList);
            return SendMessage.builder().chatId(String.valueOf(update.getMessage().getChatId())).
                    text(ACTION.getMessageText()).replyMarkup(inlineKeyboardMarkup).build();
        }
    }
}
