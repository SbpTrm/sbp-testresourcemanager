package ru.sbp.trm.handlers;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import ru.sbp.trm.core.BotActions;

import java.util.HashMap;
import java.util.Map;

@Service
public class CallbackHandlersFactory {
    public static final String CALLBACK_DATA_SPLITTER = "|";
    private static final Map<String, Class<? extends CallbackHandler>> CALLBACK_HANDLERS_CLASSES = new HashMap<>();

    static {
        CALLBACK_HANDLERS_CLASSES.put(BotActions.SHOW_MAIN_MENU.toString(), MainMenuHandler.class);
    }

    @SneakyThrows
    public CallbackHandler getCallbackHandler(String callbackData) {
        String actionLabel = callbackData.split(CALLBACK_DATA_SPLITTER)[0];
        Class<? extends CallbackHandler> aClass = CALLBACK_HANDLERS_CLASSES.get(actionLabel);
        return aClass.getConstructor().newInstance();
    }
}
