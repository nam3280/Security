package com.ssg.wannavapibackend.controller.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @GetMapping("/list")
    public String getCartList() {
        return """
            {
                "status": "success",
                "data": {
                    "dataList": [
                        {
                            "id": 30,
                            "productId": 30,
                            "quantity": 6,
                            "productName": "풀무원 식물성 유니 짜장면 2인분, 620g, 1개 2인분, 620g, 1개 풀무원 식물성 유니 짜장면 2인분, 620g, 1개 2인분, 620g, 1개",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 18700.0
                        },
                        {
                            "id": 29,
                            "productId": 45,
                            "quantity": 3,
                            "productName": "아보카도 샐러드 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 16200.0
                        },
                        {
                            "id": 28,
                            "productId": 51,
                            "quantity": 9,
                            "productName": "토마토소스파스타밀키트토마토소스파스타밀키트토마토소스파스타밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 12000.0
                        },
                        {
                            "id": 27,
                            "productId": 39,
                            "quantity": 4,
                            "productName": "참치죽 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 7650.0
                        },
                        {
                            "id": 26,
                            "productId": 12,
                            "quantity": 1,
                            "productName": "냉면 밀키트 냉면 밀키트 냉면 밀키트 냉면 밀키트 냉면 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 9500.0
                        },
                        {
                            "id": 25,
                            "productId": 52,
                            "quantity": 7,
                            "productName": "사골국 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 14400.0
                        },
                        {
                            "id": 24,
                            "productId": 52,
                            "quantity": 7,
                            "productName": "비빔밥 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 13000.0
                        },
                        {
                            "id": 23,
                            "productId": 37,
                            "quantity": 3,
                            "productName": "순두부찌개 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 7650.0
                        },
                        {
                            "id": 22,
                            "productId": 33,
                            "quantity": 1,
                            "productName": "닭가슴살 샐러드 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 13500.0
                        },
                        {
                            "id": 21,
                            "productId": 23,
                            "quantity": 1,
                            "productName": "카레 밀키트",
                            "productImage": "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/retail/images/950531282389413-5a75db2d-00d6-4e81-8caf-9a0085b15921.jpg",
                            "productFinalPrice": 9600.0
                        }
                    ]
                }
            }
            """;
    }
}
