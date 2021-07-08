package ru.sbp.trm.handlers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.sbp.trm.core.BotActions;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CallbackHandlersFactory {
    public static final String CALLBACK_DATA_SPLITTER = "\\|";
    private static final Map<String, Class<? extends CallbackHandler>> CALLBACK_HANDLERS_CLASSES = new HashMap<>();

    static {
        CALLBACK_HANDLERS_CLASSES.put(BotActions.SHOW_MAIN_MENU.toString(), MainMenuHandler.class);
        CALLBACK_HANDLERS_CLASSES.put(BotActions.SHOW_FREE.toString(), ShowFreeResourcesHandler.class);
    }

    @SneakyThrows
    public CallbackHandler getCallbackHandler(String callbackData) {
        log.info("Getting handler for callbackData {}", callbackData);
        String actionLabel = callbackData.split(CALLBACK_DATA_SPLITTER)[0];
        log.info("Getting handler for label {}", actionLabel);
        Class<? extends CallbackHandler> handlerClass = CALLBACK_HANDLERS_CLASSES.get(actionLabel);
        log.info("Using class {} for label {}", handlerClass, actionLabel);
        //todo переделать обработку исключения
        if (handlerClass == null) throw new RuntimeException("Не найден класс для обработки " + actionLabel);
        return handlerClass.getConstructor().newInstance();
    }
}
