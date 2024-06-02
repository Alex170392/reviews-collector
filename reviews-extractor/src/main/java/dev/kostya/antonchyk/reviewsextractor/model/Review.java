package dev.kostya.antonchyk.reviewsextractor.model;

public class Review {
    private String authorReview;
    private String titleReview;

    public Review() {
    }

    public Review(String authorReview, String titleReview) {
        this.authorReview = authorReview;
        this.titleReview = titleReview;
    }

    public String getAuthorReview() {
        return authorReview;
    }

    @Override
    public String toString() {
        return "Review{" +
                "authorReview='" + authorReview + '\'' +
                ", titleReview='" + titleReview + '\'' +
                '}';
    }

    public void setAuthorReview(String authorReview) {
        this.authorReview = authorReview;
    }

    public String getTitleReview() {
        return titleReview;
    }

    public void setTitleReview(String titleReview) {
        this.titleReview = titleReview;
    }
}
