package com.northcoders.makemydayapi.dto.ticketmaster;

import com.google.gson.annotations.SerializedName;

public class Dates {

    @SerializedName("start")
    private Start start;

    public Start getStart() {
        return start;
    }

    public void setStart(Start start) {
        this.start = start;
    }

    public static class Start {

        @SerializedName("localDate")
        private String localDate;

        @SerializedName("localTime")
        private String localTime;

        public String getLocalDate() {
            return localDate;
        }

        public void setLocalDate(String localDate) {
            this.localDate = localDate;
        }

        public String getLocalTime() {
            return localTime;
        }

        public void setLocalTime(String localTime) {
            this.localTime = localTime;
        }
    }


//    "dates": {
//        "start": {
//            "localDate": "2024-10-01",
//                    "localTime": "19:00:00",
//                    "dateTime": "2024-10-01T18:00:00Z",
//                    "dateTBD": false,
//                    "dateTBA": false,
//                    "timeTBA": false,
//                    "noSpecificTime": false
//        },
//        "timezone": "Europe/London",
//                "status": {
//            "code": "onsale"
//        },
//        "spanMultipleDays": false
//    },

}
