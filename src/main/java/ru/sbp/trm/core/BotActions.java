package ru.sbp.trm.core;

import lombok.Getter;

@Getter
public enum BotActions {
    SHOW_MAIN_MENU("Чего изволите?","Основное меню"),
    SHOW_FREE("Какой стенд хотите забронировать?", "Свободные стенды"),
    SHOW_MY("Какой стенд желаете освободить?", "Мои стенды"),
    ORDER("Резервирование стенда", ""),
    DISMISS("Освободить стенд", "");
    private final String messageText;
    private final String buttonText;

    BotActions(String messageText, String buttonText) {
        this.messageText = messageText;
        this.buttonText = buttonText;
    }

}
