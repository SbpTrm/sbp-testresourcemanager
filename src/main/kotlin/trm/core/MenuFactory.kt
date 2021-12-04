package trm.core

import com.fasterxml.jackson.databind.ObjectMapper
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import trm.core.BotActions.SHOW_MAIN_MENU

private val OBJECT_MAPPER = ObjectMapper()

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
    keyboard.addButton("Мои стенды", BotActions.SHOW_MY.name)
    keyboard.addButton("Свободные стенды", BotActions.SHOW_FREE.name)
    return keyboard
}

fun InlineKeyboardMarkup.addMainMenuButton() {
    this.keyboard.add(
        mutableListOf<InlineKeyboardButton>(
            InlineKeyboardButton.builder()
                .text("Главное меню")
                .callbackData(OBJECT_MAPPER.writeValueAsString(SHOW_MAIN_MENU.name))
                .build()
        )
    )
}

fun InlineKeyboardMarkup.addButton(text: String, callback: String?) {
    this.keyboard.add(
        mutableListOf<InlineKeyboardButton>(
            InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callback.let { callback })
                .build()
        )
    )
}


