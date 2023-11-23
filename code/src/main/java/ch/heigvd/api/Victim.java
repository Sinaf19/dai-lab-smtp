package ch.heigvd.api;

import java.io.File;
import java.nio.charset.Charset;

public class Victim {
    private final String email;

    public Victim(String email) {
        this.email = email;
    }

    public String getMail() {
        return email;
    }
}
