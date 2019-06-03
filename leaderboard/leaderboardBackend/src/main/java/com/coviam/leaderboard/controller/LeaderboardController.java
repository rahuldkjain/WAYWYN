package com.coviam.leaderboard.controller;
import com.coviam.leaderboard.model.CMSDynamicRequest;
import com.coviam.leaderboard.model.CMSStaticRequest;
import com.coviam.leaderboard.service.LeaderboardServiceImpl.DynamicLeaderboardImpl;
import com.coviam.leaderboard.service.LeaderboardServiceImpl.OverallLeaderboardImpl;
import com.coviam.leaderboard.service.LeaderboardServiceImpl.StaticLeaderboardImpl;
import com.coviam.leaderboard.service.ReportServiceImpl.ReportServiceImpl;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    DynamicLeaderboardImpl dynamicLeaderboardService;

    @Autowired
    StaticLeaderboardImpl staticLeaderboardService;

    @Autowired
    OverallLeaderboardImpl overallLeaderboardService;

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping("/static")
    public JSONObject getStaticLeaderboard(@RequestParam Integer userId, @RequestParam Integer contestId,@RequestParam Integer noOfRecords){
        Object data = staticLeaderboardService.getStaticLeaderboard(userId, contestId,noOfRecords);
        JSONObject response = getJSONResponse(data);

        System.out.println("GET /static : "+response.toJSONString());
        return response;
    }

    @PostMapping(value = "/static", consumes = "application/json")
    public JSONObject insertStaticData(@RequestBody CMSStaticRequest cmsStaticRequest){
        JSONObject response;
        if(cmsStaticRequest==null || cmsStaticRequest.getQuestions().isEmpty()){
            response=getJSONResponse(null);
            response.replace("error","Request cannot be null");
            response.replace("message","failure");
            response.replace("code","400");
        }else{
            String data = staticLeaderboardService.insertStaticData(cmsStaticRequest);
            response = getJSONResponse(data);
        }
        System.out.println("POST /static : "+response.toJSONString());
        return response;
    }
    @GetMapping("/dynamic")
    public JSONObject getDynamicLeaderboard(@RequestParam Integer userId, @RequestParam Integer contestId,@RequestParam Integer noOfRecords){
        Object data = dynamicLeaderboardService.getDynamicLeaderboard(userId, contestId,noOfRecords);
        JSONObject response = getJSONResponse(data);

        System.out.println("GET /dynamic : "+response.toJSONString());
        return response;
    }
    @PostMapping(value = "/dynamic", consumes = "application/json")
    public JSONObject insertDynamicData(@RequestBody CMSDynamicRequest cmsDynamicRequest){
        JSONObject response;
        if(cmsDynamicRequest==null ||cmsDynamicRequest.getResponse().isEmpty()){
            response=getJSONResponse(null);
            response.replace("error","Request cannot be null");
            response.replace("message","failure");
            response.replace("code","400");
        }else {
            Object data = dynamicLeaderboardService.insertDynamicData(cmsDynamicRequest);
            response = getJSONResponse(data);
        }

        System.out.println("POST /dynamic : "+response.toJSONString());
        return response;
    }
    @GetMapping("/daily")
    public JSONObject getDailyLeaderboard(){
        Object data = overallLeaderboardService.getDailyLeaderboard();
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("message","failure");
            response.replace("error","No entry for today");
            response.replace("code","200");
        }
        System.out.println("GET /daily : "+response.toJSONString());
        return response;
    }
    @GetMapping("/weekly")
    public JSONObject getWeeklyLeaderboard(){
        Object data = overallLeaderboardService.getWeeklyLeaderboard();
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("message","failure");
            response.replace("error","No entry for the week");
            response.replace("code","200");
        }
        System.out.println("GET /weekly : "+response.toJSONString());
        return response;
    }
    @GetMapping("/monthly")
    public JSONObject getMonthlyLeaderboard(){
        Object data = overallLeaderboardService.getMonthlyLeaderboard();
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("message","failure");
            response.replace("error","No entry for the month");
            response.replace("code","200");
        }
        System.out.println("GET /monthly : "+response.toJSONString());
        return response;
    }

    @GetMapping("/reports/topquestion")
    public JSONObject getTopQuestions(){
        Object data = reportService.mostCorrectlyAnswered();
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("message","failure");
            response.replace("error","No question in DB/ No question is answered");
            response.replace("code","500");
        }

        System.out.println("GET /reports/topquestion : "+response.toJSONString());
        return response;
    }

    @GetMapping("/reports/activeusers")
    public JSONObject getActiveUsers(@RequestParam Integer contestId){
        Object data = reportService.numberOfActiveUsers(contestId);
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("error","No active users");
            response.replace("code","500");
        }
        System.out.println("GET /reports/topquestion : "+response.toJSONString());
        return response;
    }

    @GetMapping("/reports/activecontests")
    public JSONObject getActiveContests(){
        Object data = reportService.numberOfActiveContests();
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("error","No active contests");
            response.replace("code","500");
        }
        System.out.println("GET /reports/activecontests : "+response.toJSONString());
        return response;
    }

    @GetMapping("/reports/winners")
    public JSONObject getWinners(@RequestParam Integer contestId){
        Object data = reportService.getWinners(contestId);
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("error","No contestants for the particular contest");
            response.replace("code","500");
        }
        System.out.println("GET /reports/winners : "+response.toJSONString());
        return response;
    }
    @GetMapping("/contest")
    public JSONObject getContestLeaderboard(@RequestParam Integer contestId){
        Object data = overallLeaderboardService.getContestLeaderboard(contestId);
        JSONObject response = getJSONResponse(data);
        if(((List) data).isEmpty()){
            response.replace("error","No contestants for the particular contest");
            response.replace("code","500");
        }
        System.out.println("GET /contest : "+response.toJSONString());
        return response;
    }
}
