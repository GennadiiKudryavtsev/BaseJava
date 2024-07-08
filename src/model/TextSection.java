package model;

import java.util.Objects;

public class TextSection extends Section {
    private String textInfo;

    public TextSection(String textInfo) {
        this.textInfo = textInfo;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textInfo) {
        this.textInfo = textInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(textInfo, that.textInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textInfo);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "textInfo='" + textInfo + '\'' +
                '}';
    }
}
