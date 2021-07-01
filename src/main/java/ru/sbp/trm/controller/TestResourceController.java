package ru.sbp.trm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.sbp.trm.TestResourceManagerBot;

@RestController
@Slf4j
public class TestResourceController {
    private final TestResourceManagerBot testResourceManagerBot;

    public TestResourceController(TestResourceManagerBot testResourceManagerBot) {
        this.testResourceManagerBot = testResourceManagerBot;
    }

    @PostMapping(path = "/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        log.info("Got update: " + update.toString());
        return testResourceManagerBot.onWebhookUpdateReceived(update);
    }
}
