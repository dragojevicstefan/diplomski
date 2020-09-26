package com.example.scooterRent.dto;

import java.util.List;

public class AddPicDTO {
    private String file;
    private List<String> fileSource;

    public AddPicDTO() {
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<String> getFileSource() {
        return fileSource;
    }

    public void setFileSource(List<String> fileSource) {
        this.fileSource = fileSource;
    }
}
