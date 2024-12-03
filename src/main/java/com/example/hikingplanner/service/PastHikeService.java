package com.example.hikingplanner.service;

import com.example.hikingplanner.model.Hike;
import com.example.hikingplanner.model.HikeTemplates;
import com.example.hikingplanner.repository.HikeRepository;
import com.example.hikingplanner.repository.HikeTemplatesRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional

public class PastHikeService {

    private final HikeTemplatesRepository pastHikeRepository;
    private final HikeRepository hikeRepository;

    public PastHikeService(HikeTemplatesRepository pastHikeRepository, HikeRepository hikeRepository) {
        this.pastHikeRepository = pastHikeRepository;
        this.hikeRepository = hikeRepository;
    }

    public List<HikeTemplates> getAllHikes() {
        return pastHikeRepository.findAll();
    }




//    // meetod, et kasutaja saaks valida matka algus- ja lõppkuupäeva
//    public List<String> hikeDates(LocalDate startDate, LocalDate endDate) {
//        if (endDate.isBefore(startDate)) {
//            throw new IllegalArgumentException("Lõppkuupäev ei saa olla enne alguskuupäeva.");
//        }
//
//        hikeRepository.save();
//        // Convert LocalDate to String using the default toString() method
//        return Arrays.asList(startDate.toString(), endDate.toString());
//    }
//
//    // meetod, mille eesmärk on võtta vastu tunnid ja minutid (kumbki tüübiga double) ning arvutada välja matka kestvus formaadis tund:minut (nt 2:30).
//    public String addHikeDuration(double hours, double minutes) {
//        int intHours = (int) hours; // teisendame hours väärtuse täisarvuks
//        int intMinutes = (int) (minutes % 60); // minutes % 60 arvutab, kui palju on jääke minutites pärast täistunde. (int) muudab tulemuse täisarvuks. Kui on rohkem kui 60 minutit, siis jääb alles vaid ülejäänud minutite osa (nt 90 minutist jääb alles 30 minutit).
//
//        String duration = String.format("%d:%02d", intHours, intMinutes);
//
//
//        hikeRepository.save();
//        // Vormindame vastuse, et kuvada tunnid ja minutid formaadis hours:minutes
//        return duration;
//    }
//
//
//    public String addNotes (String text) {
//
//        return text;
//    }

}
