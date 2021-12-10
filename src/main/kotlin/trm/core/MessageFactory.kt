package trm.core

import org.telegram.telegrambots.meta.api.objects.Update

fun Update.userId() = this.callbackQuery.from.id.toString()
fun Update.chatId(): String {
    return if (this.callbackQuery != null) {
        this.callbackQuery.message.chat.id.toString()
    } else {
        this.message.chat.id.toString()
    }
}

