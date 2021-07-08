package ru.sbp.trm.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sbp.trm.core.ResourceData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.sbp.trm.handlers.CallbackHandlersFactory.CALLBACK_DATA_SPLITTER;

public interface CallbackHandler {
    default ResourceData getResourceData(String callbackData){
        List<String> dataLIst = Arrays.stream(callbackData.split(CALLBACK_DATA_SPLITTER)).collect(Collectors.toList());
        if (dataLIst.size()<2) return null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(dataLIst.get(1),ResourceData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    SendMessage getResponse(Update update);
}
