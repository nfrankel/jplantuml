package ch.frankel.jplantuml.batch.model;

public class NamedBytes {

    private final String name;
    private final byte[] bytes;

    public NamedBytes(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public String name() {
        return name;
    }

    public byte[] bytes() {
        return bytes;
    }
}
