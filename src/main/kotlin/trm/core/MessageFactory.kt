package trm.core

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Update

fun Update.userId() = this.callbackQuery.from.id.toString()
fun Update.chatId() = this.message.chat.id.toString()

fun Update.createMyResourcesMessage(): BotApiMethod<*> = SendMessage.builder()
    .chatId(chatId())
    .text(BotActions.SHOW_MY.messageText)
    .replyMarkup(
        getResourceMenu(
            ResourceRepository.getReservedResources(userId()),
            BotActions.DISMISS
        )
    )
    .build()

fun Update.createFreeResourcesMessage(): BotApiMethod<*> = SendMessage.builder()
    .chatId(chatId())
    .text(BotActions.SHOW_FREE.messageText)
    .replyMarkup(
        getResourceMenu(
            ResourceRepository.getFreeResources(),
            BotActions.ORDER
        )
    )
    .build()

fun Update.createMainMenuMessage(): BotApiMethod<*> = SendMessage.builder()
    .chatId(chatId())
    .text(BotActions.SHOW_MAIN_MENU.messageText)
    .replyMarkup(getMainMenu())
    .build()

fun Update.orderResource(callbackQuery: CallbackQuery): BotApiMethod<*> {
    ResourceRepository.orderResource(userId(), callbackQuery.resourceName())
    return createMyResourcesMessage()
}

fun Update.dismissResource(callbackQuery: CallbackQuery): BotApiMethod<*> {
    ResourceRepository.dismissResource(userId(), callbackQuery.resourceName())
    return createMyResourcesMessage()
}

fun CallbackQuery.resourceName(): String {
    return this.data
}