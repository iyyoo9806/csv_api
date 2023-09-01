package com.kopo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/csv")
public class ApiController {

    @PostMapping("/api")
    public ResponseEntity<List<Map<String, String>>> api(@RequestParam("file") MultipartFile file) {
        List<Map<String, String>> jsonData = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

            String line;
            String[] headers = br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                HashMap<String, String> data = new HashMap<String, String>();
                for (int i = 0; i < headers.length; i++) {
                    data.put(headers[i].replace("\uFEFF", ""), columns[i]);
                }
                jsonData.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<List<Map<String, String>>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Map<String, String>>>(jsonData, HttpStatus.OK);
    }
}
