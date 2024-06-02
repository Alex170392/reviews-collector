package dev.kostya.antonchyk.reviewsextractor;

import dev.kostya.antonchyk.reviewsextractor.dto.ReviewDto;
import dev.kostya.antonchyk.reviewsextractor.model.Review;
import dev.kostya.antonchyk.reviewsextractor.service.impl.ImplementationWorkLogic;
import dev.kostya.antonchyk.reviewsextractor.service.impl.JsoupExtractorServiceImpl;
import dev.kostya.antonchyk.reviewsextractor.service.impl.ServiceExtractor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewsExtractorApplication {
    public static void main(String[] args) throws IOException {
        List<ReviewDto> reviewDtoList = new JsoupExtractorServiceImpl().getAllReviewByFilmNumber(326);


//        ImplementationWorkLogic implementationWorkLogic = new ImplementationWorkLogic();
//       implementationWorkLogic.getReviewData();
//        ServiceExtractor serviceExtractor=new ServiceExtractor();
//        serviceExtractor.getDataFromWebToFile("https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/10/page/1/","D://Reviews.txt");
//        System.out.println(serviceExtractor.extractAmountOfReviewsPages("D://Reviews.txt"));

        //
//        // Read and save data to .txt file
//        //  implementationWorkLogic.writeWebPageToTXTFile();

        //Read Authors from .txt file and save in List

//        List<String> authors = null;
//        List<String> titles = null;
//        try {
//            authors = implementationWorkLogic.getAuthors();
//            System.out.println("Authors: " + authors);
//
//            titles = implementationWorkLogic.getTitle();
//            System.out.println("Titles: " + titles);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("An error occurred while reading the file or extracting authors.");
//        }
//        ArrayList<Review> reviewArrayList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            reviewArrayList.add(new Review(authors.get(i),titles.get(i) ));
//        }
//        System.out.println();
//        for (Review review:
//             reviewArrayList) {
//            System.out.println(review.toString());
//        }


    }


}
