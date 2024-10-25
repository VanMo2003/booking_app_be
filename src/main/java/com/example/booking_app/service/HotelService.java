package com.example.booking_app.service;

import com.example.booking_app.dto.request.HotelRequest;
import com.example.booking_app.dto.response.HotelResponse;
import com.example.booking_app.entity.Hotel;
import com.example.booking_app.entity.User;
import com.example.booking_app.exception.AppException;
import com.example.booking_app.exception.ErrorCode;
import com.example.booking_app.mapper.HotelMapper;
import com.example.booking_app.mapper.UserMapper;
import com.example.booking_app.repository.HotelRepository;
import com.example.booking_app.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelService {
    HotelRepository hotelRepository;
    UserRepository userRepository;
    HotelMapper hotelMapper;
    UserMapper userMapper;

    public List<HotelResponse> getAllHotel(){
        return hotelRepository.findAll().stream().map(hotel ->
             hotelMapper.toFootballPitchesResponse(hotel)
        ).toList();
    }

    public HotelResponse createHotel(HotelRequest request){
        User user = userRepository.findById(request.getUserID()).orElseThrow(() -> {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        });

        if (hotelRepository.existsByUserId(request.getUserID()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Hotel hotel = hotelMapper.toFootballPitches(request);

        hotel.setUser(user);

        HotelResponse hotelResponse = hotelMapper.toFootballPitchesResponse(hotelRepository.save(hotel));
        hotelResponse.setUser(userMapper.toUserResponse(user));

        return hotelResponse;
    }
    public HotelResponse updateHotel(Long id,HotelRequest request){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.HOTEL_NOT_EXISTED));

        hotelMapper.updateFootballPitches(hotel, request);

        User user;
        if (!Objects.isNull(request.getUserID())) {
            user = userRepository.findById(request.getUserID()).orElseThrow(() -> {
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            });
            hotel.setUser(user);
        }


        HotelResponse hotelResponse = hotelMapper.toFootballPitchesResponse(hotelRepository.save(hotel));
        hotelResponse.setUser(userMapper.toUserResponse(hotel.getUser()));

        return hotelResponse;
    }

    @PreAuthorize("hasRole('ADMIN')") // chặn trước khi gọi hàm để kiểm tra role
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

}
