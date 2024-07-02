package model;

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
}
