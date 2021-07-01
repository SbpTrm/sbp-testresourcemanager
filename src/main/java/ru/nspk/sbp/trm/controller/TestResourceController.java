package ru.nspk.sbp.trm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nspk.sbp.trm.TestResourceManagerBot;

public class TestResourceController {
    private final TestResourceManagerBot testResourceManagerBot;

    public TestResourceController(TestResourceManagerBot testResourceManagerBot) {
        this.testResourceManagerBot = testResourceManagerBot;
    }

    @PostMapping(path = "/")
    public BotApiMethod<?> onUpdateReceived(Update update){
        return testResourceManagerBot.onWebhookUpdateReceived(update);
    }
}
