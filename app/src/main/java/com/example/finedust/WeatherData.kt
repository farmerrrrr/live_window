package com.example.finedust

import android.util.Log
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


class WeatherData {
    private var weather = ""
    private var tmperature = ""

    fun lookUpWeather(baseDate: String, time: String, nx: String, ny: String): String {

        var baseDate = "20211129" //조회하고싶은 날짜
        val baseTime = "1300"  //"0500" //조회하고싶은 시간
        val type = "JSON"

        val apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0"

        //홈페이지에서 받은 키
        val serviceKey = "1n2P9CU6T7l5VGlE2fFW4uQGe5vwFSb17Lgz%2B7f%2Be7hKmIn7iIiNSMavwMo6GSFLHoeZnW9UlIyDgt44TulUPg%3D%3D"

        val urlBuilder = StringBuilder(apiUrl)
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey)
        urlBuilder.append(
            "&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(
                nx, "UTF-8"
            )
        ) //경도
        urlBuilder.append(
            "&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(
                ny, "UTF-8"
            )
        ) //위도
        urlBuilder.append(
            "&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(
                baseDate, "UTF-8"
            )
        ) /* 조회하고싶은 날짜*/
        urlBuilder.append(
            "&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(
                baseTime, "UTF-8"
            )
        ) /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append(
            "&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(
                type, "UTF-8"
            )
        )    /* 타입 */

        /*
         * GET방식으로 전송해서 파라미터 받아오기
         */


        //어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
        //System.out.println(url);

        val url = URL(urlBuilder.toString())
        var conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-type", "application/json")

        // 데이터 읽기
        val rd: BufferedReader
        rd = if (conn.responseCode >= 200 && conn.responseCode <= 300) {
            BufferedReader(InputStreamReader(conn.inputStream))
        } else {
            BufferedReader(InputStreamReader(conn.errorStream))
        }
        val sb = java.lang.StringBuilder()
        var line: String?
        while (rd.readLine().also { line = it } != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        val result = sb.toString()

        //======= json에서 데이터 파싱해 오는 부분 =====//

        // response 키를 가지고 데이터를 파싱
        val jsonObj_1 = JSONObject(result)
        val response = jsonObj_1.getString("response")

        // response 로 부터 body 찾기
        val jsonObj_2 = JSONObject(response)
        val body = jsonObj_2.getString("body")

        // body 로 부터 items 찾기
        val jsonObj_3 = JSONObject(body)
        val items = jsonObj_3.getString("items")
        Log.i("ITEMS", items)

        // items로 부터 itemlist 를 받기
        var jsonObj_4 = JSONObject(items)
        val jsonArray = jsonObj_4.getJSONArray("item")

        for (i in 0 until jsonArray.length()) {
            jsonObj_4 = jsonArray.getJSONObject(i)
            val fcstValue = jsonObj_4.getString("fcstValue")
            val category = jsonObj_4.getString("category")
            if (category == "SKY") {
                weather = "현재 날씨는 "
                if (fcstValue == "1") {
                    weather += "맑은 상태로"
                } else if (fcstValue == "2") {
                    weather += "비가 오는 상태로 "
                } else if (fcstValue == "3") {
                    weather += "구름이 많은 상태로 "
                } else if (fcstValue == "4") {
                    weather += "흐린 상태로 "
                }
            }
            if (category == "T3H" || category == "T1H") {
                tmperature = "기온은 $fcstValue℃"
            }
            Log.i("날씨", fcstValue)
            Log.i("카테고리", category)
            Log.i("지금 날씨는", weather + tmperature)
        }

        return weather + tmperature
    }

    fun timeChange(time: String): String {
        // 현재 시간에 따라 데이터 시간 설정(3시간 마다 업데이트) //
        /**
        시간은 3시간 단위로 조회해야 한다. 안그러면 정보가 없다고 뜬다.
        0200, 0500, 0800 ~ 2300까지
        그래서 시간을 입력했을때 when문으로 조회 가능한 시간대로 변경해주었다.
         **/
       var time = when (time) {
            "0200", "0300", "0400" -> "0200"
            "0500", "0600", "0700" -> "0500"
            "0800", "0900", "1000" -> "0800"
            "1100", "1200", "1300" -> "1100"
            "1400", "1500", "1600" -> "1400"
            "1700", "1800", "1900" -> "1700"
            "2000", "2100", "2200" -> "2000"
            else -> "2300"
        }

        return time

    }

}


