package trm

import org.springframework.beans.factory.annotation.Autowired
import org.telegram.telegrambots.bots.TelegramWebhookBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Update
import trm.config.TestResourceManagerConfigurationProperties
import trm.core.BotActions.*
import trm.core.Callback
import trm.core.UpdateHandler
import trm.core.fromJson
import trm.core.toJson

class TestResourceManagerBot(
    @Autowired val testResourceManagerConfigurationProperties: TestResourceManagerConfigurationProperties,
    @Autowired val updateHandler: UpdateHandler
) : TelegramWebhookBot() {

    override fun getBotUsername() = testResourceManagerConfigurationProperties.username

    override fun getBotPath() = testResourceManagerConfigurationProperties.path

    override fun getBotToken() = testResourceManagerConfigurationProperties.token

    override fun onWebhookUpdateReceived(update: Update): BotApiMethod<*> {
        //todo разобраться как нормально логировать
        println("Incoming update: ${update.toJson()}")
        if (update.hasCallbackQuery()) {
            val callback: Callback = update.callbackQuery.data.fromJson(Callback::class.java)
            return when (callback.action) {
                (SHOW_MY) -> updateHandler.createMyResourcesMessage(update)
                (SHOW_FREE) -> updateHandler.createFreeResourcesMessage(update)
                (ORDER) -> updateHandler.orderResource(callback.resId!!, update)
                (DISMISS) -> updateHandler.dismissResource(callback.resId!!, update)
                else -> updateHandler.createMainMenuMessage(update)
            }
        }
        return updateHandler.createMainMenuMessage(update)
    }
}