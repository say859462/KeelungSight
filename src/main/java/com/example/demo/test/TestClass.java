package com.example.demo.test;

import com.example.demo.main.KeelungSightsCrawler;
import com.example.demo.main.Sight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestClass {
    private static KeelungSightsCrawler crawler;

    @BeforeAll
    public static void setUp() throws IOException {
        crawler = new KeelungSightsCrawler();

    }

    @DisplayName("測試爬蟲物件建立")
    @Test
    public void testCrawlerCreation() {
        assertNotNull(crawler);
    }

    @DisplayName("測試爬蟲物件所有地區資料抓取是否成功")
    @Test
    public void testCrawler() throws IOException {
        double time1 = System.currentTimeMillis();
        int total = 0;
        String[] zone = {"七堵區", "中山區", "中正區", "仁愛區", "安樂區", "信義區", "暖暖區"};
        //獲取每個地區的景點資料
        for (int i = 0; i < zone.length; i++) {

            ArrayList<Sight> data = crawler.getItems(zone[i]);
            System.out.println(zone[i] + "\n---------------------------------------------");
            for (int j = 0; j < data.size(); j++) {
                System.out.println((j + 1) + "." + data.get(j).getSightName());
            }
            System.out.println();
            total += data.size();
            //確認該地區資料存在
            assertNotNull(data);
        }
        double time2 = System.currentTimeMillis();
        //確認最終資料數量是否正確
        assertEquals(41, total);
        System.out.println("預期資料數量:41");
        System.out.println("實際資料數量:" + total);
        System.out.println(time2 - time1);
    }


}
