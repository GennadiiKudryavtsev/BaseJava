package model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {

    public List<String> strings = new ArrayList<>();

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }
}
