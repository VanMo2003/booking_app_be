//package com.example.booking_app.service;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//import com.example.booking_app.constant.StatusOrder;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import com.example.booking_app.dto.request.OrderBookingRequest;
//import com.example.booking_app.dto.response.HotelResponse;
//import com.example.booking_app.dto.response.OrderBookingResponse;
//import com.example.booking_app.dto.response.UserResponse;
//import com.example.booking_app.entity.Hotel;
//import com.example.booking_app.entity.Booking;
//import com.example.booking_app.entity.User;
//import com.example.booking_app.exception.AppException;
//import com.example.booking_app.exception.ErrorCode;
//import com.example.booking_app.mapper.HotelMapper;
//import com.example.booking_app.mapper.OrderBookingMapper;
//import com.example.booking_app.mapper.UserMapper;
//import com.example.booking_app.repository.HotelRepository;
//import com.example.booking_app.repository.OrderBookingRepository;
//import com.example.booking_app.repository.UserRepository;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class OrderBookingService {
//    OrderBookingRepository orderBookingRepository;
//    OrderBookingMapper orderBookingMapper;
//    UserRepository userRepository;
//    HotelRepository hotelRepository;
//    UserMapper userMapper;
//    HotelMapper hotelMapper;
//
//    public List<OrderBookingResponse> getAllOrder() {
//        List<OrderBookingResponse> orderBookingResponses = orderBookingRepository.findAll().stream()
//                .map(orderBooking -> {
//                    OrderBookingResponse response = orderBookingMapper.toOrderBookingResponse(orderBooking);
//                    response.setUser(userMapper.toUserResponse(orderBooking.getUser()));
//                    response.setHotel(hotelMapper.toHotelResponse(orderBooking.getHotel()));
//
//                    return response;
//                })
//                .toList();
//
//        return orderBookingResponses;
//    }
//
//    public OrderBookingResponse createOrder(OrderBookingRequest request) {
//        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> {
//            throw new AppException(ErrorCode.USER_NOT_EXISTED);
//        });
//
//        UserResponse userResponse = userMapper.toUserResponse(user);
//
//        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> {
//            throw new AppException(ErrorCode.HOTEL_NOT_EXISTED);
//        });
//
//        HotelResponse hotelResponse = hotelMapper.toHotelResponse(hotel);
//
//        Booking booking = orderBookingMapper.toOrderBooking(request);
//
////        booking.setTotalPrice(BigDecimal.valueOf(hotel.getPrice() * request.getNumberOfRoom()));
//        booking.setUser(user);
//        booking.setHotel(hotel);
//
//        OrderBookingResponse response =
//                orderBookingMapper.toOrderBookingResponse(orderBookingRepository.save(booking));
//        response.setUser(userResponse);
//        response.setHotel(hotelResponse);
//
//        return response;
//    }
//
//    public OrderBookingResponse updateStatusOrder(Long id,boolean isConfirm){
//        var context = SecurityContextHolder.getContext();
//        String username = context.getAuthentication().getName();
//
//        Booking booking = orderBookingRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXISTED));
//
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        if (user.getId().equals(booking.getUser().getId())){
//            if (isConfirm) throw new AppException(ErrorCode.UN_AUTHENTICATED);
//            else booking.setStatusOrder(StatusOrder.CANCELED);
//        }
//
//        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow(() ->  new AppException(ErrorCode.UN_AUTHENTICATED));
//
//        if (hotel.getId().equals(booking.getHotel().getId())){
//            if (isConfirm) booking.setStatusOrder(StatusOrder.CONFIRMED);
//            else booking.setStatusOrder(StatusOrder.CANCELED);
//        }
//
//        OrderBookingResponse orderBookingResponse = orderBookingMapper.toOrderBookingResponse(orderBookingRepository.save(booking));
//        orderBookingResponse.setUser(userMapper.toUserResponse(booking.getUser()));
//        orderBookingResponse.setHotel(hotelMapper.toHotelResponse(booking.getHotel()));
//
//        return orderBookingResponse;
//    }
//    public List<OrderBookingResponse> getOrderMySelf(){
//        var context = SecurityContextHolder.getContext();
//        String username = context.getAuthentication().getName();
//
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//
//        List<OrderBookingResponse>  orderBookingResponses = new ArrayList<>();
//        orderBookingRepository.findByUserId(user.getId()).forEach (orderBooking -> {
//            OrderBookingResponse response = orderBookingMapper.toOrderBookingResponse(orderBooking);
//
//            response.setUser(userMapper.toUserResponse(user));
//            response.setHotel(hotelMapper.toHotelResponse(orderBooking.getHotel()));
//            orderBookingResponses.add(response) ;
//        });
//
//        orderBookingResponses.sort(Comparator.comparing(OrderBookingResponse::getOnCreate).reversed());
//
//        return orderBookingResponses;
//    }
//
//    public List<OrderBookingResponse> getOrderOfHotel(){
//        var context = SecurityContextHolder.getContext();
//        String username = context.getAuthentication().getName();
//
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        Hotel hotel = hotelRepository.findByUserId(user.getId()).orElseThrow(() ->  new AppException(ErrorCode.UN_AUTHENTICATED));
//        List<OrderBookingResponse> orderBookingResponses = new ArrayList<>();
//        orderBookingRepository.findByHotelId(hotel.getId()).forEach(orderBooking -> {
//            OrderBookingResponse response = orderBookingMapper.toOrderBookingResponse(orderBooking);
//
//            response.setUser(userMapper.toUserResponse(orderBooking.getUser()));
//            response.setHotel(hotelMapper.toHotelResponse(hotel));
//            orderBookingResponses.add(response) ;
//
//        });
//
//        orderBookingResponses.sort(Comparator.comparing(OrderBookingResponse::getOnCreate).reversed());
//
//        return orderBookingResponses;
//    }
//}
