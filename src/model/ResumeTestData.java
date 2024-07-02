package model;

import java.time.LocalDate;
import java.util.Map;

public class ResumeTestData {
    static Resume resume;

    public static void main(String[] args) {
        resume = new Resume("Борисов Алексей Борисович");
        System.out.println();
        addFullName(resume);

        System.out.println();
        addContact("89098888888", "skype-borisov", "borisov@yandex.ru",
                "https://www.linkedin.com/in/borisov", "https://github.com/borisov",
                "https://stackoverflow.com/users/borisov\n");

        addTitleSection(SectionType.OBJECTIVE.getTitle());
        addPosition("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        addTitleSection(SectionType.PERSONAL.getTitle());
        addPersonal("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.\n");

        addTitleSection(SectionType.ACHIEVEMENT.getTitle());
        addAchievement("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, " +
                "Spring-MVC, \nGWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов \n" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии \nчерез систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы \nпо JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, \nChronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        addTitleSection(SectionType.QUALIFICATIONS.getTitle());
        addQualification("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,\n" +
                " OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, \nUML, функционального программирования\n" +
                "Родной русский, английский \"upper intermediate\"\n");

        addTitleSection(SectionType.EXPERIENCE.getTitle());
        addOrganisation(2013, 10, 1, 2024, 7, 2,
                "Java Online Projects", "https://javaops.ru/",
                "Автор проекта.\nСоздание, организация и проведение Java онлайн проектов и стажировок.\n");

        addTitleSection(SectionType.EXPERIENCE.getTitle());
        addOrganisation(2013, 10, 1, 2024, 7, 2,
                "Wrike", "https://www.wrike.com/vao/",
                "Старший разработчик (backend)\n" +
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, " +
                        "Maven, Spring, \nMyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, " +
                        "авторизация по OAuth1, OAuth2, JWT SSO.\n");

        addTitleSection(SectionType.EDUCATION.getTitle());
        addOrganisation(2013, 03, 01, 2013, 05, 01,
                "Coursera", "https://www.coursera.org/learn/scala-functional-programming",
                        "'Functional Programming Principles in Scala' by Martin Odersky\n");

        addOrganisation(2011, 03, 01, 2013, 04, 01,
                "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                        "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
    }
    public static void addFullName(Resume r) {
        Resume resume1 = r;
        System.out.println(resume1.getFullName());
    }
    public static void addContact(String phoneNum, String skype, String email, String linkedin,
                                  String github, String stackoverflow) {
        resume.getMapContact().put(SectionsContact.PHONE, phoneNum);
        resume.getMapContact().put(SectionsContact.SKYPE, skype);
        resume.getMapContact().put(SectionsContact.EMAIL, email);
        resume.getMapContact().put(SectionsContact.LINKEDIN, linkedin);
        resume.getMapContact().put(SectionsContact.GITHUB, github);
        resume.getMapContact().put(SectionsContact.STACKOVERFLOW, stackoverflow);
        for (Map.Entry<SectionsContact, String> entry : resume.getMapContact().entrySet()) {
            System.out.println(entry.getKey().getContactType() + entry.getValue());
        }
    }

    public static void addPosition(String info) {
        TextSection position = new TextSection(info);
        System.out.println(position.getTextInfo());
    }

    public static void addPersonal(String info) {
        TextSection position = new TextSection(info);
        System.out.println(position.getTextInfo());
    }

    public static void addAchievement(String info) {
        TextSection position = new TextSection(info);
        resume.getListInfo().add(position);
        if (resume.getListInfo().contains(position)) {
            System.out.println(position.getTextInfo());
        }
    }

    public static void addQualification(String info) {
        TextSection qualification = new TextSection(info);
        resume.getListInfo().add(qualification);
            if (resume.getListInfo().contains(qualification)) {
                System.out.println(qualification.getTextInfo());
            }
    }
    public static void addOrganisation(int startYear, int startMonth, int startDayOfMonth, int lastYear,
                                     int lastMonth, int lastDayOfMonth, String nameCompany,
                                     String url, String info) {

        LocalDate dateBefore = LocalDate.of(startYear, startMonth, startDayOfMonth);
        LocalDate lastDateOf = LocalDate.of(lastYear, lastMonth, lastDayOfMonth);

        int beforeYear = dateBefore.getYear();
        int beforeMonth = dateBefore.getMonthValue();
        int afterYear = lastDateOf.getYear();
        int afterMonth = lastDateOf.getMonthValue();

        CompanySection companySection = new CompanySection(nameCompany, url, dateBefore, lastDateOf, info);

        resume.getLsitCompany().add(companySection);
        if (resume.getLsitCompany().contains(companySection)) {
            System.out.println(beforeMonth + "/" + beforeYear + " - " +
                    ((!lastDateOf.equals(LocalDate.now())) ? (afterMonth + "/" + afterYear) : "Сейчас") +  "\n"
                    + companySection.getName() + "\n" + companySection.getUrl() + "\n"
                    + companySection.getDescription());
        }
    }

    public static void addTitleSection(String type) {
        System.out.println(type);
    }
}
