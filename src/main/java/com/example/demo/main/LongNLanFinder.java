package com.example.demo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LongNLanFinder {
    private double latitude;
    private double longitude;

    public LongNLanFinder(String placeName) {

        // 使用 ProcessBuilder 運行 Python 程式碼
        String pythonScript = "import geocoder\n" +
                "def find_location(place_name):\n" +
                "    g = geocoder.arcgis(place_name, country='Taiwan')\n" +
                "    if g.ok:\n" +
                "        latitude, longitude = g.latlng\n" +
                "        print(latitude)\n" +
                "        print(longitude)\n" +
                "    else:\n" +
                "        print('找不到此地點的經緯度資訊')\n" +
                "\n" +
                "find_location('" + placeName + "')";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "-c", pythonScript);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 捕獲 Python 輸出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> outputLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                outputLines.add(line);
            }
            reader.close();

            // 解析 Python 輸出並提取經緯度資訊
            if (outputLines.size() >= 2) {
                latitude = Double.parseDouble(outputLines.get(0));
                longitude = Double.parseDouble(outputLines.get(1));
                System.out.println("景點：" + placeName + " 的經緯度為：(" + latitude + ", " + longitude + ")");
            } else {
                System.out.println("找不到此地點的經緯度資訊:" + placeName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}