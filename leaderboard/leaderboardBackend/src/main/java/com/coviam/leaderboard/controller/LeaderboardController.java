package com.coviam.leaderboard.controller;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderboardController {
    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }


}
