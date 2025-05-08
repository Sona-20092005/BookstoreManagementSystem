package org.example.springtask1.service.additional;

public enum CoverErrorState {
    INVALID_URL ("The url is invalid"),
    IO_ERROR ("Unable to process"),
    DOWNLOAD_ERROR ("Unable to download"),
    MAKE_THUMBNAIL_ERROR ("Unable to make a thumbnail image");

    private final String message;

    CoverErrorState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
