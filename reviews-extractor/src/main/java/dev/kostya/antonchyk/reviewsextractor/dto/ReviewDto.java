package dev.kostya.antonchyk.reviewsextractor.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ReviewDto {

    /**
     * review author's nickname
     * example: marcopinks
     * example: Xiphactinus audax
     */
    String authorNickname;

    String reviewTitle;

    /**
     * Date and time for review
     * example: "27 ноября 2023 | 17:03"
     */
    String reviewDateTime;

    /**
     * Main text content of Review
     */
    String reviewContent;

    String authorPhoto;
    String likeCount;
    String dislikeCount;
    /**
     * Review rating
     * example: 6/10
     * example: 3/10
     */
    String reviewRating;

    /**
     * Type of review
     * example: 1- positive, 2-negative, 0-not defined
     */
    String reviewType;


    /**
     * Direct link to review
     */
    String directLink;

}
