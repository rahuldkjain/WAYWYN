package com.coviam.leaderboard.scheduler;

import com.coviam.leaderboard.entity.*;
import com.coviam.leaderboard.queryresult.UserAggregateScore;
import com.coviam.leaderboard.repository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class SchedulerTasks {
    private static final Logger logger = LoggerFactory.getLogger(SchedulerTasks.class);
    @Autowired
    MonthlyLeaderboardRepository monthlyLeaderboardRepository;
    @Autowired
    ContestLeaderboardRepository contestLeaderboardRepository;
    @Autowired
    WeeklyLeaderboardRepository weeklyLeaderboardRepository;
    @Autowired
    DailyLeaderboardRepository  dailyLeaderboardRepository;
    @Autowired
    UserScoreRepository userScoreRepository;
    @Autowired
    ContestRepository contestRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuestionDetailsRepository questionDetailsRepository;

    @Scheduled(fixedRate = 2000)
    public void updateContestLeaderboard(){
        // logger.info("Current Thread : {}", Thread.currentThread().getName());
        //todo for each contest
        List<Integer> contests=userScoreRepository.findAllContests();
        for(Integer contest:contests){
            List<UserScore> userScoreList=userScoreRepository.findAllByOrderByScoreDescByContestId(contest);
            List<ContestLeaderboard> contestLeaderboardList=new ArrayList<ContestLeaderboard>();
            int rank =0;
            int previousScore=-1;
            for(UserScore user:userScoreList){
                ContestLeaderboard contestLeaderboard=new ContestLeaderboard();
                contestLeaderboard.setContestId(user.getContestId());
                contestLeaderboard.setScore(user.getScore());
                contestLeaderboard.setUserId(user.getUserId());
                contestLeaderboard.setUsername(user.getUsername());
                if(contestLeaderboard.getScore()!=previousScore){
                    contestLeaderboard.setUserRank(++rank);
                }else {
                    contestLeaderboard.setUserRank(rank);
                }
                previousScore=user.getScore();
                contestLeaderboardList.add(contestLeaderboard);
            }
            System.out.println("\n\nupdateContestLeaderboardThread: ");
            for(UserScore user:userScoreList){
                System.out.println(user.getUsername());
            }
            contestLeaderboardRepository.save(contestLeaderboardList);
        }

        return ;

//        List<UserScore> userScoreList=userScoreRepository.findAllByOrderByScoreDesc();
//        List<ContestLeaderboard> contestLeaderboardList=new ArrayList<ContestLeaderboard>();
//        int rank =0;
//        int previousScore=-1;
//        for(UserScore user:userScoreList){
//            ContestLeaderboard contestLeaderboard=new ContestLeaderboard();
//            contestLeaderboard.setContestId(user.getContestId());
//            contestLeaderboard.setScore(user.getScore());
//            contestLeaderboard.setUserId(user.getUserId());
//            contestLeaderboard.setUsername(user.getUsername());
//            if(contestLeaderboard.getScore()!=previousScore){
//                contestLeaderboard.setUserRank(++rank);
//            }else {
//                contestLeaderboard.setUserRank(rank);
//            }
//            previousScore=user.getScore();
//            contestLeaderboardList.add(contestLeaderboard);
//        }
//        System.out.println("\n\nupdateContestLeaderboardThread: ");
//        for(UserScore user:userScoreList){
//            System.out.println(user.getUsername());
//        }
//        contestLeaderboardRepository.save(contestLeaderboardList);
//        return ;
    }

    @Scheduled(fixedRate = 4000)
    public void updateDailyLeaderboard(){
//        System.out.println("hi i am in update daily");
        java.util.Date today=new java.util.Date();
        Date dateVal=new Date(today.getTime());

        //find group by user id results an agg score
        List<Object> userScoreObjectList=userScoreRepository.findScoreSumGroupByUserId(dateVal);
        Iterator iterator=userScoreObjectList.iterator();
        //copy to agg score list
        List<UserAggregateScore> userScoreList=new ArrayList<UserAggregateScore>();
        while(iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            UserAggregateScore aggregateScore=new UserAggregateScore();
            aggregateScore.setScore(Integer.parseInt(String.valueOf(object[2])));
            aggregateScore.setUser_id(Integer.parseInt(String.valueOf(object[0])));
            aggregateScore.setUsername(String.valueOf(object[1]));
            userScoreList.add(aggregateScore);
        }

        long epoch=System.currentTimeMillis()/1000/60/60/24;
        List<DailyLeaderboard> dailyLeaderboards=new ArrayList<DailyLeaderboard>();
        int rank=0;
        int previousScore=-1;
        //setting the daily leaderboard table
        for(UserAggregateScore userScore:userScoreList){
            DailyLeaderboard dailyLeaderboard=new DailyLeaderboard();
            dailyLeaderboard.setDayId((int)epoch);
            dailyLeaderboard.setUsername(userScore.getUsername());
            dailyLeaderboard.setScore(userScore.getScore());
            if(dailyLeaderboard.getScore()!=previousScore){
                dailyLeaderboard.setUserRank(++rank);
            }else{
                dailyLeaderboard.setUserRank(rank);
            }
            previousScore=dailyLeaderboard.getScore();
            dailyLeaderboards.add(dailyLeaderboard);
        }
        System.out.println("\n\nupdateDailyLeaderboardThread: ");
        for(DailyLeaderboard dailyLeaderboard:dailyLeaderboards){
            System.out.println(dailyLeaderboard.getUsername());
        }
        dailyLeaderboardRepository.save(dailyLeaderboards);
        return ;
    }

    @Scheduled(fixedRate = 6000)
    public void updateWeeklyLeaderboard(){
//        System.out.println("hi i am in update weekly");
        long today=System.currentTimeMillis()/1000/60/60/24;
        long weekId=System.currentTimeMillis()/1000/60/60/24/7;
        long startdate=weekId*7;
//        System.out.println(today+"   -------"+startdate);

        List<Object> dailyLeaderboardlist=dailyLeaderboardRepository.findByUserIdGroupByDateRange(startdate,today);
        Iterator iterator=dailyLeaderboardlist.iterator();
        List<WeeklyLeaderboard> weeklyLeaderboardList=new ArrayList<WeeklyLeaderboard>();

        int rank=0;
        int previousScore=-1;
        while (iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            WeeklyLeaderboard weeklyLeaderboard=new WeeklyLeaderboard();
            weeklyLeaderboard.setUsername(String.valueOf(object[0]));
            weeklyLeaderboard.setScore(Integer.parseInt(String.valueOf(object[1])));
            weeklyLeaderboard.setWeekId((int)weekId);
            if(Integer.parseInt(String.valueOf(object[1]))!=previousScore){
                weeklyLeaderboard.setUserRank(++rank);
            }else{
                weeklyLeaderboard.setUserRank(rank);
            }
            previousScore=weeklyLeaderboard.getScore();
            weeklyLeaderboardList.add(weeklyLeaderboard);
        }
        System.out.println("\n\nupdateWeeklyLeaderboardThread: ");
        for(WeeklyLeaderboard weeklyLeaderboard:weeklyLeaderboardList){
            System.out.println(weeklyLeaderboard.getUsername());
        }
        weeklyLeaderboardRepository.save(weeklyLeaderboardList);
        return ;
    }

    @Scheduled(fixedRate = 8000)
    public void updateMonthlyLeaderboard(){
//        System.out.println("hi i am in update monthly");
        long todayWeek=System.currentTimeMillis()/1000/60/60/24/7;
        long monthId=System.currentTimeMillis()/1000/60/60/24/7/4;
        long startWeek=monthId*4;
        System.out.println(todayWeek+"   -------"+startWeek);

        List<Object> weeklyLeaderboardlist=weeklyLeaderboardRepository.findByUserIdGroupByDateRange(startWeek,todayWeek);
        Iterator iterator=weeklyLeaderboardlist.iterator();
        List<MonthlyLeaderboard> monthlyLeaderboardList=new ArrayList<MonthlyLeaderboard>();

        int rank=0;
        int previousScore=-1;
        while (iterator.hasNext()){
            Object[] object=(Object[]) iterator.next();
            MonthlyLeaderboard monthlyLeaderboard=new MonthlyLeaderboard();
            monthlyLeaderboard.setUsername(String.valueOf(object[0]));
            monthlyLeaderboard.setScore(Integer.parseInt(String.valueOf(object[1])));
            monthlyLeaderboard.setMonthId((int)monthId);
            if(Integer.parseInt(String.valueOf(object[1]))!=previousScore){
                monthlyLeaderboard.setUserRank(++rank);
            }else{
                monthlyLeaderboard.setUserRank(rank);
            }
            previousScore=monthlyLeaderboard.getScore();
            monthlyLeaderboardList.add(monthlyLeaderboard);
        }
        System.out.println("\n\nupdateMonthlyLeaderboardThread: ");
        for(MonthlyLeaderboard monthlyLeaderboard:monthlyLeaderboardList){
            System.out.println(monthlyLeaderboard.getUsername());
        }
        monthlyLeaderboardRepository.save(monthlyLeaderboardList);
        return ;
    }

    @Scheduled(fixedRate = 2000)
    public ResponseEntity updateStaticContestDetails(){

        ResponseEntity returnVal=addStaticContestsToDB();
        if(returnVal.equals(HttpStatus.OK)) {
            return returnVal;
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @Scheduled(fixedRate = 3000)
    public ResponseEntity updateDynamicContestDetails(){
        ResponseEntity returnVal=addDynamicContestsToDB();
        if(!returnVal.equals(HttpStatus.OK)){
            return returnVal;
        }
        return new ResponseEntity(HttpStatus.OK);
    }
    @Scheduled(fixedRate = 10000)
    public ResponseEntity updateQuestionDetails(){
        ResponseEntity returnVal=addQuestionDetails();
        if(!returnVal.equals(HttpStatus.OK)){
            return returnVal;
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    private ResponseEntity addQuestionDetails() {
        System.out.println("\n\naddQuestionDetailsThread: ");
        List<Contest> contestList= (List<Contest>) contestRepository.findAll();
        RestTemplate restTemplate = new RestTemplate();

        for(Contest contest:contestList){
            String cmsContesturl = "http://10.177.7.130:8080/contest";
            ResponseEntity<String> response;
            try{
                response = restTemplate.getForEntity(cmsContesturl + "/getquestionanswerofcontest?contestId="+contest.getContestId(), String.class);
            }catch(Exception ex){
                ex.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ObjectMapper mapper = new ObjectMapper();
            List<QuestionDetails> questionList=new ArrayList<QuestionDetails>();
            try {
                JsonNode jsonArray = mapper.readTree(response.getBody());
                for(JsonNode j: jsonArray) {
//                System.out.println("####" + j.get("contestName"));
                    QuestionDetails question=new QuestionDetails();
                    question.setAnswer(j.get("answer").toString().replaceAll("^\"|\"$", ""));
                    question.setAnswerType(j.get("answerType").toString().replaceAll("^\"|\"$", ""));
                    question.setBinaryFilePath(j.get("binaryFilePath").toString().replaceAll("^\"|\"$", ""));
                    String category=j.get("categoryOfQuestion").toString();
                    if(category!=null){
                        question.setCategoryOfQuestion(category.replaceAll("^\"|\"$", ""));
                    }else{
                        question.setCategoryOfQuestion("nil");
                    }
                    question.setDifficultyLevel(j.get("difficultyLevel").toString().replaceAll("^\"|\"$", ""));
                    question.setOptionA(j.get("optionA").toString().replaceAll("^\"|\"$", ""));
                    question.setOptionB(j.get("optionB").toString().replaceAll("^\"|\"$", ""));
                    question.setOptionC(j.get("optionC").toString().replaceAll("^\"|\"$", ""));
                    question.setQuestionId(Integer.parseInt(j.get("questionId").toString()));
                    question.setQuestionText(j.get("questionText").toString().replaceAll("^\"|\"$", ""));
                    question.setQuestionType(j.get("questionType").toString().replaceAll("^\"|\"$", ""));
                    System.out.println(question.toString());
                    questionList.add(question);
                }
                questionDetailsRepository.save(questionList);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    private ResponseEntity addDynamicContestsToDB() {
        System.out.println("\n\naddDynamicConteststoDBThread: ");
        RestTemplate restTemplate = new RestTemplate();
        String cmsContesturl = "http://10.177.7.130:8080/contest/getbytype";
        ResponseEntity<String> response;
        try{
            response = restTemplate.getForEntity(cmsContesturl + "/dynamic", String.class);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Contest> contestList=new ArrayList<Contest>();
        try {
            JsonNode jsonArray = mapper.readTree(response.getBody());
            for(JsonNode j: jsonArray) {
//                System.out.println("####" + j.get("contestName"));
                Contest contest=new Contest();
                contest.setCategory(j.get("categoryName").toString().replaceAll("^\"|\"$", ""));
                contest.setContestId(Integer.parseInt(j.get("contestId").toString()));
                contest.setType(j.get("contestType").toString().replaceAll("^\"|\"$", ""));
                long Date=Long.parseLong(j.get("endTimeOfContest").toString().replaceAll("^\"|\"$", ""));
                java.util.Date date=new java.util.Date(Date);
                java.sql.Date endDate=new Date(date.getTime());
                contest.setDate(endDate);
                contest.setContestName(j.get("contestName").toString());
                System.out.println(contest.toString());
                contestList.add(contest);
            }
            contestRepository.save(contestList);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity addStaticContestsToDB() {
        System.out.println("\n\naddStaticConteststoDBThread: ");
        RestTemplate restTemplate = new RestTemplate();
        String cmsContesturl = "http://10.177.7.130:8080/contest/getbytype";
        ResponseEntity<String> response;
        try{
            response = restTemplate.getForEntity(cmsContesturl + "/static", String.class);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ObjectMapper mapper = new ObjectMapper();
        List<Contest> contestList=new ArrayList<Contest>();
        try {
            JsonNode jsonArray = mapper.readTree(response.getBody());
            for(JsonNode j: jsonArray) {
//                System.out.println("####" + j.get("contestName"));
                Contest contest=new Contest();
                contest.setCategory(j.get("categoryName").toString().replaceAll("^\"|\"$", ""));
                contest.setContestId(Integer.parseInt(j.get("contestId").toString()));
                contest.setType(j.get("contestType").toString().replaceAll("^\"|\"$", ""));
                long Date=Long.parseLong(j.get("endTimeOfContest").toString());
                java.util.Date date=new java.util.Date(Date);
                java.sql.Date endDate=new Date(date.getTime());
                contest.setDate(endDate);
                contest.setContestName(j.get("contestName").toString().replaceAll("^\"|\"$", ""));
                System.out.println(contest.toString());
                contestList.add(contest);
            }
            contestRepository.save(contestList);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
