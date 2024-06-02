package dev.kostya.antonchyk.reviewsextractor.service.impl;

import dev.kostya.antonchyk.reviewsextractor.model.Review;
import org.jsoup.Jsoup;

import javax.swing.text.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class ImplementationWorkLogic {
    ServiceExtractor serviceExtractor = new ServiceExtractor();
    String url2 = "https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/10/page/1/";
    String url = "https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/10/page/";
    String urlsecond = "/";
    String filePath = "D://Reviews.txt";

    public void getReviewData() throws IOException {
        serviceExtractor.getDataFromWebToFile(url + 1 + urlsecond, filePath);
        int reviewAmount = serviceExtractor.extractAmountOfReviewsFromFile(filePath);
        int pagesAmount = serviceExtractor.extractAmountOfReviewsPages(filePath);
        System.out.println(reviewAmount);
        System.out.println(pagesAmount);
        ArrayList<Review> reviewList = new ArrayList<>();
        ArrayList<String> listOfAuthors = new ArrayList<>();
        ArrayList<String> listofTitles = new ArrayList<>();
        listOfAuthors = (ArrayList<String>) serviceExtractor.extractAuthorFromFile(filePath);
        listofTitles = (ArrayList<String>) serviceExtractor.extractTitleFromFile(filePath);
        for (int i = 2; i <= 5; i++) {
            serviceExtractor.getDataFromWebToFile(url + i + urlsecond, filePath);
            listOfAuthors.addAll(serviceExtractor.extractAuthorFromFile(filePath));
            listofTitles.addAll(serviceExtractor.extractTitleFromFile(filePath));
        }
        for (int i = 0; i < listOfAuthors.size(); i++) {
            reviewList.add(new Review(listOfAuthors.get(i), listofTitles.get(i)));
        }
        for (Review review :
                reviewList) {
            System.out.println(reviewList);
        }
        System.out.println("size is " + reviewList.size());
    }
    //
//    public void writeWebPageToTXTFile() {
//        serviceExtractor.getDataFromWebToFile(url,filePath);
//    }
//    public List<String> getAuthors() throws IOException {
//      return   serviceExtractor.extractAuthorFromFile(filePath);
//    }
//    public List<String> getTitle() throws IOException {
//        return   serviceExtractor.extractTitleFromFile(filePath);
//    }
//    public int getReviewsAmount() throws IOException {
//        return serviceExtractor.extractAmountOfReviewsFromFile(filePath);
//    }
//    public int getPagesAmount() throws IOException {
//        return serviceExtractor.extractAmountOfReviewsPages(filePath);
//    }
}
