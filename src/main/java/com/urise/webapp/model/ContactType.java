package com.urise.webapp.model;

public enum ContactType {
    PHONE("Тел.: "),
    MOBILE_PHONE("Мобильный тел.: "),
    HOME_PHONE("Домашний тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");


    private final String contactType;

    ContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactType() {
        return contactType;
    }
}
