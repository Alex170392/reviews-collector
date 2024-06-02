package dev.kostya.antonchyk.reviewsextractor.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ServiceExtractor implements ServiceExtractorInterface {



    public void getDataFromWebToFile(String url, String filePath) {

        try {
            // Получаем HTML-код страницы по URL
            Document document = Jsoup.connect(url).get();

            // Получаем HTML-код как строку
            String htmlContent = document.html();

            // Записываем HTML-код в файл
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(htmlContent);
            }


            System.out.println("HTML content successfully written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while fetching or writing HTML content.");
        }

    }

    @Override
    public List<String> extractAuthorFromFile(String filePath) throws IOException {
        // Read the HTML content from the file
        File inputFile = new File(filePath);
        Document document = Jsoup.parse(inputFile, "UTF-8");
        return extractAuthorNameFromDocument(document);
    }

    public List<String> extractAuthorFromHtml(String filePath) throws IOException {
        Document document = Jsoup.parse("https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/10/page/1/", "UTF-8");
        // Select the elements containing the authors' names
        return extractAuthorNameFromDocument(document);
    }


    private List<String> extractAuthorNameFromDocument(Document document) {
        // Select the elements containing the authors' names
        Elements authorElements = document.select("a[itemprop=name]");

        // Extract and return the authors' names
        List<String> authors = new ArrayList<>();
        for (Element authorElement : authorElements) {
            String authorName = authorElement.text();
            authors.add(authorName);
        }

        return authors;
    }

    @Override
    public List<String> extractTitleFromFile(String filePath) throws IOException {
        // Read the HTML content from the file
        File inputFile = new File(filePath);
        Document document = Jsoup.parse(inputFile, "UTF-8");

        return extractTitleFromDocument(document);
    }

    private List<String> extractTitleFromDocument(Document document) {
        // Select the elements containing the titles
        Elements titleElements = document.select("p.sub_title");

        // Extract and return the titles
        List<String> titles = new ArrayList<>();
        for (Element titleElement : titleElements) {
            String title = titleElement.text();
            titles.add(title);
        }

        return titles;
    }

    @Override
    public int extractAmountOfReviewsFromFile(String filePath) throws IOException {
        // Read the HTML content from the file
        File inputFile = new File(filePath);
        Document document = Jsoup.parse(inputFile, "UTF-8");

        return extractAmountOfReviewsFromDocument(document);
    }

    private int extractAmountOfReviewsFromDocument(Document document) {
        // Select the element containing the pages info
        Element pagesElement = document.selectFirst("div.pagesFromTo");

        if (pagesElement != null) {
            // Extract the number from the text content
            String pagesText = pagesElement.text();
            String[] parts = pagesText.split(" из ");
            if (parts.length == 2) {
                try {
                    return Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    @Override
    public int extractAmountOfReviewsPages(String filePath) throws IOException {
//        // Read the HTML content from the file
//        File inputFile = new File(filePath);
//        Document document = Jsoup.parse(inputFile, "UTF-8");
//
//        // Select the last pagination link containing the page number
//        Elements pageLinks = document.select("a[href*=/film/326/reviews/ord/date/status/all/perpage/10/page/]");
//
//        int totalPages = 0;
//        for (Element pageLink : pageLinks) {
//            String href = pageLink.attr("href");
//            String[] parts = href.split("/page/");
//            if (parts.length == 2) {
//                try {
//                    int pageNumber = Integer.parseInt(parts[1].replace("/", ""));
//                    if (pageNumber > totalPages) {
//                        totalPages = pageNumber;
//                    }
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return totalPages;


        // Регулярное выражение для нахождения номера страницы
        String regex = "/page/(\\d+)/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("<a href=\"/film/326/reviews/ord/date/status/all/perpage/10/page/57/\">»»</a>");

        if (matcher.find()) {
            try {
                // Возвращаем найденное число как int
                return Integer.parseInt(matcher.group(1));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Возвращаем 0, если номер страницы не найден
        return 0;


    }
}
