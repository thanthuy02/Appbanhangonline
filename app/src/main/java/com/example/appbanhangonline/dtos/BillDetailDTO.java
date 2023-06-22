package com.example.appbanhangonline.dtos;

import com.example.appbanhangonline.models.DetailBill;

public class BillDetailDTO extends DetailBill {
    private BillDTO billDTO;
    private ProductDTO productDTO;

    public BillDetailDTO() {
    }

    public BillDetailDTO(int billDetailID, String billID, String productId, int quantity, double price, BillDTO billDTO, ProductDTO productDTO) {
        super(billDetailID, billID, productId, quantity, price);
        this.billDTO = billDTO;
        this.productDTO = productDTO;
    }

    public BillDTO getBillDTO() {
        return billDTO;
    }

    public void setBillDTO(BillDTO billDTO) {
        this.billDTO = billDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
