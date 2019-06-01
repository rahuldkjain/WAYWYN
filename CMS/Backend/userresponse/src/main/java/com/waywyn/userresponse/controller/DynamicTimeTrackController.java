package com.waywyn.userresponse.controller;

import com.waywyn.userresponse.DTO.DynamicTimeTrackDTO;
import com.waywyn.userresponse.service.DynamicTimeTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DynamicTimeTrackController {

    @Autowired
    private DynamicTimeTrackService dynamicTimeTrackService;

    @PostMapping(value = "userresponse/addtime", consumes = {"application/json"})
    public String addTime (@RequestBody DynamicTimeTrackDTO dynamicTimeTrackDTO) {
        return dynamicTimeTrackService.addTime(dynamicTimeTrackDTO);
    }
}
