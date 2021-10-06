package com.speedhome.poc.service.util;

import com.speedhome.poc.service.exception.BadRequestException;
import groovy.util.logging.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Slf4j
public class CommonUtil {


    public CommonUtil() {
    }



    public static void isNullOrEmpty(Object obj, String errorMsg) {
        if (obj == null)
            throw new BadRequestException(errorMsg);

        if (obj instanceof String && ((String) obj).isEmpty() )
            throw new BadRequestException(errorMsg) ;

        if (obj instanceof MultipartFile && ((MultipartFile) obj).isEmpty() ) {
            throw new BadRequestException(errorMsg) ;
        }

    }

    public static boolean nullOrEmpty(MultipartFile objects) {
        return objects == null || objects.isEmpty();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean nullOrEmpty(LocalDateTime input) {
        return input == null || input.toString().trim().isEmpty();
    }

    public static boolean notNullOrEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static String convertLocalDateToString(LocalDate date, String format) {
        if (Objects.isNull(date)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }

    public static LocalDate convertStringToLocalDate(String value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, new Locale("en"));
        if (value == null || value.trim().isEmpty()) {
            return null;
        } else if (value.contains(".")) {
            value = value.substring(0, value.indexOf('.'));
        }
        return LocalDate.parse(value, formatter);
    }
    public static String localDateToString(LocalDateTime value, String format) {
        if (!notNull(value)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return value.format(formatter); // "1986-04-08 12:30"
    }

    public static String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
