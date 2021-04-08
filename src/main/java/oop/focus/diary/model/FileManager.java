package oop.focus.diary.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * The interface can be used to manage the opening/closing of buffers.
 * Every file has his own FileManager.
 */
public interface FileManager {
    /**
     * The method create a new buffered reader of the input's file.
     * @param file  the file of which is returned his buffered reader
     * @throws FileNotFoundException    if the file doesn't exist
     */
    void openBufferedReader(File file) throws FileNotFoundException;

    /**
     * Return the last buffered reader created.
     * @return  the last buffered reader created
     */
    BufferedReader getBufferedReader();

    /**
     * The method create a new buffered writer of the input's file.
     * @param file  the file of which is returned his buffered reader
     * @throws IOException  if the buffered can't be opened
     */
    void openBufferedWriter(File file) throws IOException;

    /**
     * Return the last buffered writer created.
     * @return  the last buffered writer created
     */
    BufferedWriter getBufferedWriter();

    /**
     * Return the path of the specific file of file manager.
     * @return  the path of the file specified in the constructor of the class
     */
    Path getFile();
}

