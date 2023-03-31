package com.example.interview.service;

import com.example.interview.entity.Scan;
import com.example.interview.repository.ScanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {

    @Autowired
    private ScanRepository scanRepository;

    @KafkaListener(id = "scan-listener-spring", topics = "scan_topic", groupId = "scan_group", concurrency = "3")
    public void consumeScan(String message) {
        System.out.println("message: " + message);
        Scan scan = new Scan();
        scan.setData(message);
        scan.setTimestamp(LocalDateTime.now());

        scanRepository.save(scan);
    }
}
