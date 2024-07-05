package model;

import java.time.LocalDate;
import java.util.Map;

public class ResumeTestData {
    static Resume resume;

    public static void main(String[] args) {
        resume = new Resume("Борисов Алексей Борисович\n");
        addFullName(resume);

        addContact("89098888888", "skype-borisov", "borisov@yandex.ru",
                "https://www.linkedin.com/in/borisov", "https://github.com/borisov",
                "https://stackoverflow.com/users/borisov\n");

        addTitleSection(SectionType.OBJECTIVE.getTitle());
        addTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        addTitleSection(SectionType.PERSONAL.getTitle());
        addTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.\n");

        addTitleSection(SectionType.ACHIEVEMENT.getTitle());
        addListSection("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
                "Spring, Spring-MVC, \nGWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов \n" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии \nчерез систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы \nпо JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, \nChronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        addTitleSection(SectionType.QUALIFICATIONS.getTitle());
        addListSection("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,\n" +
                "OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, \nUML, функционального программирования\n" +
                "Родной русский, английский \"upper intermediate\"\n");

        addTitleSection(SectionType.EXPERIENCE.getTitle());
        addOrganisation("2020-10-01", "2022-01-01",
                "Java Online Projects", "https://javaops.ru/",
                "Автор проекта.", "Создание, организация и проведение " +
                        "Java онлайн проектов и стажировок.\n");

        addOrganisation("2015-01-02", "2016-02-01",
                "Wrike", "https://www.wrike.com/vao/",
                "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                                "(Java 8 API, Maven, Spring, \nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                                "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n");

        addTitleSection(SectionType.EDUCATION.getTitle());
        addOrganisation("2013-05-06", "2013-10-01",
                "Coursera", "https://www.coursera.org/learn/scala-functional-programming", "",
                        "'Functional Programming Principles in Scala' by Martin Odersky\n");

        addOrganisation("2011-03-01", "2013-04-01",
                "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                "","Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
    }
    public static void addFullName(Resume r) {
        System.out.println(r.getFullName());
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

    public static void addTextSection(String info) {
        TextSection position = new TextSection(info);
        System.out.println(position.getTextInfo());
    }

    public static void addListSection(String info) {
        ListSection list = new ListSection();
        list.strings.add(info);
        if (list.strings.contains(info)) {
            System.out.println(info);
        }
    }

    public static void addOrganisation(String startDate, String lastDate, String nameCompany,
                                       String url, String title, String description) {

        LocalDate firstDate = LocalDate.parse(startDate);
        LocalDate endDate = LocalDate.parse(lastDate);

        Period period = new Period(firstDate, endDate, title, description);

        Company company = new Company(nameCompany, url);
        company.getListPeriod().add(period);

        CompanySection companySection = new CompanySection();
        companySection.getOrganization().add(company);

        resume.getMapSectionType().put(SectionType.EXPERIENCE, companySection);

        if (resume.getMapSectionType().containsValue(companySection)) {
            for (int i = 0; i < companySection.getOrganization().size(); i++) {
                if (companySection.getOrganization().get(i).equals(company)) {
                    System.out.println(company.getListPeriod().get(i).getStartDay() + " - " +
                            company.getListPeriod().get(i).getEndDay() + "\n" +
                            company.getName() + "\n" + company.getWebsite() + "\n" +
                            company.getListPeriod().get(i).getTitle() + "\n" +
                            company.getListPeriod().get(i).getDescription());
                }
            }
        }
    }

    public static void addTitleSection(String type) {
        System.out.println(type);
    }
}