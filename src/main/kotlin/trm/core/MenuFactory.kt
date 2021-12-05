package trm.core

import com.google.gson.GsonBuilder
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import trm.core.BotActions.*

fun Any.toJson(): String = when (this) {
    is String -> this
    else -> {
        val builder = GsonBuilder()
        builder.serializeNulls()
        builder.create().toJson(this)
    }
}

fun <T : Any?> String.fromJson(type: Class<T>): T = GsonBuilder().serializeNulls().create().fromJson(this, type)

class Callback(val action: BotActions, val resId: String? = null)

fun createKeyboard(): InlineKeyboardMarkup {
    val inlineKeyboardMarkup = InlineKeyboardMarkup()
    inlineKeyboardMarkup.keyboard = mutableListOf<List<InlineKeyboardButton>>()
    return inlineKeyboardMarkup
}

fun getResourceMenu(resources: List<ResourceData>, callback: BotActions): InlineKeyboardMarkup {
    val keyboard = createKeyboard()
    for (resource in resources) {
        keyboard.addButton(callback.messageText + " " + resource.name, Callback(ORDER, resource.name).toJson())
    }
    keyboard.addMainMenuButton()
    return keyboard
}

fun getMainMenu(): InlineKeyboardMarkup {
    val keyboard = createKeyboard()
    keyboard.addButton("Мои стенды", Callback(SHOW_MY).toJson())
    keyboard.addButton("Свободные стенды", Callback(SHOW_FREE).toJson())
    return keyboard
}

fun InlineKeyboardMarkup.addMainMenuButton() {
    this.keyboard.add(
        mutableListOf<InlineKeyboardButton>(
            InlineKeyboardButton.builder()
                .text("Главное меню")
                .callbackData(Callback(SHOW_MAIN_MENU).toJson())
                .build()
        )
    )
}

fun InlineKeyboardMarkup.addButton(text: String, callback: String?) {
    println("add button with text='$text' and callback='$callback'")
    this.keyboard.add(
        mutableListOf<InlineKeyboardButton>(
            InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callback.let { callback })
                .build()
        )
    )
}


