package com.example.hikingplanner.service;

import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlannedHikeService {

    private final HikeTemplatesRepository hikeTemplatesRepository;

    public PlannedHikeService(HikeTemplatesRepository hikeTemplatesRepository) {
        this.hikeTemplatesRepository = hikeTemplatesRepository;
    }

    public List<HikeTemplates> getAllHikes(){
       return hikeTemplatesRepository.findAll();
    }
/*
    public List<HikeTemplates> getTrailByName(String name){
     return hikeTemplatesRepository.

    }*/

  /*  public HikeTemplates planHike(Hike hike){
        return
    }*/
    /*
    kasutaja valib raja - get kaardilt koht/nupp JA/VÕI sisestab otsingusse VÕI dropdown menüü?
    Igal juhul vist GET päring, sõltub lihtsalt mille järgi?
    see annab koha nime jms - siis mingi nupp et planeeri matk? POST mis lisaks siis selle hikes asja selle key järgi ja kõik muud väljad ka


    kaasa?

     */
}
