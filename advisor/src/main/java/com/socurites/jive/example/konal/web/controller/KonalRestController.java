package com.socurites.jive.example.konal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.socurites.jive.core.bot.JiveScriptBot;
import com.socurites.jive.core.bot.builder.JiveScriptReplyBuilder;
import com.socurites.jive.example.konal.web.domain.Domain;
import com.socurites.jive.example.konal.web.domain.KonalDistrict;
import com.socurites.jive.example.konal.web.repository.KonalDistrictRepository;
import com.socurites.jive.ext.analyze.entity.JiveExtDomainEntity;
import com.socurites.jive.ext.analyze.entity.JiveScriptExtBot;
import com.socurites.jive.ext.analyze.entity.JiveScriptExtReplyBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api", method =  {RequestMethod.GET, RequestMethod.POST})
public class KonalRestController {
    @Autowired
    private JiveScriptBot bot;

    @Autowired
    private KonalDistrictRepository disctrictRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    public static final Logger logger = LoggerFactory.getLogger(KonalRestController.class);

    @RequestMapping(value = "/domain/", method = {RequestMethod.GET, RequestMethod.POST})
    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    @ResponseBody
    //public ResponseEntity<Domain> requestPostAnswer(@RequestBody Domain domain) {
    public ResponseEntity<Domain> requestPostAnswer(@RequestBody Domain domain) {
    	
    	
    	System.out.println("=========== Rest API Called ==============================");
        String name = domain.getName();
        String message = domain.getMessage();
        System.out.println("[Domain name ] : " + name);
        System.out.println("[Domain message ] : " + message);

        JiveScriptReplyBuilder reply = bot.reply(name, message);

        String weatherResult = "";

        if (bot instanceof JiveScriptExtBot) {
            JiveScriptExtBot extBot = (JiveScriptExtBot) bot;
            reply.getReplyAsText();
            JiveExtDomainEntity extDomain = ((JiveScriptExtReplyBuilder) reply).getDomainEntity();
            System.out.println(extDomain);

            if (extDomain.isEmpty()) {
                System.out.println("[날씨가 아닐경우 ] : " + reply.getReplyAsText());
                domain.setName(name);
                domain.setMessage(reply.getReplyAsText());
                return new ResponseEntity<Domain>(domain, HttpStatus.OK);
            }

            try {
                String sido = (extDomain.hasProp("sido")) ? extDomain.getProp("sido") : "";
                String sigungu = (extDomain.hasProp("sigungu")) ? extDomain.getProp("sigungu") : "";
                String town = (extDomain.hasProp("town")) ? extDomain.getProp("town") : "";

                KonalDistrict standardDistrcit = disctrictRepository.getStandardDistrcit(sido, sigungu, town);
                System.out.println("standardDistrcit=" + standardDistrcit);

//                ResponseEntity<String> responseEntity = restTemplate.exchange(
//                        "http://apis.skplanetx.com/weather/current/hourly?lon=&village=" + standardDistrcit.getTown() + "&county=" + standardDistrcit.getSigungu() + "&lat=&city=" + standardDistrcit.getSido() + "&version=1", HttpMethod.GET,
//                        httpEntity, String.class);
//                String responseBody = responseEntity.getBody();
                 String responseBody = "해당기능은 제공되지 않습니다.";

//                JsonObject jsonObj = new JsonParser().parse(responseBody).getAsJsonObject();
//                JsonArray hourlyWeatherArray = jsonObj.get("weather").getAsJsonObject().get("hourly").getAsJsonArray();
//
//                if (hourlyWeatherArray != null) {
//                    JsonObject hourlyWeatherObject = hourlyWeatherArray.get(0).getAsJsonObject();
//
//                    String sky = hourlyWeatherObject.get("sky").getAsJsonObject().get("name").getAsString().replaceAll("\"", "");
//
//                    String tempCurr = hourlyWeatherObject.get("temperature").getAsJsonObject().get("tc").getAsString().replaceAll("\"", "");
//
//                    String tempMax = hourlyWeatherObject.get("temperature").getAsJsonObject().get("tmax").getAsString().replaceAll("\"", "");
//
//                    String tempMin = hourlyWeatherObject.get("temperature").getAsJsonObject().get("tmin").getAsString().replaceAll("\"", "");
//
//                    String humidity = hourlyWeatherObject.get("humidity").getAsString().replaceAll("\"", "");
//
//                    weatherResult += "\n";
//                    weatherResult += sky + ", 현재 온도는 " + tempCurr + "(최저: " + tempMin + ", 최고: " + tempMax + "), 습도는 " + humidity + "%입니다.";
//                } else {
//                    weatherResult += "\n";
//                    weatherResult += "내가 알고 있는 지역이 아닌 듯..";
//                }

                // weatherResult += sky + ", 현재 온도는 " + tempCurr + "(최저: " + tempMin + ", 최고: " + tempMax + "), 습도는 " + humidity + "%입니다.";
                //weatherResult += "<br>현재 온도는  10도 (최저: 5도, 최고: 12도 ), 습도는 40%입니다.";
                 weatherResult += "현재 기상청과 연결이 되지 않습니다." ;

            } catch (Exception e) {
                e.printStackTrace();
                domain.setName(name);
                domain.setMessage(reply.getReplyAsText());
                return new ResponseEntity<Domain>(domain, HttpStatus.OK);
            }
        } else {
            weatherResult = message;
        }

        System.out.println("응답 : " + reply.getReplyAsText() + weatherResult);

        domain.setMessage(reply.getReplyAsText() + weatherResult);
        domain.setName(name);

        return new ResponseEntity<Domain>(domain, HttpStatus.OK);
    }
}
