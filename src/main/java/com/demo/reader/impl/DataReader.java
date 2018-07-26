package com.demo.reader.impl;

import com.demo.model.Team;
import com.demo.reader.IDataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

public class DataReader implements IDataReader {

    private final static Logger LOGGER = Logger.getLogger(DataReader.class.getName());

    // TODO: Create param to send the name of the file
    @Override
    public StringBuilder readTeamFromFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader().getResource("input.txt").toURI());
            StringBuilder data = new StringBuilder();
            Stream<String> lines = Files.lines(path);
            lines.forEach(line -> data.append(line).append("\n"));
            lines.close();

            return data;
        } catch (URISyntaxException e) {
            LOGGER.log(Level.SEVERE, "Error: URI to resource is incorrect");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading data from file");
        }

        return null;
    }

    /**
     * Transform an InputStream to String
     * @param inputStream InputStream to transform
     * @return String with the transformation of the InputStream
     * @throws IOException
     */
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}
