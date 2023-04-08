package com.example.interview.controller;

import com.example.interview.entity.Scan;
import com.example.interview.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/scan") // This means URL's start with /demo (after Application path)
public class ScanController {

    @Autowired private ScanRepository scanRepository;

    @GetMapping
    public ResponseEntity<List<Scan>> getAllScans() {
        List<Scan> scans = (List<Scan>) scanRepository.findAll();
        return new ResponseEntity<>(scans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scan> getScanById(@PathVariable("id") Long id) {
        Optional<Scan> scan = scanRepository.findById(Math.toIntExact(id));
        if (scan.isPresent()) {
            return new ResponseEntity<>(scan.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/getByType/{type}/{start}/{end}")
//    public ResponseEntity<List<Scan>> getScanByType(
//            @PathVariable("type") String type,
//            @PathVariable("start") @RequestParam(required = false) String start,
//            @PathVariable("end") @RequestParam(required = false) String end) {
//
//        if(Objects.isNull(type)){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        List<Scan> scans = (List<Scan>) scanRepository.findAll();
//        List<Scan> scansByType = scans.stream()
//                    .filter(doc -> doc.getType().equals(type)).toList();
//
//        if (!start.equals("")){
//            System.out.println("in start but start is " + start);
//            String[] dateHour = start.split("T");
//            String[] startDate = dateHour[0].split("-");
//            String[] startHour = dateHour[1].split(":");
//            LocalDateTime startTime = LocalDateTime.of(Integer.parseInt(startDate[0]),
//                    Month.of(Integer.parseInt(startDate[1])),
//                    Integer.parseInt(startDate[2]),
//                    Integer.parseInt(startHour[0]),
//                    Integer.parseInt(startHour[1]));
//            scansByType = scansByType.stream()
//                    .filter(doc -> doc.getTimestamp().isAfter(startTime)).toList();
//        }
//        if (!end.equals("")){
//            System.out.println("in end but end is " + end);
//            String[] dateHour = end.split("T");
//            String[] endDate = dateHour[0].split("-");
//            String[] endHour = dateHour[1].split(":");
//            LocalDateTime endTime = LocalDateTime.of(Integer.parseInt(endDate[0]),
//                    Month.of(Integer.parseInt(endDate[1])),
//                    Integer.parseInt(endDate[2]),
//                    Integer.parseInt(endHour[0]),
//                    Integer.parseInt(endHour[1]));
//            scansByType = scansByType.stream()
//                    .filter(doc -> doc.getTimestamp().isBefore(endTime)).toList();
//        }
//
//        return new ResponseEntity<>(scansByType, HttpStatus.OK);
//    }

    @GetMapping("/getByType/{data}")
    public ResponseEntity<List<Scan>> getScanByType(
            @PathVariable("data") String data) {

        String[] parameters = data.split("&");
        if(parameters.length > 0 && Objects.isNull(parameters[0])){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        String type = parameters[0];
        List<Scan> scans = (List<Scan>) scanRepository.findAll();
        List<Scan> scansByType = scans.stream()
                .filter(doc -> doc.getType().equals(type)).toList();

        try {
            if (Objects.nonNull(parameters[1])) {
                String[] dateHour = parameters[1].split("T");
                String[] startDate = dateHour[0].split("-");
                String[] startHour = dateHour[1].split(":");
                LocalDateTime startTime = LocalDateTime.of(Integer.parseInt(startDate[0]),
                        Month.of(Integer.parseInt(startDate[1])),
                        Integer.parseInt(startDate[2]),
                        Integer.parseInt(startHour[0]),
                        Integer.parseInt(startHour[1]));
                scansByType = scansByType.stream()
                        .filter(doc -> doc.getTimestamp().isAfter(startTime)).toList();
            }
        }catch (Exception e){
            System.out.println("There is not starDate for this search");
        }

        try {
            if (Objects.nonNull(parameters[2])) {
                String[] dateHour = parameters[2].split("T");
                String[] endDate = dateHour[0].split("-");
                String[] endHour = dateHour[1].split(":");
                LocalDateTime endTime = LocalDateTime.of(Integer.parseInt(endDate[0]),
                        Month.of(Integer.parseInt(endDate[1])),
                        Integer.parseInt(endDate[2]),
                        Integer.parseInt(endHour[0]),
                        Integer.parseInt(endHour[1]));
                scansByType = scansByType.stream()
                        .filter(doc -> doc.getTimestamp().isBefore(endTime)).toList();
            }
        }catch (Exception e){
            System.out.println("There is not endDate dor this search");
        }

        return new ResponseEntity<>(scansByType, HttpStatus.OK);
    }
}
