package com.example.demo.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;

public class KeelungSightsCrawler {

    private final Document pageData;//主頁面資料
    private final String[] Zone = {"七堵區", "中山區", "中正區", "仁愛區", "安樂區", "信義區", "暖暖區"};

    public Map<String, ArrayList<Sight>> Sights = new HashMap<>();//景點資料

    //Constructor
    public KeelungSightsCrawler() throws IOException {
        pageData = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
        //抓取基隆所有地區的景點並存在map中 以地區名稱為key 該地區所有景點為value
        for (int i = 0; i < Zone.length; i++) {
            Sights.put(Zone[i], FetchItems(Zone[i]));
        }

    }

    //回傳特定地區的所有景點資料
    public ArrayList<Sight> getItems(String zone) throws IOException {
        if (!zone.endsWith("區")) zone += "區";

        return Sights.get(zone);

    }

    //抓取某地區的所有景點
    public ArrayList<Sight> FetchItems(String zone) throws IOException {

        Element query = pageData.select("#content #map_u .box_ss").first();//contain the information of sight of all zone
        String target = null;//Url that contain the information of sights of specific zone

        //抓取指定區域連結
        for (Element data : query.children()) {
            if (data.text().contains(zone)) {
                target = "https://www.travelking.com.tw" + data.select("a").attr("href");
            }
        }


        Document infoLink = connectToUrl(target);

        ArrayList<Sight> result = new ArrayList<Sight>();

        //抓區該地區所有景點資料並存在arrayList
        for (Element data : infoLink.select("#guide-point .box ul")) {
            for (Element sightInfo : data.select("li")) {
                result.add(fetchOneSightInfo("https://www.travelking.com.tw" + sightInfo.select("a").attr("href"), zone));
            }

        }

        return result;
    }

    //連結到某url並回傳該網頁資料
    private Document connectToUrl(String url) {
        while (true) {
            try {
                Document link = Jsoup.connect(url).timeout(5000).get();//Get html data of the page
                return link;
            } catch (Exception err) {
                System.err.println("與 (" + url + ") 連結過程發生錯誤或超時.......正在重新嘗試連結");
            }
        }
    }

    //抓取某景點資料
    private Sight fetchOneSightInfo(String url, String zone) {
        Document page = connectToUrl(url);//連結至某景點的url
        String sightName = page.select("#point_area h1").first().children().text();
        String category = page.select("#point_area cite .point_type span strong").first().text();
        String photoUrl = "";
        try {
            photoUrl = page.select("#point_area #galleria .g_main div ").first().select(".gpic img").first().attr("data-src");
        } catch (Exception err) {
            System.out.println(zone + sightName + "圖片抓取失敗或不存在，已使用空字串代替");
            photoUrl = "";
        }
        String description = page.select("#point_area .text").first().text();
        String address = page.select("#point_data .address p a span").text();
        return new Sight(sightName, zone, category, photoUrl, address, description);
    }
}
