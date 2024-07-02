import model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;
    private static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }
    public TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance);
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }
    public enum Singleton {
        INSTANCE
    }
}
