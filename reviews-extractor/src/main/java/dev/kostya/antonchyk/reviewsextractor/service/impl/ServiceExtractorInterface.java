package dev.kostya.antonchyk.reviewsextractor.service.impl;

import java.io.IOException;
import java.util.List;

public interface ServiceExtractorInterface {
    // method read web page and record to txt. file
    void getDataFromWebToFile(String url, String filePath);

    // find and extarxt author from file
    List<String> extractAuthorFromFile(String filePath) throws IOException;

    // find and extarxt title from file
    List<String> extractTitleFromFile(String filePath) throws IOException;

    // find and extarxt author from file
    int extractAmountOfReviewsFromFile(String filePath) throws IOException;

    //find and extractamount of reviews pages
    int  extractAmountOfReviewsPages(String filePath) throws IOException;



}
