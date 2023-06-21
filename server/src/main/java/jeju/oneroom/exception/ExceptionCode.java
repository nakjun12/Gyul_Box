package jeju.oneroom.exception;

import lombok.Getter;

public enum ExceptionCode {
    // BAD REQUEST
    CERTIFICATION_NOT_MATCH(400, "Invalid code."),
    CODE_EXPIRATION(400, "This code has expired."),

    // UNAUTHORIZED
    UNAUTHORIZED(401,"Unauthorized"),
    NOT_CERTIFIED(401,"Certification required."),

    // FORBIDDEN
    NO_PERMISSION_TO_EDIT(403, ""),
    NO_PERMISSION_TO_CREATE(403, ""),
    NO_PERMISSION_TO_DELETE(403, ""),
    NO_PERMISSION_TO_LIKE(403,"Author Can't Click"),
    NOT_MATCH_MEMBER(403, "Doesn't Belong This Member"),

    // NOT FOUND
    NOT_CERTIFICATION(404, "It is not certified"),
    NOT_FOUND_AREA(404, "Area not found"),
    NOT_FOUND_HOUSE_INFO(404, "HouseInfo Not Found"),
    NOT_FOUND_MESSAGE_ROOM(404, "MessageRoom Not Found"),
    NOT_FOUND_REVIEW(404,"Review Not Found"),
    NOT_FOUND_USER(404,"User Not Found"),
    NOT_FOUND_CATEGORY(404, "Category Not Found"),
    NOT_FOUND_SHOWCASE(404,"Showcase Not Found"),
    NOT_FOUND_POST(404, "Post Not Found"),
    NOT_FOUND_POSTCOMMENT(404, "PostComment Not Found"),

    // Conflict
    EXISTS_MESSAGEROOM(409,"MessageRoom Is Exists"),
    EXISTS_NICKNAME(409,"NickName Is Exists"),
    EXISTS_POST(409,"Post Is Exists");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
