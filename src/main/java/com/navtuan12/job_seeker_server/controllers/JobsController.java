package com.navtuan12.job_seeker_server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @PostMapping("/upload-job")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @PutMapping("update-job/{jobId}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }
    
    @GetMapping("/find-jobs")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/get-job-detail/{jobId}")
    public String getMethodName1(@RequestParam String param) {
        return new String();
    }
    
    
}
