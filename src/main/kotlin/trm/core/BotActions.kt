package trm.core

enum class BotActions(val messageText: String) {
    SHOW_MAIN_MENU("Чего изволите?"),
    SHOW_FREE("Какой стенд хотите забронировать?"),
    SHOW_MY("Какой стенд желаете освободить?"),
    ORDER("Резервирование стенда"),
    DISMISS("Освободить стенд");
}