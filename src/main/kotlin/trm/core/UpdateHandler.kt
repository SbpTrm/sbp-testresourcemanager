package trm.core

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import trm.repository.ResourceRepository

@Service
class UpdateHandler(@Autowired val repository: ResourceRepository) {

    init {
        println("repository.getFreeResources=" + repository.getFreeResources())
    }

    fun createMyResourcesMessage(update: Update): BotApiMethod<*> = SendMessage.builder()
        .chatId(update.chatId())
        .text(BotActions.SHOW_MY.messageText)
        .replyMarkup(
            getResourceMenu(
                repository.getReservedResourcesByUserId(update.userId()),
                BotActions.DISMISS
            )
        )
        .build()

    fun createFreeResourcesMessage(update: Update): BotApiMethod<*> = SendMessage.builder()
        .chatId(update.chatId())
        .text(BotActions.SHOW_FREE.messageText)
        .replyMarkup(
            getResourceMenu(
                repository.getFreeResources(),
                BotActions.ORDER
            )
        )
        .build()

    fun createMainMenuMessage(update: Update): BotApiMethod<*> = SendMessage.builder()
        .chatId(update.chatId())
        .text(BotActions.SHOW_MAIN_MENU.messageText)
        .replyMarkup(getMainMenu())
        .build()

    fun orderResource(resourceName: String, update: Update): BotApiMethod<*> {
        repository.orderResource(update.userId(), resourceName)
        return createMyResourcesMessage(update)
    }

    fun dismissResource(resourceName: String, update: Update): BotApiMethod<*> {
        repository.dismissResource(update.userId(), resourceName)
        return createMyResourcesMessage(update)
    }

}