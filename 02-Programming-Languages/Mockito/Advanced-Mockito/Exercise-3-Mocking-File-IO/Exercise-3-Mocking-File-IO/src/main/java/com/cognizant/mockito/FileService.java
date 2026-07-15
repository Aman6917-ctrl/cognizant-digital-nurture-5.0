package com.cognizant.mockito;

public class FileService {

    private FileReader fileReader;
    private FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String processedContent = "Processed " + fileReader.read();
        fileWriter.write(processedContent);
        return processedContent;
    }
}
