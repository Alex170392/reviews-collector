package dev.kostya.antonchyk.reviewsextractor.service;

import dev.kostya.antonchyk.reviewsextractor.dto.ReviewDto;

import java.util.List;

public interface ExtractorService {

    List<ReviewDto> getAllReviewByFilmNumber(int filmNumber);

}
