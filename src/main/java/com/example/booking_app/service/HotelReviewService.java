package com.example.booking_app.service;

import com.example.booking_app.dto.request.HotelReviewRequest;
import com.example.booking_app.dto.response.HotelReviewResponse;
import com.example.booking_app.entity.HotelReview;
import com.example.booking_app.entity.User;
import com.example.booking_app.mapper.HotelReviewMapper;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.HotelReviewRepository;
import com.example.booking_app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelReviewService {
    HotelReviewRepository hotelReviewRepository;
    HotelReviewMapper hotelReviewMapper;
    public List<HotelReviewResponse> getAllReviews() {
        return hotelReviewRepository.findAll().stream().map(hotelReview -> {
            return  hotelReviewMapper.toHotelReviewResponse(hotelReview);
        }).toList();
    }

    public HotelReviewResponse createReview(HotelReviewRequest request) {

        HotelReview hotelReview =  hotelReviewRepository.save(hotelReviewMapper.toHotelReview(request));

        return hotelReviewMapper.toHotelReviewResponse(hotelReview);
    }

    public void deleteReview(Long id) {
        hotelReviewRepository.deleteById(id);
    }
}
