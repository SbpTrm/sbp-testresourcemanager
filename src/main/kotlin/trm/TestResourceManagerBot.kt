package trm

import com.fasterxml.jackson.databind.ObjectMapper
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import trm.config.TestResourceManagerConfigurationProperties
import trm.core.*

class TestResourceManagerBot(
    private val testResourceManagerConfigurationProperties: TestResourceManagerConfigurationProperties,
) : TelegramWebhookBot() {

    private val OBJECT_MAPPER = ObjectMapper()

    override fun getBotUsername() = testResourceManagerConfigurationProperties.username

    override fun getBotPath() = testResourceManagerConfigurationProperties.path

    override fun getBotToken() = testResourceManagerConfigurationProperties.token

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        //todo разобраться как нормально логировать
        println("Incoming update: " + OBJECT_MAPPER.writeValueAsString(update))
        return update.getResponse()
    }

    private fun Update.getResponse(): BotApiMethod<*> {
        if (this.hasCallbackQuery()) {
            return when (this.callbackQuery.data) {
                ("SHOW_MY") -> this.createMyResourcesMessage()
                ("SHOW_FREE") -> this.createFreeResourcesMessage()
                ("ORDER") -> this.orderResource(this.callbackQuery)
                ("DISMISS") -> this.dismissResource(this.callbackQuery)
                else -> createMainMenuMessage()
            }
        }
        return createMainMenuMessage()
    }
}