package org.example.springtask1.service.additional;

public class CoverError {
    Long id;
    String title;
    CoverErrorState coverErrorState;

    public CoverError(Long id, String title, CoverErrorState coverErrorState) {
        this.id = id;
        this.title = title;
        this.coverErrorState = coverErrorState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CoverErrorState getCoverErrorState() {
        return coverErrorState;
    }

    public void setCoverErrorState(CoverErrorState coverErrorState) {
        this.coverErrorState = coverErrorState;
    }

    @Override
    public String toString() {
        return "CoverError{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coverErrorState=" + coverErrorState +
                '}';
    }
}
