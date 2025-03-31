package sumit.sharma.com.modules;

import com.google.gson.Gson;
import sumit.sharma.com.pojos.*;

public class PayloadManager {

    // Converting the Java Object to String.
    // GSON

    Gson gson;

    public String createPayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Sumit");
        booking.setLastname("Sharma");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-03-01");
        bookingdates.setCheckout("2025-03-02");
        booking.setBookingdates(bookingdates);

        booking.setAdditionalneeds("Breakfast");

        //Serialization

        System.out.println(booking); // Now this is an object , convert it in string
        gson=new Gson();
        String jsonStringPayload= gson.toJson(booking);
        System.out.println(jsonStringPayload);

        return jsonStringPayload;
    }

   // public String createPayloadBookingAsStringfromExcel(){}

    //Deserialization

    public BookingResponse bookingResponseJava(String responseString){
        gson=new Gson();
        BookingResponse bookingResponse=gson.fromJson(responseString, BookingResponse.class);

        return bookingResponse;
    }

    public Booking getResponseFromJSON(String getResponse){
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    public String setAuthPayload(){
        // AUTH Object --> JSON String
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the --> "+ jsonPayloadString);
        return jsonPayloadString;
    }

    public String getTokenFromJson(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public String fullUpdatePayloadAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Udit");
        booking.setLastname("Sharma");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-03-01");
        bookingdates.setCheckout("2025-03-02");
        booking.setBookingdates(bookingdates);

        booking.setAdditionalneeds("Breakfast");

        return gson.toJson(booking);
    }

}
