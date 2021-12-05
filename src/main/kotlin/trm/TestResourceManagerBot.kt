package trm

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import trm.config.TestResourceManagerConfigurationProperties
import trm.core.*
import trm.core.BotActions.*

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
        if (update.hasCallbackQuery()) {
            val callback: Callback =
                OBJECT_MAPPER.readValue(update.callbackQuery.data, object : TypeReference<Callback>() {})
            return when (callback.action) {
                (SHOW_MY) -> update.createMyResourcesMessage()
                (SHOW_FREE) -> update.createFreeResourcesMessage()
                (ORDER) -> update.orderResource(callback.resId!!)
                (DISMISS) -> update.dismissResource(callback.resId!!)
                else -> update.createMainMenuMessage()
            }
        }
        return update.createMainMenuMessage()
    }
}