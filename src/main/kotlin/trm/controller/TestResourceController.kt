package trm.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import trm.TestResourceManagerBot

@RestController
open class TestResourceController(private val testResourceManagerBot: TestResourceManagerBot) {
    @PostMapping(path = ["/"])
    fun onUpdateReceived(@RequestBody update: Update): BotApiMethod<*> =
        testResourceManagerBot.onWebhookUpdateReceived(update)
}