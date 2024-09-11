package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {

    private static final long serialVersionUID = 1L;
    private List<String> strings = new ArrayList<>();

    public ListSection(String... items) {
        this.strings = Arrays.asList(items);
    }

    public ListSection() {
    }

    public List<String> getStrings() {
         return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "strings=" + strings +
                '}';
    }
}
