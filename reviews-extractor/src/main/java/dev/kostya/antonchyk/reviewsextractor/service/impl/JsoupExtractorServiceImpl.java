package dev.kostya.antonchyk.reviewsextractor.service.impl;

import dev.kostya.antonchyk.reviewsextractor.dto.ReviewDto;
import dev.kostya.antonchyk.reviewsextractor.model.Review;
import dev.kostya.antonchyk.reviewsextractor.service.ExtractorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JsoupExtractorServiceImpl implements ExtractorService {

    private static final Logger logger = LoggerFactory.getLogger(JsoupExtractorServiceImpl.class);


    @Override
    public List<ReviewDto> getAllReviewByFilmNumber(int filmNumber) {
        //todo proccess for extracting all film review by number of film........
        String url = String.format("https://www.kinopoisk.ru/film/%d/reviews/", filmNumber);
        Document doc = getHtmlPageDocument(url);

        Elements reviewElements = getReviewElements(doc);


        List<ReviewDto> resultList = new ArrayList<>();
        // Находим все элементы <p> с классом "sub_title"
//        Elements reviewList = doc.select("p.sub_title");
        for (Element review : reviewElements) {

            String authorNickName = extractAuthorName(review);
            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//            String reviewRating = extractReviewRating(review);
//
            ReviewDto reviewDto = ReviewDto.builder()
                    .authorNickname(authorNickName)
                    .reviewRating(reviewRating)
                    .likeCount("43")
                    .dislikeCount("8")
                    .reviewTitle("blabla title")
                    .build();

            resultList.add(reviewDto);
        }
        return resultList;
//        List<ReviewDto> reviewList = fakeReviews();
//        return reviewList;
    }

    /**
     * This method extract review rating from current review
     *
     * @param review - html Element block that contain info about one review
     * @return String value of rating
     */
    private String extractReviewRating(Element review) {
//        review.select("p.sub_title");
        //todo написать реализацию, которая достанет рейтинг из Element(по сути Рецензия)
        return "6/10";
    }

    private String extractAuthorName(Element review) {
//        review.select("p.sub_title");
        //todo написать реализацию, которая достанет Имя автора из Element(по сути Рецензия)
        // Извлекаем все элементы <p> с классом "profile_name"
        try {
            Elements profileNames = review.select("p.profile_name");

            // Проходимся по найденным элементам
            for (Element profileName : profileNames) {
                // Находим элемент <a> внутри <p> и получаем его текст
                Element anchor = profileName.selectFirst("a[itemprop=name][data-popup-info=disabled]");
                if (anchor != null) {
                    String userName = anchor.text();
                    logger.info(String.format("extracting authorNickName=%s finished successfulyy", userName));
                    return userName;
                }
            }
        } catch (Exception e) {
            logger.error("extracting authorNickName was finished with error", e);
        }
        return "";
    }

    private Elements getReviewElements(Document document) {
        //todo написать метод, который достанет список элементов, где каждый элемент - это блок ревью(с сайта)
        Elements resultElements = null;
        try {
            resultElements = document.select("div.reviewItem.userReview");
            logger.info("getting HTML Elements-blocks that contain info about review was finished successfully");
        } catch (Exception e) {
            logger.error("getting HTML Elements-blocks that contain info about review was fails with error", e);
        }
        return resultElements;
    }

    private Document getHtmlPageDocument(String url) {
        logger.info(String.format("try to connect to html page with url=%s", url));
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            logger.info(String.format("connecting to url=%s was finished successfully", url));
        } catch (Exception e) {
            logger.error(String.format("connect to html page with url=%s fail with error", url), e);
            throw new RuntimeException(e);
        }
        return doc;
    }

    private static List<ReviewDto> fakeReviews() {
        ReviewDto review1 = new ReviewDto();
        review1.setAuthorNickname("vasya nemodnij");
        review1.setReviewType("1");
        review1.setLikeCount("35");
        review1.setDislikeCount("20");

        ReviewDto review2 = ReviewDto.builder()
                .authorNickname("bogach kolach98")
                .reviewRating("6/10")
                .likeCount("43")
                .dislikeCount("8")
                .reviewTitle("blabla title")
                .build();

        ReviewDto review3 = ReviewDto.builder()
                .authorNickname("dart veider")
                .reviewRating("8/10")
                .likeCount("123")
                .dislikeCount("88")
                .reviewTitle("blabla2 title")
                .build();

        List<ReviewDto> reviewList = List.of(review1, review2, review3);
        return reviewList;
    }
}
