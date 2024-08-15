package model;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.Map;

public class ResumeTestData {
    static Resume resume;
    static CompanySection educationCompany = new CompanySection();;
    static CompanySection experience = new CompanySection();;

    public static void main(String[] args) {
        resume = new Resume("uuid1", "Ivanov Ivan Ivanovich");
        addFullName(resume);

        addContact("89098888888", "skype-borisov", "borisov@yandex.ru",
                "https://www.linkedin.com/in/borisov", "https://github.com/borisov",
                "https://stackoverflow.com/users/borisov\n");

        addTitleSection(SectionType.OBJECTIVE);
        addTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        addTitleSection(SectionType.PERSONAL);
        addTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.\n");

        addTitleSection(SectionType.ACHIEVEMENT);
        addListSection("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
                "Spring, Spring-MVC, \nGWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов \n" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии \nчерез систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы \nпо JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, \nChronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        addTitleSection(SectionType.QUALIFICATIONS);
        addListSection("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,\n" +
                "OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, \nUML, функционального программирования\n" +
                "Родной русский, английский \"upper intermediate\"\n");

        addTitleSection(SectionType.EXPERIENCE);
        addOrganisation(SectionType.EXPERIENCE,"2020-10-01", "2022-01-01",
                "Java Online Projects", "https://javaops.ru/",
                "Автор проекта.", "Создание, организация и проведение " +
                        "Java онлайн проектов и стажировок.\n");

        addOrganisation(SectionType.EXPERIENCE,"2000-05-07", "2016-02-01",
                "Java Online Projects", "https://www.wrike.com/vao/",
                "Стажер",
                        "Развитие проекта и поддержка проекта.\n");

        addOrganisation(SectionType.EXPERIENCE,"2015-01-02", "2016-02-01",
                "Wrike", "https://www.wrike.com/vao/",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, \nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n");

        addTitleSection(SectionType.EDUCATION);
        addOrganisation(SectionType.EDUCATION,"2013-05-06", "2013-10-01",
                "Coursera", "https://www.coursera.org/learn/scala-functional-programming", "",
                        "'Functional Programming Principles in Scala' by Martin Odersky\n");

        addOrganisation(SectionType.EDUCATION,"2011-03-01", "2013-04-01",
                "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                "","Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'\n");

        addOrganisation(SectionType.EDUCATION,"1993-09-01", "1996-07-01",
                "Санкт-Петербургский национальный исследовательский университет " +
                        "информационных технологий, механики и оптики", "https://itmo.ru/",
                "", "Аспирантура (программист С, С++)\n");

        addOrganisation(SectionType.EDUCATION,"2000-09-01", "2001-07-01",
                "Санкт-Петербургский национальный исследовательский университет " +
                        "информационных технологий, механики и оптики", "https://itmo.ru/",
                "", "Инженер (программист Fortran, C)");

        resume.getMapSectionType().put(SectionType.EDUCATION, educationCompany);
        resume.getMapSectionType().put(SectionType.EXPERIENCE, experience);

    }

    public static void addFullName(Resume r) {
        System.out.println(r.getFullName());
    }
    public static void addContact(String phoneNum, String skype, String email, String linkedin,
                                  String github, String stackoverflow) {
        resume.getMapContact().put(ContactType.PHONE, phoneNum);
        resume.getMapContact().put(ContactType.SKYPE, skype);
        resume.getMapContact().put(ContactType.EMAIL, email);
        resume.getMapContact().put(ContactType.LINKEDIN, linkedin);
        resume.getMapContact().put(ContactType.GITHUB, github);
        resume.getMapContact().put(ContactType.STACKOVERFLOW, stackoverflow);
        for (Map.Entry<ContactType, String> entry : resume.getMapContact().entrySet()) {
            System.out.println(entry.getKey().getContactType() + entry.getValue());
        }
    }

    public static void addTextSection(String info) {

        TextSection position = new TextSection(info);
        System.out.println(position.getTextInfo());
    }

    public static void addListSection(String info) {

        ListSection list = new ListSection();
        list.getStrings().add(info);
        if (list.getStrings().contains(info)) {
            System.out.println(info);
        }
    }

    public static void addOrganisation(SectionType sectionType, String startDate, String lastDate, String nameCompany,
                                       String url, String title, String description) {

        LocalDate firstDate = LocalDate.parse(startDate);
        LocalDate endDate = LocalDate.parse(lastDate);
        Period period = new Period(firstDate, endDate, title, description);

        Company company = new Company(nameCompany, url);
        company.getPeriods().add(period);

        if (sectionType == SectionType.EDUCATION) {
            nameCompany = checkSection(educationCompany, nameCompany);
            }
        if (sectionType == SectionType.EXPERIENCE) {
            nameCompany = checkSection(experience, nameCompany);
            }

        addInList(sectionType, company);
        for (int i = 0; i < company.getPeriods().size(); i++) {
            System.out.println(company.getPeriods().get(i).getStartDate() + " - "
            + company.getPeriods().get(i).getEndDate() + "\n" + nameCompany + "\n"
            + company.getWebsite() + "\n" + company.getPeriods().get(i).getTitle() + "\n"
            + company.getPeriods().get(i).getDescription());
        }
    }

    public static String checkSection(CompanySection companySection, String checkName) {
            for (int j = 0; j < companySection.getCompanies().size(); j++) {
                if (companySection.getCompanies().get(j).getName().equals(checkName)) {
                    return "";
                }
            }
            return checkName;
    }

    private static void addTitleSection(SectionType type) {
        System.out.println(type.getTitle());
    }

    public static void addInList(SectionType sectionType, Company company) {
        if (sectionType == SectionType.EDUCATION) {
            educationCompany.getCompanies().add(company);
        }
        if (sectionType == SectionType.EXPERIENCE){
            experience.getCompanies().add(company);
        }
    }

    public Resume createResume(String uuid, String fullName) {
        resume = new Resume(uuid, fullName);
        addFullName(resume);

        addContact("89098888888", "skype-borisov", "borisov@yandex.ru",
                "https://www.linkedin.com/in/borisov", "https://github.com/borisov",
                "https://stackoverflow.com/users/borisov\n");

        System.out.println(SectionType.OBJECTIVE.getTitle());
        addTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям\n");

        System.out.println(SectionType.PERSONAL.getTitle());
        addTextSection("Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.\n");

        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        addListSection("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, " +
                "Spring, Spring-MVC, \nGWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов \n" +
                "(SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о " +
                "состоянии \nчерез систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы \nпо JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, " +
                "Eport, \nChronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.\n");

        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        addListSection("Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,\n" +
                "OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных " +
                "шаблонов, \nUML, функционального программирования\n" +
                "Родной русский, английский \"upper intermediate\"\n");

        System.out.println(SectionType.EXPERIENCE.getTitle());
        addOrganisation(SectionType.EXPERIENCE,"2020-10-01", "2022-01-01",
                "Java Online Projects", "https://javaops.ru/",
                "Автор проекта.", "Создание, организация и проведение " +
                        "Java онлайн проектов и стажировок.\n");

        addOrganisation(SectionType.EXPERIENCE,"2000-05-07", "2016-02-01",
                "Java Online Projects", "https://www.wrike.com/vao/",
                "Стажер",
                        "Развитие проекта и поддержка проекта.\n");

        addOrganisation(SectionType.EXPERIENCE,"2015-01-02", "2016-02-01",
                "Wrike", "https://www.wrike.com/vao/",
                "Старший разработчик (backend)",
                "Проектирование и разработка онлайн платформы управления проектами Wrike " +
                        "(Java 8 API, Maven, Spring, \nMyBatis, Guava, Vaadin, PostgreSQL, Redis). " +
                        "Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.\n");

        System.out.println(SectionType.EDUCATION.getTitle());
        addOrganisation(SectionType.EDUCATION,"2013-05-06", "2013-10-01",
                "Coursera", "https://www.coursera.org/learn/scala-functional-programming", "",
                        "'Functional Programming Principles in Scala' by Martin Odersky\n");

        addOrganisation(SectionType.EDUCATION,"2011-03-01", "2013-04-01",
                "Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
                "","Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'\n");

        addOrganisation(SectionType.EDUCATION,"1993-09-01", "1996-07-01",
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, " +
                        "механики и оптики", "https://itmo.ru/",
                "", "Аспирантура (программист С, С++)\n");

        addOrganisation(SectionType.EDUCATION,"2000-09-01", "2001-07-01",
                "Санкт-Петербургский национальный исследовательский университет информационных технологий, " +
                        "механики и оптики", "https://itmo.ru/",
                "", "Инженер (программист Fortran, C)");

        resume.getMapSectionType().put(SectionType.EDUCATION, educationCompany);
        resume.getMapSectionType().put(SectionType.EXPERIENCE, experience);

        return resume = new Resume(uuid, fullName);
    }
}