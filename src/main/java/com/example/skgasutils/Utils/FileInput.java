package com.example.skgasutils.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class FileInput {

    /**
     * file input stream check
     * */
    public boolean checkInputStream(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();


    }


}
