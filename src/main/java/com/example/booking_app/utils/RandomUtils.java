package com.example.booking_app.utils;

import java.time.LocalDate;
import java.util.Random;

public class RandomUtils {
    private static final String[] HO_LIST = {
        "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ", "Đặng"
    };

    private static final String[] DEM_LIST = {
        "Văn", "Thị", "Hữu", "Minh", "Quốc", "Thanh", "Ngọc", "Đức", "Gia", "Khánh"
    };
    private static final String[] TEN_LIST = {
        "Anh", "Bình", "Châu", "Dũng", "Em", "Hà", "Hải", "Hân", "Khôi", "Lan", "Long", "Linh", "Minh", "Nhi", "Phong",
        "Phúc", "Quân", "Thảo", "Thành", "Trang"
    };

    public static String generateRandomName() {
        Random random = new Random();

        // Chọn ngẫu nhiên họ, tên đệm, và tên
        String ho = HO_LIST[random.nextInt(HO_LIST.length)];
        String dem = DEM_LIST[random.nextInt(DEM_LIST.length)];
        String ten = TEN_LIST[random.nextInt(TEN_LIST.length)];

        // Ghép lại thành một tên hoàn chỉnh
        return ho + " " + dem + " " + ten;
    }

    public static LocalDate generateRandomBirthdate() {
        Random random = new Random();

        // Chọn ngẫu nhiên năm sinh trong khoảng từ 1963 đến 2005
        int minYear = 1990;
        int maxYear = 2005;
        int year = minYear + random.nextInt(maxYear - minYear + 1);

        // Chọn ngẫu nhiên tháng (1 đến 12)
        int month = 1 + random.nextInt(12);

        // Chọn ngẫu nhiên ngày trong tháng
        int day = 1 + random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth());

        // Tạo đối tượng LocalDate từ năm, tháng, ngày
        return LocalDate.of(year, month, day);
    }

    private static final String[] HOMETOWN_LIST = {
        "An Giang",
        "Bà Rịa – Vũng Tàu",
        "Bạc Liêu",
        "Bắc Giang",
        "Bắc Kạn",
        "Bắc Ninh",
        "Bến Tre",
        "Bình Dương",
        "Bình Định",
        "Bình Phước",
        "Bình Thuận",
        "Cà Mau",
        "Cao Bằng",
        "Cần Thơ",
        "Đà Nẵng",
        "Đắk Lắk",
        "Đắk Nông",
        "Điện Biên",
        "Đồng Nai",
        "Đồng Tháp",
        "Gia Lai",
        "Hà Giang",
        "Hà Nam",
        "Hà Nội",
        "Hà Tĩnh",
        "Hải Dương",
        "Hải Phòng",
        "Hậu Giang",
        "Hòa Bình",
        "Thành phố Hồ Chí Minh",
        "Hưng Yên",
        "Khánh Hòa",
        "Kiên Giang",
        "Kon Tum",
        "Lai Châu",
        "Lạng Sơn",
        "Lào Cai",
        "Lâm Đồng",
        "Long An",
        "Nam Định",
        "Nghệ An",
        "Ninh Bình",
        "Ninh Thuận",
        "Phú Thọ",
        "Phú Yên",
        "Quảng Bình",
        "Quảng Nam",
        "Quảng Ngãi",
        "Quảng Ninh",
        "Quảng Trị",
        "Sóc Trăng",
        "Sơn La",
        "Tây Ninh",
        "Thái Bình",
        "Thái Nguyên",
        "Thanh Hóa",
        "Thừa Thiên Huế",
        "Tiền Giang",
        "Trà Vinh",
        "Tuyên Quang",
        "Vĩnh Long",
        "Vĩnh Phúc",
        "Yên Bái"
    };

    public static String generateRandomHometown() {
        Random random = new Random();
        return HOMETOWN_LIST[random.nextInt(HOMETOWN_LIST.length)];
    }

    private static final String[] DISTRICTS = {
        "Ba Đình", "Hai Bà Trưng", "Thanh Xuân", "Bình Thạnh", "Gò Vấp",
        "Tân Bình", "Hoàn Kiếm", "Ninh Kiều", "Sơn Trà", "Hồng Bàng",
        "Ngũ Hành Sơn", "Liên Chiểu", "Lê Chân", "Cẩm Lệ", "Phú Nhuận"
    };

    // Danh sách tên đường
    private static final String[] STREETS = {
        "Nguyễn Huệ",
        "Lê Lợi",
        "Trần Phú",
        "Quang Trung",
        "Lê Duẩn",
        "Trường Chinh",
        "Phạm Văn Đồng",
        "Nguyễn Trãi",
        "Lê Quý Đôn",
        "Nguyễn Văn Cừ",
        "Lý Thường Kiệt",
        "Hùng Vương",
        "Ngô Quyền",
        "Điện Biên Phủ",
        "Hai Bà Trưng"
    };

    public static String generateRandomAddress() {
        Random random = new Random();

        // Tạo số nhà ngẫu nhiên từ 1 đến 999
        int houseNumber = 1 + random.nextInt(999);

        // Chọn ngẫu nhiên một tên đường, quận/huyện và thành phố/tỉnh
        String street = STREETS[random.nextInt(STREETS.length)];
        String district = DISTRICTS[random.nextInt(DISTRICTS.length)];
        String city = HOMETOWN_LIST[random.nextInt(HOMETOWN_LIST.length)];

        // Ghép các thành phần lại thành một địa chỉ
        return "Số " + houseNumber + " đường " + street + ", " + "quận " + district + ", " + city;
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();

        // Phần đầu số: 09 hoặc 03, 07, 08 (dành cho nhà mạng Việt Nam phổ biến)
        String[] prefixes = {"09", "03", "07", "08", "05"};
        String prefix = prefixes[random.nextInt(prefixes.length)];

        // Phần tiếp theo là 8 chữ số ngẫu nhiên
        StringBuilder phoneNumber = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(random.nextInt(10));
        }

        return phoneNumber.toString();
    }
}
