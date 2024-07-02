package model;

public enum SectionsContact {
    PHONE("Тел.: "),
    MOBILE_PHONE("Мобильный тел.: "),
    HOME_PHONE("Домашний тел.: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    LINKEDIN("Профиль Linkedin"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");


    private String contactType;

    SectionsContact(String contactType) {
        this.contactType = contactType;
    }

    public String getContactType() {
        return contactType;
    }
}
