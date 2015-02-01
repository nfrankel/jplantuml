package ch.frankel.jplantuml.batch.model;

public class NamedContent {

    private final String name;
    private final String content;

    public NamedContent(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String name() {
        return name;
    }

    public String content() {
        return content;
    }
}
