import { formatPrice } from "/js/common/format.js";

document.addEventListener('DOMContentLoaded', function () {
  async function productDetail(productId) {
    const response = await fetch(`/api/products?id=${productId}`);
    console.log("productId: " + productId)
    const jsonData = await response.json();
    const data = jsonData.data;

    const nameElements = document.getElementsByClassName("product-name");
    for (let i = 0; i < nameElements.length; i++) {
      nameElements[i].textContent = data.name;
    }

    document.getElementById("product-image").src = data.image;
    document.getElementById("discount-rate").textContent = data.discountRate + "%";
    document.getElementById("selling-price").textContent = formatPrice(data.sellingPrice);
    document.getElementById("final-price").textContent = formatPrice(data.finalPrice);
    document.getElementById("product-description").src = data.description.description;
    document.getElementById("product-stock").textContent = data.stock;

  }

  // productDetail(productId);

  /**
   * 가격을 한국 원화(KRW) 형식으로 포맷팅
   */
  const priceElements = document.querySelectorAll(".price");
  priceElements.forEach(function (priceElement) {
    const price = parseInt(priceElement.getAttribute("data-price"));
    priceElement.textContent = formatPrice(price);
  })

  /**
   * 장바구니 담기 상품 수량 증가/감소 버튼
   */
  const decreaseBtn = document.getElementById("decrease-btn");
  const quantityInput = document.getElementById("quantity-input");
  const increaseBtn = document.getElementById("increase-btn");
  const QUANTITY_MIN = 1;
  const QUANTITY_MAX = 99;

  // 장바구니 담기 수량 감소 버튼
  decreaseBtn.addEventListener("click", () => {
    let currentValue = parseInt(quantityInput.value);
    if (currentValue > QUANTITY_MIN) {
      quantityInput.value = currentValue - 1;
    }
  })

  // 장바구니 담기 수량 증가 버튼
  increaseBtn.addEventListener("click", () => {
    let currentValue = parseInt(quantityInput.value);
    if (currentValue < QUANTITY_MAX) {
      quantityInput.value = currentValue + 1;
    }
  })
});