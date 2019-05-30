package com.coviam.leaderboard.controller;
import com.coviam.leaderboard.model.CMSRequest;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
    public JSONObject getJSONResponse(Object data){
        JSONObject response = new JSONObject();
        response.put("code", "200");
        response.put("data", data);
        response.put("error","");
        response.put("message", "success");
        return response;
    }

    @GetMapping("/static")
    public JSONObject getStaticLeaderboard(@RequestParam Integer userId, @RequestParam Integer contestId){
        return null;
    }

    @PostMapping("/static")
    public JSONObject insertStaticData(@RequestBody CMSRequest cmsRequest){
        return null;
    }
    @GetMapping("/dynamic")
    public JSONObject getDynamicLeaderboard(@RequestParam Integer userId, @RequestParam Integer contestId){
        return null;
    }
    @PostMapping("/dynamic")
    public JSONObject insertDynamicData(@RequestBody CMSRequest cmsRequest){
        return null;
    }
    @GetMapping("/daily")
    public JSONObject getDailyLeaderboard(){
        return null;
    }
    @GetMapping("/weekly")
    public JSONObject getWeeklyLeaderboard(){
        return null;
    }
    @GetMapping("/monthly")
    public JSONObject getMonthlyLeaderboard(){
        return null;
    }
}
