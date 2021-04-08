package oop.focus.diary.model;
/**
 * The interface Diary models a diary's page in witch is possible to set title and content of the note.
 */
public interface Diary {
    /**
     * @return  the content of a diary
     */
    String getContent();
    /**
     * Sets the content of a diary's note.
     * @param content   the content of the diary's note
     */
    void setContent(String content);
    /**
     * @return the title of diary's note
     */
    String getName();
    /**
     * @param name the title of diary's note
     */
    void setName(String name);

}
