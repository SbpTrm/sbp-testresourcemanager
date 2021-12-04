package trm.core

enum class BotActions(val messageText: String) {
    SHOW_MAIN_MENU("Чего изволите?"),
    SHOW_FREE("Свободные стенды"),
    SHOW_MY("Мои стенды"),
    ORDER("Забрать"),
    DISMISS("Освободить");
}