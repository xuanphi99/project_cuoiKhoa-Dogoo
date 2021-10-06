package com.speedhome.poc.service.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public Constants() {
    }

    public static class FileInfo {

        public static final List<String> format = Arrays.asList("png","jpg","jpeg");
        public static final long MAX_FILE_SIZE = 1 * 1024 * 1024;
        public static final String MAX_FILE_SIZE_MSG = "File size is too limited";
        public static final String ERROR_FORMAT_MSG = "File format not supported , only format support [\"png\",\"jpg\",\"jpeg\"] ";

    }
    public static class ConstantPost {

        public static final String POST_DOES_NOT_EXIST = "Post does not exist";
        public static final String NO_DATA = "Post is empty";
        public static final String FILE_IS_NULL = "File is not null";
        public static final String ERROR_REQUEST = "Fields cannot be left blank";
        public static final int MIN_LENGTH = 3;
        public static final int MAX_LENGTH = 100;
        public static final String TERM_INPUT_INVALID_MSG =
                "Term input is invalid. Please in put at least 3 normal characters";
        public static final String ERROR_INDEX =
                "Error loading index ";
    }
    public static class ContactMSG {

        public static final String MESSAGE_EMPTY = "Message not empty";
        public static final String USER_NAME_EMPTY = "user name not empty";
        public static final String EMAIL_FORMAT = "wrong email format";
        public  static String REGEX_EMAIL = "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";

    }
}
