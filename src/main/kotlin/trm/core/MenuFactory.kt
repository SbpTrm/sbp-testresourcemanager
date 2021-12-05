package trm.core

import com.fasterxml.jackson.databind.ObjectMapper
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import trm.core.BotActions.*

private val OBJECT_MAPPER = ObjectMapper()

class Callback(val action: BotActions, val resId: String? = null)

fun createKeyboard(): InlineKeyboardMarkup {
    val inlineKeyboardMarkup = InlineKeyboardMarkup()
    inlineKeyboardMarkup.keyboard = mutableListOf<List<InlineKeyboardButton>>()
    return inlineKeyboardMarkup
}

fun getResourceMenu(resources: List<ResourceData>, callback: BotActions): InlineKeyboardMarkup {
    val keyboard = createKeyboard()
    for (resource in resources) {
        keyboard.addButton(callback.messageText + " " + resource.name, callback.name)
    }
    keyboard.addMainMenuButton()
    return keyboard
}

fun getMainMenu(): InlineKeyboardMarkup {
    val keyboard = createKeyboard()
    keyboard.addButton("Мои стенды", OBJECT_MAPPER.writeValueAsString(Callback(SHOW_MY)))
    keyboard.addButton("Свободные стенды", OBJECT_MAPPER.writeValueAsString(Callback(SHOW_FREE)))
    return keyboard
}

fun InlineKeyboardMarkup.addMainMenuButton() {
    this.keyboard.add(
        mutableListOf<InlineKeyboardButton>(
            InlineKeyboardButton.builder()
                .text("Главное меню")
                .callbackData(OBJECT_MAPPER.writeValueAsString(Callback(SHOW_MAIN_MENU)))
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


