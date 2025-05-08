package org.example.springtask1.service.additional;

import java.util.ArrayList;
import java.util.List;

public class CoverDownloadResult {
    List<CoverError> coverErrors = new ArrayList<>();

    public List<CoverError> getCoverErrors() {
        return coverErrors;
    }

    public void setCoverErrors(List<CoverError> coverErrors) {
        this.coverErrors = coverErrors;
    }

    @Override
    public String toString() {
        return "CoverDownloadResult{" +
                "coverErrors=" + coverErrors +
                '}';
    }
}
