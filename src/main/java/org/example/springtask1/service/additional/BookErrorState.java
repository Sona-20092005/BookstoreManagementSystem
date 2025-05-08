package org.example.springtask1.service.additional;

public enum BookErrorState {
    NO_OR_INCORRECT_ISBN ("The ISBN is missing or not of correct format"),
    NO_TITLE ("The title is missing"),
    NO_BOOKID ("The bookId is missing"),
    NO_OR_INCORRECT_AUTHORS ("The authors list is missing or not of correct format"),
    DUPLICATE_ISBN ("A book with the same ISBN is already in the database"),
    BOOK_UPDATED ("Book updated");

    private final String message;

    BookErrorState(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
