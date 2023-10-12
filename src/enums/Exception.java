package enums;

import java.time.LocalDateTime;

public enum Exception {
    PASSWORD_OR_LOGIN_IS_WRONG_EXCEPTION(401,"Your Login or Password is Wrong",LocalDateTime.now()),
    OPERATION_NOT_FOUND_EXCEPTION(402,"Choosed you operation is doesn't exist !",LocalDateTime.now()),
    INSUFFCIENT_FOUNDS_EXCEPTION(504,"Insuffcient founds in your balance !",LocalDateTime.now()),
    OBJECT_NOT_FOUND_EXCEPTION(507,"Object in this Id not found .",LocalDateTime.now()),
    BOOK_NOT_FOUND_EXCEPTION(417," A Book by this Name not found .",LocalDateTime.now()),
    DONT_HAVE_EMPTY_COURIER_EXCEPTION(477,"At the moment all Couriers are busy",LocalDateTime.now()),
    DONT_CANCELED_EXCEPTION(509,"This Order cannot be canceled",LocalDateTime.now()),
    DONT_HAVE_ORDER_EXCEPTION(570,"You Dont have Order for cancel",LocalDateTime.now());

    private final int status;
    private final String message;
    private final LocalDateTime expDate;

    Exception(int status,String message,LocalDateTime dateTime){
        this.status = status;
        this.message = message;
        this.expDate = dateTime;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getExpDate() {
        return expDate;
    }
}
