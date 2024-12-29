package com.example.booking_app.service;

import com.example.booking_app.dto.request.HotelReviewRequest;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.dto.response.HotelReviewResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.HotelReview;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.HotelMapper;
import com.example.booking_app.mapper.HotelReviewMapper;
import com.example.booking_app.mapper.UserMapper;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.HotelReviewRepository;
import com.example.booking_app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelReviewService {
    HotelReviewRepository hotelReviewRepository;
    UserRepository userRepository;
    HotelRepository hotelRepository;
    HotelReviewMapper hotelReviewMapper;
    UserMapper userMapper;
    HotelMapper hotelMapper;
    public List<HotelReviewResponse> getAllReviews(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        return hotelReviewRepository.findByHotel(hotel).stream().map(hotelReview -> {
            HotelReviewResponse hotelReviewResponse = hotelReviewMapper.toHotelReviewResponse(hotelReview);
            hotelReviewResponse.setUser(userMapper.toUserResponse(hotelReview.getUser()));
            hotelReviewResponse.setHotel(hotelMapper.toHotelResponse(hotelReview.getHotel()));

            return hotelReviewResponse;
        }).toList();
    }

    public HotelReviewResponse createReview(HotelReviewRequest request) {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user =
                userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));
        HotelReview hotelReview =  hotelReviewMapper.toHotelReview(request);
        hotelReview.setUser(user);
        hotelReview.setHotel(hotel);

        HotelReviewResponse hotelResponse = hotelReviewMapper.toHotelReviewResponse(hotelReviewRepository.save(hotelReview));
        hotelResponse.setUser(userMapper.toUserResponse(user));
        hotelResponse.setHotel(hotelMapper.toHotelResponse(hotel));


        return hotelResponse;
    }

    public void deleteReview(Long id) {
        hotelReviewRepository.deleteById(id);
    }
}
