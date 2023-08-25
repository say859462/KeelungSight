package com.example.demo.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SightService {
    private final SightRepository repository;

    @Autowired
    public  SightService(SightRepository repository){
        this.repository = repository;
    }
    //新增某地區所有景點資訊到資料庫
    public void addSightInfo(ArrayList<Sight> sightInfo,String zone){


        for(int i=0 ; i < sightInfo.size();i++){
            try{
                if(repository.findBySightName(sightInfo.get(i).getSightName()) == null)//判斷資料庫中此資料是否重複
                    repository.save(sightInfo.get(i));
            }catch(Exception err){
                System.err.println(sightInfo.get(i).getSightName()+"新增至資料庫時發生錯誤");
            }
        }

        System.out.println("已新增"+zone+"所有景點");

    }
    //查詢特定地區景點資訊
    public ArrayList<Sight> getSightInfo(String zone){
        if(!zone.endsWith("區")) zone+="區";
        return repository.findByZone(zone);
    }

}
