package com.anish.telegram.util;

public class Constants {

    private Constants() { }

    public static final class TelegramConstants {

        private TelegramConstants() { }

        public static final String CHAT_ID = "chat_id";
        public static final String PARSE_MODE = "parse_mode";
        public static final String INLINE_KEYBOARD = "inline_keyboard";
        public static final String REPLY_MARKUP = "reply_markup";
    }

    public static final class CommonConstants {

        private CommonConstants() { }

        public static final String TEXT = "text";
        public static final String URL = "url";
        public static final String REQUEST_ID_HEADER = "X-Request-Id";
        public static final String REQUEST_ID = "requestId";
    }

    public static final class SwaggerDocExamples {

        private SwaggerDocExamples() { }

        public static final String SUCCESS = """
                        {
                          "statusCode": 202,
                          "statusMessage": "Accepted",
                          "message": "Notification queued successfully",
                          "data": true
                        }
                """;

        public static final String BAD_REQUEST = """
                        {
                          "statusCode": 400,
                          "statusMessage": "Bad Request",
                          "message": "url : must be a valid URL; buttonTitle : must not be blank; ",
                          "data": false
                        }
                """;

        public static final String INTERNAL_SERVER_ERROR = """
                        {
                          "statusCode": 500,
                          "statusMessage": "Internal Server Error",
                          "message": "Failed to queue the notification",
                          "data": false
                        }
                """;
    }
}
