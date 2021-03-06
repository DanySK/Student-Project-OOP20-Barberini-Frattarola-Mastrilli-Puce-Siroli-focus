package oop.focus.diary.model;

public interface Diary {
    /**
     * @return  the content of a diary
     */
    String getContent();
    /**
     * Sets the content of a diary's note.
     * @param content
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
