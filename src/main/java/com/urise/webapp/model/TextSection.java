package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section {
    private static final long serialVersionUID = 1L;
    private String textInfo;

    public TextSection(String textInfo) {
        this.textInfo = textInfo;
    }

    public TextSection() {
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
